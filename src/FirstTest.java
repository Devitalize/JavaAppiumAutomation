import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
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
        capabilities.setCapability("app", "C:/Users/888/Desktop/course/JavaAppiumAutomation/apks/org.wikipedia.apk");

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
//
//    }
//
//
//
//    @Test
//    public void cancelSearch() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        "Test",
//                        "'Search...' is not found ",
//                        5
//                );
//        waitForElementPresent
//                (
//                        By.id("org.wikipedia:id/search_results_list"),
//                        "List result is not found",
//                        5
//                );
//
//        waitForElementAndClick
//                (
//                        By.id("org.wikipedia:id/search_close_btn"),
//                        "Cannot find X(close) button",
//                        5
//                );
//
//        waitForElementNotPresent
//                (
//                        By.id("org.wikipedia:id/search_results_list"),
//                        "List result is still present on the page",
//                        5
//                );
//
//    }
//
//    @Test
//    public void testSwipeArticle()
//    {
//        waitForElementAndClick(
//                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                "Cannot find Search Wikipedia input",
//                5
//        );
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        "Appium",
//                        "'Search...' is not found ",
//                        5
//                );
//        waitForElementAndClick(
//                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
//                "Cannot find 'Appium' article in search",
//                5
//        );
//        swipeUpToFindElement(
//                By.xpath("//*[@text='View page in browser']"),
//                "'View page in browser' is not found",
//                20
//        );
//    }
//
//    @Test
//    public void saveFirstArticleToMyList() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//        waitForElementPresent
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        "Cannot find 'Search...' placeholder on the screen",
//                        5
//                );
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        "Java",
//                        "'Search...' is not found ",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//*[@text='Object-oriented programming language']"),
//                        "Cannot find Search Wikipedia input",
//                        5
//                );
//        waitForElementPresent
//                (
//                        By.id("org.wikipedia:id/view_page_title_text"),
//                        "Cannot find article title.",
//                        15
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
//                        "Cannot find button to open article options",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//*[@text='Add to reading list']"),
//                        "Cannot find option to add article to reading list",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.id("org.wikipedia:id/onboarding_button"),
//                        "Cannot find 'Go it' tip overlay",
//                        5
//                );
//        waitForElementAndClear
//                (
//                        By.id("org.wikipedia:id/text_input"),
//                        "Cannot find input to set name of articles folder",
//                        5
//                );
//
//        String name_of_folder = "Learning programming";
//        waitForElementAndSendKeys
//                (
//                        By.id("org.wikipedia:id/text_input"),
//                        name_of_folder,
//                        "Cannot put into articles folder input",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//*[@text='OK']"),
//                        "Cannot find 'OK' button",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
//                        "Cannot find article, cannot find X link",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
//                        "Cannot find navigation button to My list",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//*[@text='" + name_of_folder + "']"),
//                        "Cannot find created folder",
//                        10
//                );
//        swipeElementToLeft(
//                By.xpath("//*[@text='Java (programming language)']"),
//                "Cannot find saved article"
//        );
//        waitForElementNotPresent(
//                By.xpath("//*[@text='Java (programming language)']"),
//                "Cannot delete save article",
//                5
//        );
//
//    }
//
//    @Test
//    public void testAmountOfNotEmptySearch() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//
//        String search_line = "Linkin Park Discography";
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        search_line,
//                        "'Search...' is not found ",
//                        5
//                );
//
//        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
//        waitForElementPresent
//                (
//                        By.xpath(search_result_locator),
//                        "Cannot find anything by the request " + search_line,
//                        15
//                );
//        int amount_of_search_results = getAmountOfElements(
//                By.xpath(search_result_locator)
//        );
//        Assert.assertTrue(
//                "We found too few results",
//                amount_of_search_results > 0
//        );
//    }
//
//    @Test
//    public void testAmountOfEmptySearch() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//
//        String search_line = "gfcyhtftyyhuik";
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        search_line,
//                        "'Search...' is not found ",
//                        15
//                );
//
//        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
//        String empty_resoult_label = "//*[@text='No results found']";
//
//        waitForElementPresent(
//                By.xpath(empty_resoult_label),
//                "Cannot find empty result label by the request" + search_line,
//                15
//        );
//
//        assertElementsNotPresent(
//                By.xpath(search_result_locator),
//                "We've found some results by request " + search_line
//        );
//
//    }
//
//    @Test
//    public void testChangeScreenOrientationOnSearchResults() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//
//        String search_line = "Java";
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        search_line,
//                        "'Search...' input is not found ",
//                        5
//                );
//        waitForElementAndClick
//                (
//                        By.xpath("//*[@text='Object-oriented programming language']"),
//                        "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
//                        15
//                );
//
//        String title_before_rotation = waitForElementAndGetAttribute(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "text",
//                "Cannot find title of article",
//                15
//        );
//
//        driver.rotate(ScreenOrientation.LANDSCAPE);
//
//        String title_after_rotation = waitForElementAndGetAttribute(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "text",
//                "Cannot find title of article",
//                15
//        );
//        Assert.assertEquals(
//                "Article title have been changed after screen rotation",
//                title_before_rotation,
//                title_after_rotation
//        );
//        driver.rotate(ScreenOrientation.PORTRAIT);
//
//        String title_after_second_rotation = waitForElementAndGetAttribute(
//                By.id("org.wikipedia:id/view_page_title_text"),
//                "text",
//                "Cannot find title of article",
//                15
//        );
//        Assert.assertEquals(
//                "Article title have been changed after screen rotation",
//                title_before_rotation,
//                title_after_second_rotation
//        );
//    }
//
//    @Test
//    public void testCheckSearchArticleBackground() {
//        waitForElementAndClick
//                (
//                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
//                        "Cannot find Search Wikipedia button",
//                        5
//                );
//
//        String search_line = "Java";
//        waitForElementAndSendKeys
//                (
//                        By.xpath("//*[contains(@text,'Search…')]"),
//                        search_line,
//                        "'Search...' input is not found ",
//                        5
//                );
//        waitForElementPresent
//                (
//                        By.xpath("//*[@text='Object-oriented programming language']"),
//                        "Cannot find 'Object-oriented programming language' topic searching by" + search_line,
//                        15
//                );
//        driver.runAppInBackground(2);
//
//        waitForElementPresent
//                (
//                        By.xpath("//*[@text='Object-oriented programming language']"),
//                        "Cannot find 'Object-oriented programming language' after returning from background",
//                        15
//                );
//
//    }

    @Test
    public void saveTwoArticlesInMyListAndDelete() {
        waitForElementAndClick
                (
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia button",
                        5
                );

        waitForElementAndSendKeys
                (
                        By.xpath("//*[contains(@text,'Search…')]"),
                        "Java",
                        "'Search...' is not found ",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//*[@text='Object-oriented programming language']"),
                        "Cannot find Search Wikipedia input",
                        5
                );
        waitForElementPresent
                (
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Cannot find article title.",
                        15
                );
        waitForElementAndClick
                (
                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                        "Cannot find button to open article options",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//*[@text='Add to reading list']"),
                        "Cannot find option to add article to reading list",
                        5
                );
        waitForElementAndClick
                (
                        By.id("org.wikipedia:id/onboarding_button"),
                        "Cannot find 'Go it' tip overlay",
                        5
                );
        waitForElementAndClear
                (
                        By.id("org.wikipedia:id/text_input"),
                        "Cannot find input to set name of articles folder",
                        5
                );

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys
                (
                        By.id("org.wikipedia:id/text_input"),
                        name_of_folder,
                        "Cannot put into articles folder input",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//*[@text='OK']"),
                        "Cannot find 'OK' button",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                        "Cannot find article, cannot find X link",
                        15
                );

        waitForElementAndClick
                (
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia button",
                        5
                );

        waitForElementAndSendKeys
                (
                        By.xpath("//*[contains(@text,'Search…')]"),
                        "Java",
                        "'Search...' is not found ",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//*[@text='JavaScript']"),
                        "Cannot find 'JavaScript' title",
                        5
                );
        waitForElementPresent
                (
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Cannot find article title.",
                        15
                );
        waitForElementAndClick
                (
                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                        "Cannot find button to open article options",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//*[@text='Add to reading list']"),
                        "Cannot find option to add article to reading list",
                        5
                );
        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' list name",
                10
        );
        waitForElementAndClick
                (
                        By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                        "Cannot find article, cannot find X link",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                        "Cannot find navigation button to My list",
                        5
                );
        waitForElementAndClick
                (
                        By.xpath("//*[@text='" + name_of_folder + "']"),
                        "Cannot find created folder",
                        10
                );
        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete save article",
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

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_mesage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_mesage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release().perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swipes = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swipes > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n " + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swipes;
        }
    }

    protected void swipeElementToLeft(By by, String error_message) {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(350)
                .moveTo(left_x, middle_y)
                .release().perform();
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementsNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }
}


