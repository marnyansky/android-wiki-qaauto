package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgCurrentReadingListHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    WebElement articleTitle;

    @FindBy(id = "org.wikipedia:id/page_list_item_container")
    WebElement articleContainer;

    @FindBy(xpath = "//*[@content-desc='Navigate up']")
    WebElement navigateBackButton;

    @FindBy(id = "org.wikipedia:id/item_reading_list_statistical_description")
    WebElement readingListStatistics;

    @FindBy(id = "org.wikipedia:id/reading_list_empty_text")
    WebElement emptyReadingListMessage;

    //--- CTOR
    public PgCurrentReadingListHelper(WebDriver driver) {
        super(driver);
    }

    public boolean verifyBookmarkedArticleTitle(String article) {
        waitUntilElementIsVisible(articleTitle, 15);
        return articleTitle.getText().equals(article);
    }

    public PgCurrentReadingListHelper swipeToRemove() {
        waitUntilElementIsClickable(articleContainer, 15);
        swipeLeft(articleContainer);
        return this;
    }

    public void navigateToReadingListsPage() {
        waitUntilElementIsClickable(navigateBackButton, 15);
        navigateBackButton.click();
    }

    public boolean verifyNoArticlesInReadingList() {
        waitUntilElementIsVisible(readingListStatistics, 15);
        waitUntilElementIsVisible(emptyReadingListMessage, 15);
        return readingListStatistics.getText().startsWith("0 of 0")
                && emptyReadingListMessage.getText().contains("no articles");
    }
}
