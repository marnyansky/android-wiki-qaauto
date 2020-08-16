package com.marnyansky.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public abstract class PageBase {

    AndroidDriver<AndroidElement> driver;

    //--- CTOR
    public PageBase(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
    }

    //--- Utilitarian methods
    public boolean elementTextIsCorrect(AndroidElement element, String text) {
        return element.getText().equals(text);
    }

    public void fillField(AndroidElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public void swipeLeft(AndroidElement element) {
        TouchAction<?> touchAction = new TouchAction<>(driver);

        //--- get element location, tap on the center of the element and swipe to the left
        int fromX = (int) (element.getLocation().x + element.getSize().width*0.5);
        int fromY = (int) (element.getLocation().y + element.getSize().height*0.5);
        int toX = (int) (element.getLocation().x + element.getSize().width*0.1);
        int toY = fromY;

        touchAction.press(PointOption.point(fromX, fromY))
                .waitAction()
                .moveTo(PointOption.point(toX, toY))
                .release()
                .perform();
    }

    public void rotateScreenLandscape() {
        driver.rotate(ScreenOrientation.LANDSCAPE);

    }

    public void rotateScreenPortrait() {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    public void runInBackground(int seconds) {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }

    public void randomFun() {
        System.out.println("The random fun begins!");
        for (int i = 0; i < 5; i++) { //--- maximum 5 random actions to prevent android emulator from freezing
            int actionId = randomInt(3); //--- picks one of three possible actions
            switch (actionId) {
                case 0:
                    driver.runAppInBackground(Duration.ofSeconds(randomInt(5)));
                    System.out.println("Action " + actionId + " >>> Penguin hides in the snow");
                    break;
                case 1:
                    driver.rotate(ScreenOrientation.PORTRAIT);
                    System.out.println("Action " + actionId + " >>> Penguin stands still!");
                    break;
                case 2:
                    driver.rotate(ScreenOrientation.LANDSCAPE);
                    System.out.println("Action " + actionId + " >>> Penguin sleeps...ZZZzzz...");
                    break;
                default:
                    throw new IllegalArgumentException("ERROR! actionId value is " + actionId);
            }
        }
        System.out.println("=== END OF TEST ===");
    }

    private int randomInt(int bound) {
        return new Random(System.currentTimeMillis()).nextInt(bound);
    }

    //--- WebDriverWait methods
    public void waitUntilElementIsClickable(AndroidElement element, int timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(AndroidElement element, int timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elementList, int timeout) {
        try {
            new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfAllElements(elementList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
