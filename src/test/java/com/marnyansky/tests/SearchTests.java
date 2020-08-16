package com.marnyansky.tests;

import com.marnyansky.pages.PgCurrentArticleHelper;
import com.marnyansky.pages.PgHomePageHelper;
import com.marnyansky.pages.PgSearchHelper;
import com.marnyansky.util.DataProviders;
import com.marnyansky.util.Retry;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {

    private PgHomePageHelper homePage;
    private PgSearchHelper searchPage;
    private PgCurrentArticleHelper currentArticlePage;
    private final String article = "Selenium (software)";

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = new PgHomePageHelper(driver);
        searchPage = new PgSearchHelper(driver);
        currentArticlePage = new PgCurrentArticleHelper(driver, article);

        homePage.openSearchPage();
    }

    @Test(groups = {"smoke", "regression"})
    public void testSearchArticle() {
        searchPage.inputSearchQuery(article);

        Assert.assertTrue(searchPage.articleIsInSearchResult(article),
                "\nThe article '" + article + "' is not in the search result\n");
    }

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = Retry.class)
    public void testSearchArticleAndOpenIt() {
        searchPage.inputSearchQuery("Selenium")
                .openArticle(article);

        Assert.assertTrue(currentArticlePage.articleTitleIsCorrect(),
                "\nThe article title '" + article + "' doesn't correspond " +
                        "to the actual article title\n");
    }

    @Test(groups = {"regression"})
    public void testSearchArticleAndOpenItRandomFun() {
        String penguinArticle = "Penguin";

        searchPage.inputSearchQuery(penguinArticle)
                .openArticle(penguinArticle);
        currentArticlePage.articleTitleIsCorrectDp(penguinArticle);
        currentArticlePage.randomFun(); //--- calamity begins

        Assert.assertTrue(currentArticlePage.articleTitleIsCorrectDp(penguinArticle),
                "\nMagic failure! The randomFun() broke the test!\n");
    }

    @Test(groups = {"retired"}, retryAnalyzer = Retry.class,
            dataProviderClass = DataProviders.class,
            dataProvider = "articleSearchDp1")
    public void testSearchArticleAndOpenItDp(String searchQuery, String articleTitle) {
        searchPage.inputSearchQuery(searchQuery)
                .openArticle(articleTitle);

        Assert.assertTrue(currentArticlePage.articleTitleIsCorrectDp(articleTitle),
                "\nThe article title '" + articleTitle + "' doesn't correspond " +
                        "to the actual article title\n");
    }

    @Test(groups = {"regression"}, retryAnalyzer = Retry.class,
            dataProviderClass = DataProviders.class,
            dataProvider = "articleSearchFromFileDp1")
    public void testSearchArticleAndOpenItDpAdvanced(String searchQuery, String articleTitle) {
        searchPage.inputSearchQuery(searchQuery)
                .openArticle(articleTitle);

        Assert.assertTrue(currentArticlePage.articleTitleIsCorrectDp(articleTitle),
                "\nThe article title '" + articleTitle + "' doesn't correspond " +
                        "to the actual article title\n");
    }

    @Test(groups = {"regression"}, retryAnalyzer = Retry.class)
    public void testSearchArticleOpenItAndRotate() {
        searchPage.inputSearchQuery("Selenium")
                .openArticle(article)
                .rotateScreenLandscape();
        Assert.assertTrue(currentArticlePage.articleTitleIsCorrect());
    }

    @Test(groups = {"regression"}, retryAnalyzer = Retry.class)
    public void testSearchArticleOpenItAndRunInBackground() {
        searchPage.inputSearchQuery("Selenium").
                runInBackground(2);
        searchPage.openArticle(article);
        Assert.assertTrue(currentArticlePage.articleTitleIsCorrect());
    }


}
