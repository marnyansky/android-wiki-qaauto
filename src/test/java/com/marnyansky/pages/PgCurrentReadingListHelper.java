package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgCurrentReadingListHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    WebElement articleTitle;

    //--- CTOR
    public PgCurrentReadingListHelper(WebDriver driver) {
        super(driver);
    }

    public boolean verifyBookmarkedArticleTitle(String article) {
        waitUntilElementIsVisible(articleTitle, 15);
        return articleTitle.getText().equals(article);
    }

}
