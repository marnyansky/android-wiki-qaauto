package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgHomePageHelper extends PageBase {

    @FindBy(className = "android.widget.TextView")
    WebElement searchField;

    @FindBy(xpath = "//*[@content-desc='My lists']")
    WebElement bookmarkIcon;

    //--- CTOR
    public PgHomePageHelper(WebDriver driver) {
        super(driver);
    }

    public String getSearchFieldPlaceholder() {
        waitUntilElementIsClickable(searchField, 30);
        return searchField.getText();
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
