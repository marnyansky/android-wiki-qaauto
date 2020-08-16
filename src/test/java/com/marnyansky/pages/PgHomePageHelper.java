package com.marnyansky.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PgHomePageHelper extends PageBase {

    @AndroidFindBy(className = "android.widget.TextView")
    private AndroidElement searchField;

    @AndroidFindBy(xpath = "//*[@content-desc='My lists']")
    private AndroidElement bookmarkIcon;

    //--- CTOR
    public PgHomePageHelper(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public boolean placeholderIsCorrect(String placeholder) {
        waitUntilElementIsClickable(searchField, 30);
        return elementTextIsCorrect(searchField, placeholder);
    }

    public void openSearchPage() {
        waitUntilElementIsClickable(searchField, 15);
        searchField.click();
    }

    public void openReadingListsPage() {
        waitUntilElementIsClickable(bookmarkIcon, 15);
        bookmarkIcon.click();
    }

}
