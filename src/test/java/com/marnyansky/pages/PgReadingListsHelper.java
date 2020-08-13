package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgReadingListsHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/item_container")
    WebElement articleReadingList;

    //--- CTOR
    public PgReadingListsHelper(WebDriver driver) {
        super(driver);
    }

    public void openReadingList() {
        waitUntilElementIsClickable(articleReadingList, 15);
        articleReadingList.click();
    }

}
