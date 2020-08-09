package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgHomePageHelper extends PageBase {

    @FindBy(className = "android.widget.TextView")
    WebElement searchField;

    public PgHomePageHelper(WebDriver driver) {
        super(driver);
    }

    public String getSearchFieldPlaceholder() {
        return searchField.getText();
    }

    public void openSearchPage() {
        searchField.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(searchField, 15);
    }

}
