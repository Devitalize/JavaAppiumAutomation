import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/home/pbx/Рабочий стол/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void testDown() {
        driver.quit();
    }


    //
//    @Test
//    public void findSearchPlaceholder() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//        WebElement placeholder = waitForElementPresent
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        "Cannot find 'Search...' placeholder on the screen",
//                        5
//                );
//
//        String actual_placeholder = placeholder.getAttribute("text");
//
//        Assert.assertEquals
//                (
//                        "Placeholder 'Search...' does not present on the screen",
//                        "Search…",
//                        actual_placeholder
//                );
//    }
//


    @Test
    public void cancelSearch() {
        waitForElementAndClick
                (
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia button",
                        5
                );

        waitForElementAndSendKeys
                (
                        By.xpath("//*[contains(@text,'Search…')]"),
                        "Test",
                        "'Search...' is not found ",
                        5
                );
        waitForElementPresent
                (
                        By.id("org.wikipedia:id/search_results_list"),
                        "List result is not found",
                        5
                );

        waitForElementAndClick
                (
                        By.id("org.wikipedia:id/search_close_btn"),
                        "Cannot find X(close) button",
                        5
                );

        waitForElementNotPresent
                (
                        By.id("org.wikipedia:id/search_results_list"),
                        "List result is still present on the page",
                        5
                );

    }

    private WebElement waitForElementPresent(By by, String error_mesage) {
        return waitForElementPresent(by, error_mesage, 5);
    }

    private WebElement waitForElementPresent(By by, String error_mesage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_mesage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_mesage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_mesage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_mesage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_mesage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }


}


