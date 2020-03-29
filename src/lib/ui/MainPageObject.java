package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }

//Проверка, что результатов больше 1
    public void assertElementsPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements < 1) {
            String default_message = "An element '" + by.toString() + "' supposed to be present \n";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    //Ожидание появления элемента на странице с дефолтным таймаутом 5 сек
    public WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    //Ожидание появления элемента на странице с указанием таймаута
    public WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //Клик по элементу
    public WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    //Ввод текста
    public WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    //Очистка текста в инпуте
    public WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    //Проверка отсутствия элемента
    public boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    //Свайп снизу вверх
    public void swipeUp(int timeOfSwipe) {
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

    //Свайп снизу вверх с указанием скорости свайпа
    public void swipeUpQuick() {
        swipeUp(200);
    }

    //Свайп вверх с поиском элемента
    public void swipeUpToFindElement(By by, String error_message, int max_swipes) {
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

    //Свайп элемента влево
    public void swipeElementToLeft(By by, String error_message) {
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

    //Возвращает кол-во найденых элементов
    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    //Проверка, что элементы не найдены
    public void assertElementsNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    //Возвращает указанный атрибут элемента
    public String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

}
