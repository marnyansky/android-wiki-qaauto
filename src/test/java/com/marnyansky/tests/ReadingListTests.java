package com.marnyansky.tests;

import com.marnyansky.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReadingListTests extends TestBase {

    private PgHomePageHelper homePage;
    private PgSearchHelper searchPage;
    private PgCurrentArticleHelper currentArticlePage;
    private SecListTipHelper listTipSection;
    private PgReadingListHelper readingListPage;
    private String article;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);
        searchPage = PageFactory.initElements(driver, PgSearchHelper.class);
        currentArticlePage = PageFactory.initElements(driver, PgCurrentArticleHelper.class);
        listTipSection = PageFactory.initElements(driver, SecListTipHelper.class);
        readingListPage = PageFactory.initElements(driver, PgReadingListHelper.class);

        article = "Selenium (software)";
        currentArticlePage.setArticle(article);

        homePage.waitUntilPageIsLoaded();
        homePage.openSearchPage();
        searchPage.waitUntilPageIsLoaded();
    }

    @Test(enabled = false, groups = {"regression"}/*, retryAnalyzer = Retry.class*/)
    public void testArticleIsInReadingList() throws InterruptedException {
        searchPage.inputSearchQuery("Selenium")
                .openArticle(article);

        currentArticlePage.waitUntilPageIsLoaded();
        currentArticlePage.bookmarkArticle();

        listTipSection.clickAcceptButton();

        Thread.sleep(5000);
//        driver.switchTo().frame(0);
        driver.switchTo().frame(driver.findElement(By.id("org.wikipedia:id/parentPanel")));
        Thread.sleep(5000);
        driver.findElement(By.id("org.wikipedia:id/text_input")).sendKeys("SeleniumList");
        driver.findElement(By.id("android:id/button1")).click();
        Thread.sleep(5000);

        currentArticlePage.navigateToHomePage();

        homePage.waitUntilPageIsLoaded();
        homePage.openReadingListPage();

        readingListPage.waitUntilPageIsLoaded();

        Assert.assertTrue(readingListPage.verifyBookmarkedArticleTitle(article),
                "\nThe bookmarked article title '" + article
                        + "' doesn't correspond to the actual article title\n");
    }

}
