package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgReadingListHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    WebElement articleTitle;

    @FindBy(id = "org.wikipedia:id/page_list_item_action_primary")
    WebElement itemMenu;

    //--- CTOR
    public PgReadingListHelper(WebDriver driver) {
        super(driver);
    }

    public boolean verifyBookmarkedArticleTitle(String article) {
        return articleTitle.getText().equals(article);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(articleTitle, 15);
        waitUntilElementIsClickable(itemMenu, 15);
    }

}
