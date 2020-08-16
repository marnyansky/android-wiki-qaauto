package com.marnyansky.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PgSearchHelper extends PageBase {

    @AndroidFindBy(id = "org.wikipedia:id/search_src_text")
    private AndroidElement searchFieldBlack;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    private List<WebElement> listOfArticles;

    //--- CTOR
    public PgSearchHelper(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public PgSearchHelper inputSearchQuery(String searchQuery) {
        waitUntilElementIsClickable(searchFieldBlack, 15);
        fillField(searchFieldBlack, searchQuery);
        return this;
    }

    public boolean articleIsInSearchResult(String articleTitle) {
        waitUntilAllElementsAreVisible(listOfArticles, 30);
        for (WebElement element : listOfArticles) {
            if (element.getText().equals(articleTitle)) {
                return true;
            }
        }
        return false;
    }

    public PgSearchHelper openArticle(String article) {
        waitUntilAllElementsAreVisible(listOfArticles, 30);
        AndroidElement articleResult = driver.findElement(By
                        .xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title' " +
                                "and @text='" + article + "']"));
        articleResult.click();

        return this;
    }

}
