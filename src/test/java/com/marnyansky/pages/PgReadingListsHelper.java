package com.marnyansky.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PgReadingListsHelper extends PageBase {

    @AndroidFindBy(id = "org.wikipedia:id/item_container")
    private AndroidElement articleReadingList;

    //--- CTOR
    public PgReadingListsHelper(AndroidDriver<AndroidElement> driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void openReadingList() {
        waitUntilElementIsClickable(articleReadingList, 15);
        articleReadingList.click();
    }

}
