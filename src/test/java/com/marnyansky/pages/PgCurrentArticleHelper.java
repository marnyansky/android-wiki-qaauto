package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgCurrentArticleHelper extends PageBase {

    private String article;

    @FindBy(id = "org.wikipedia:id/view_page_title_text")
    WebElement articleTitle;

    @FindBy(xpath = "//*[@content-desc='Add this article to a reading list']")
    WebElement bookmarkIcon;

    @FindBy(id = "org.wikipedia:id/onboarding_button")
    WebElement acceptButton;

    @FindBy(id = "org.wikipedia:id/text_input")
    WebElement listTitleField;

    @FindBy(id = "android:id/button1")
    WebElement submitButton;

    @FindBy(xpath = "//*[@content-desc='Navigate up']")
    WebElement navigateUpCrossButton;

    //--- CTOR
    public PgCurrentArticleHelper(WebDriver driver) {
        super(driver);
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public boolean verifyArticleTitle() {
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

