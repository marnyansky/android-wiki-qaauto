package com.marnyansky.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PgCurrentReadingListHelper extends PageBase {

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_title")
    private AndroidElement articleTitle;

    @AndroidFindBy(id = "org.wikipedia:id/page_list_item_container")
    private AndroidElement articleContainer;

    @AndroidFindBy(xpath = "//*[@content-desc='Navigate up']")
    private AndroidElement navigateBackButton;

    @AndroidFindBy(id = "org.wikipedia:id/item_reading_list_statistical_description")
    private AndroidElement readingListStatistics;

    @AndroidFindBy(id = "org.wikipedia:id/reading_list_empty_text")
    private AndroidElement emptyReadingListMessage;

    //--- CTOR
    public PgCurrentReadingListHelper(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean articleTitleIsCorrect(String value) {
        waitUntilElementIsVisible(articleTitle, 15);
        return elementTextIsCorrect(articleTitle, value);
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

    public boolean readingListIsEmpty() {
        waitUntilElementIsVisible(readingListStatistics, 15);
        waitUntilElementIsVisible(emptyReadingListMessage, 15);
        return readingListStatistics.getText().startsWith("0 of 0")
                && emptyReadingListMessage.getText().contains("no articles");
    }
}
