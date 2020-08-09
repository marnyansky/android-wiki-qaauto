package com.marnyansky.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgCurrentArticleHelper extends PageBase {

    private String article;

    @FindBy(id = "org.wikipedia:id/view_page_title_text")
    WebElement articleTitle;

    public PgCurrentArticleHelper(WebDriver driver) {
        super(driver);
    }

    public void setArticle(String article) {
        this.article = article;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsVisible(articleTitle, 30);
    }

    public boolean verifyArticleTitle() {
        return articleTitle.getText().equals(article);
    }
}

