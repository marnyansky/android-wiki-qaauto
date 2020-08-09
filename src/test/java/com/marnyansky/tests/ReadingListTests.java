package com.marnyansky.tests;

import com.marnyansky.pages.PgCurrentArticleHelper;
import com.marnyansky.pages.PgHomePageHelper;
import com.marnyansky.pages.PgReadingListHelper;
import com.marnyansky.pages.PgSearchHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadingListTests extends TestBase {

    private PgHomePageHelper homePage;
    private PgSearchHelper searchPage;
    private PgCurrentArticleHelper currentArticlePage;
    private PgReadingListHelper readingListPage;
    private String article;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);
        searchPage = PageFactory.initElements(driver, PgSearchHelper.class);
        currentArticlePage = PageFactory.initElements(driver, PgCurrentArticleHelper.class);
        readingListPage = PageFactory.initElements(driver, PgReadingListHelper.class);

        article = "Selenium (software)";
        currentArticlePage.setArticle(article);

        homePage.waitUntilPageIsLoaded();
        homePage.openSearchPage();
        searchPage.waitUntilPageIsLoaded();
    }

    @Test(enabled = false, groups = {"regression"}/*, retryAnalyzer = Retry.class*/)
    public void testArticleIsInReadingList() {
        searchPage.inputSearchQuery("Selenium")
                .openArticle(article);

        currentArticlePage.waitUntilPageIsLoaded();
        currentArticlePage.bookmarkArticle();

        //--- switch to alert/frame/window
        //--- waitUntil...isLoaded
        //--- perform actions

        /*
        driver.switchTo().frame(driver.findElement(By.id("android:id/content")));
        Thread.sleep(5000);
        driver.findElement(By.id("org.wikipedia:id/text_input")).sendKeys("SeleniumList");
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("org.wikipedia:id/text_input")).sendKeys("SeleniumList");
        driver.findElement(By.id("android:id/button1")).click();
        driver.switchTo().parentFrame();
         */

        //--- switch to alert/frame/window

        currentArticlePage.navigateToHomePage();

        homePage.waitUntilPageIsLoaded();
        homePage.openReadingListPage();

        readingListPage.waitUntilPageIsLoaded();

        Assert.assertTrue(readingListPage.verifyBookmarkedArticleTitle(article),
                "\nThe bookmarked article title '" + article
                        + "' doesn't correspond to the actual article title\n");
    }

}
