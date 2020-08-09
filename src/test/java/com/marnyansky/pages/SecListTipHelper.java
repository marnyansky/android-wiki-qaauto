package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecListTipHelper extends PageBase {

    @FindBy(id = "org.wikipedia:id/onboarding_button")
    WebElement acceptButton;

    //--- CTOR
    public SecListTipHelper(WebDriver driver) {
        super(driver);
    }

    public void clickAcceptButton() {
        acceptButton.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(acceptButton, 15);
    }

}
