package com.marnyansky.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.marnyansky.SuiteConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

public abstract class TestBase {

    protected static URL gridHubUrl = null;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    protected AppiumDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void initTestSuite() throws IOException {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
            gridHubUrl = new URL(config.getProperty("grid.url"));
        }
        capabilities = config.getCapabilities();
    }

    @BeforeMethod(alwaysRun = true)
    public void initWebDriver() throws MalformedURLException {
        //---Currently unable to use gridHubUrl variable instead of explicit URL
        driver = new AndroidDriver(new URL(baseUrl), capabilities);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
