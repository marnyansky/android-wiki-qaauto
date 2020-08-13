package com.marnyansky.tests;

import com.marnyansky.pages.PgHomePageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    private PgHomePageHelper homePage;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);
    }

    @Test(groups = {"smoke", "regression"})
    public void testOpenHomePage() {
        String expectedPlaceholder = "Search Wikipedia";

        Assert.assertEquals(homePage.getSearchFieldPlaceholder(),
                expectedPlaceholder,
                "\nActual placeholder doesn't correspond " +
                        "expected placeholder '" + expectedPlaceholder + "'\n");
    }

}
