package com.marnyansky.pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class PgCurrentArticleHelper extends PageBase {

    private String article;

    @AndroidFindBy(id = "org.wikipedia:id/view_page_title_text")
    private AndroidElement articleTitle;

    @AndroidFindBy(xpath = "//*[@content-desc='Add this article to a reading list']")
    private AndroidElement bookmarkIcon;

    @AndroidFindBy(id = "org.wikipedia:id/onboarding_button")
    private AndroidElement acceptButton;

    @AndroidFindBy(id = "org.wikipedia:id/text_input")
    private AndroidElement listTitleField;

    @AndroidFindBy(id = "android:id/button1")
    private AndroidElement submitButton;

    @AndroidFindBy(xpath = "//*[@content-desc='Navigate up']")
    private AndroidElement navigateUpCrossButton;

    //--- CTOR
    public PgCurrentArticleHelper(AndroidDriver<AndroidElement> driver, String article) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.article = article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public boolean articleTitleIsCorrect() {
        waitUntilElementIsVisible(articleTitle, 30);
        return articleTitle.getText().equals(article);
    }

    public void navigateToHomePage() {
        waitUntilElementIsClickable(navigateUpCrossButton, 15);
        navigateUpCrossButton.click();
    }

    public PgCurrentArticleHelper createNewReadingListAndAddArticle(String listTitle) {
        waitUntilElementIsClickable(bookmarkIcon, 30);
        bookmarkIcon.click();
        waitUntilElementIsClickable(acceptButton, 15);
        acceptButton.click();
        waitUntilElementIsClickable(listTitleField, 15);
        fillField(listTitleField, listTitle);
        submitButton.click();

        return this;
    }
}

