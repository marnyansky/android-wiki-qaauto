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
        return articleTitle.getText().equals(article);
    }

    public void bookmarkArticle() {
        bookmarkIcon.click();
    }

    public void navigateToHomePage() {
        navigateUpCrossButton.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(articleTitle, 30);
        waitUntilElementIsClickable(navigateUpCrossButton, 30);
    }

}

