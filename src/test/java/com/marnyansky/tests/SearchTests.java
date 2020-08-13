package com.marnyansky.tests;

import com.marnyansky.pages.PgCurrentArticleHelper;
import com.marnyansky.pages.PgHomePageHelper;
import com.marnyansky.pages.PgSearchHelper;
import com.marnyansky.util.Retry;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {

    private PgHomePageHelper homePage;
    private PgSearchHelper searchPage;
    private PgCurrentArticleHelper currentArticlePage;
    private String article;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);
        searchPage = PageFactory.initElements(driver, PgSearchHelper.class);
        currentArticlePage = PageFactory.initElements(driver, PgCurrentArticleHelper.class);

        article = "Selenium (software)";
        currentArticlePage.setArticle(article);

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

        Assert.assertTrue(currentArticlePage.verifyArticleTitle(),
                "\nThe article title '" + article + "' doesn't correspond " +
                        "to the actual article title\n");
    }

}
