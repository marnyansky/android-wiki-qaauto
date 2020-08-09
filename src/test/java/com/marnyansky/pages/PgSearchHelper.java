package com.marnyansky.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PgSearchHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/search_src_text")
    WebElement searchFieldBlack;

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    List<WebElement> listOfArticles;

    public PgSearchHelper(WebDriver driver) {
        super(driver);
    }

    public PgSearchHelper inputSearchQuery(String searchQuery) {
        fillField(searchFieldBlack, searchQuery);
        waitUntilAllElementsAreVisible(listOfArticles, 15);
        return this;
    }

    public boolean articleIsInSearchResult(String articleTitle) {
        for (WebElement element : listOfArticles) {
            if (element.getText().equals(articleTitle)) {
                return true;
            }
        }
        return false;
    }

    public void openArticle(String article) {
        WebElement articleResult = driver.findElement(By.xpath("//*[@text='" + article + "']"));
        articleResult.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(searchFieldBlack, 15);
    }

}
