package com.marnyansky.tests;

import com.marnyansky.pages.*;
import com.marnyansky.util.DataProviders;
import com.marnyansky.util.Retry;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadingListTests extends TestBase {

    private PgHomePageHelper homePage;
    private PgSearchHelper searchPage;
    private PgCurrentArticleHelper currentArticlePage;
    private PgReadingListsHelper readingListsPage;
    private PgCurrentReadingListHelper currentReadingListPage;
    private final String article = "Selenium (software)";

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = new PgHomePageHelper(driver);
        searchPage = new PgSearchHelper(driver);
        currentArticlePage = new PgCurrentArticleHelper(driver, article);
        readingListsPage = new PgReadingListsHelper(driver);
        currentReadingListPage = new PgCurrentReadingListHelper(driver);

        homePage.openSearchPage();
    }

    @Test(groups = {"regression"}, retryAnalyzer = Retry.class)
    public void testArticleIsInReadingList() {
        searchPage.inputSearchQuery("Selenium")
                .openArticle(article);
        currentArticlePage.createNewReadingListAndAddArticle("NewList")
                .navigateToHomePage();
        homePage.openReadingListsPage();
        readingListsPage.openReadingList();

        Assert.assertTrue(currentReadingListPage.articleTitleIsCorrect(article),
                "\nThe bookmarked article title '" + article
                        + "' doesn't correspond to the actual article title\n");
    }

    @Test(groups = {"regression"}, retryAnalyzer = Retry.class,
            dataProviderClass = DataProviders.class,
            dataProvider = "articleSearchFromFileDp1")
    public void testArticleIsInReadingListDp(String searchQuery, String articleTitle) {
        searchPage.inputSearchQuery(searchQuery)
                .openArticle(articleTitle);
        currentArticlePage.createNewReadingListAndAddArticle("NewList")
                .navigateToHomePage();
        homePage.openReadingListsPage();
        readingListsPage.openReadingList();

        Assert.assertTrue(currentReadingListPage.articleTitleIsCorrect(articleTitle),
                "\nThe bookmarked article title '" + articleTitle
                        + "' doesn't correspond to the actual article title\n");
    }

    @Test(groups = {"regression"}, retryAnalyzer = Retry.class)
    public void testRemovedArticleIsNotInReadingList() {
        String readingListTitle = "AndroidTestingList";

        searchPage.inputSearchQuery("Selenium")
                .openArticle(article);
        currentArticlePage.createNewReadingListAndAddArticle(readingListTitle)
                .navigateToHomePage();
        homePage.openReadingListsPage();
        readingListsPage.openReadingList();

        currentReadingListPage.swipeToRemove()
                .navigateToReadingListsPage();
        readingListsPage.openReadingList();

        Assert.assertTrue(currentReadingListPage.readingListIsEmpty(),
                "The reading list '" + readingListTitle + "' is not empty");
    }

}
