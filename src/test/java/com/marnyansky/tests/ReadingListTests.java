package com.marnyansky.tests;

import com.marnyansky.pages.*;
import com.marnyansky.util.Retry;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadingListTests extends TestBase {

    private PgHomePageHelper homePage;
    private PgSearchHelper searchPage;
    private PgCurrentArticleHelper currentArticlePage;
    private PgReadingListsHelper readingListsPage;
    private PgCurrentReadingListHelper currentReadingListPage;
    private String article;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);
        searchPage = PageFactory.initElements(driver, PgSearchHelper.class);
        currentArticlePage = PageFactory.initElements(driver, PgCurrentArticleHelper.class);
        readingListsPage = PageFactory.initElements(driver, PgReadingListsHelper.class);
        currentReadingListPage = PageFactory.initElements(driver, PgCurrentReadingListHelper.class);

        article = "Selenium (software)";
        currentArticlePage.setArticle(article);

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

        Assert.assertTrue(currentReadingListPage.verifyBookmarkedArticleTitle(article),
                "\nThe bookmarked article title '" + article
                        + "' doesn't correspond to the actual article title\n");
    }

}
