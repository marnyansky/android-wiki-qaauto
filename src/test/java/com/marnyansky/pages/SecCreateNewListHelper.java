package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecCreateNewListHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/text_input")
    WebElement listTitleField;

    @FindBy(id = "android:id/button1")
    WebElement submitButton;

    //--- CTOR
    public SecCreateNewListHelper(WebDriver driver) {
        super(driver);
    }

    public void setListTitle(String listTitle) {
        fillField(listTitleField, listTitle);
        submitButton.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(listTitleField, 15);
        waitUntilElementIsClickable(submitButton, 15);
    }

}
