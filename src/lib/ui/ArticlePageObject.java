package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import lib.Platform;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
    TITLE,
    FOOTER_ELEMENT,
    OPTIONS_BUTTON,
    OPTIONS_ADD_TO_MY_LIST_BUTTON,
    ADD_TO_MY_LIST_OVERLAY,
    MY_LIST_NAME_INPUT,
    MY_LIST_OK_BUTTON,
    CLOSE_ARTICLE_BUTTON,
    MY_LIST_EXISTING_NAME_TPL;


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
    /* TEMPLATES METHODS */

    //Изменение подстроки для поиска нужного заголовка
    private static String getMyListExistingName(String substring) {
        return MY_LIST_EXISTING_NAME_TPL.replace("{NAME_OF_FOLDER}", substring);
    }
    /* TEMPLATES METHODS */

    //Ожидание появления тайтла
    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    //Возвращает текст тайтла
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        }else {
            return title_element.getAttribute("name");
        }

    }

    public void swipeUpTillElementAppear(String locator, String error_message, int max_swipes){
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator)){
            if (already_swiped > max_swipes){
                Assert.assertTrue(error_message, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator){
        int element_location_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 15).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_location_by_y < screen_size_by_y;
    }
    //Свайпает до футера
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT,"Cannot find the end of article", 40);
        }
    }

    //Добавление статьи в Мой лист
    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick
                (
                        OPTIONS_BUTTON,
                        "Cannot find button to open article options",
                        5
                );
        this.waitForElementAndClick
                (
                        OPTIONS_ADD_TO_MY_LIST_BUTTON,
                        "Cannot find option to add article to reading list",
                        5
                );
        this.waitForElementAndClick
                (
                        ADD_TO_MY_LIST_OVERLAY,
                        "Cannot find 'Go it' tip overlay",
                        5
                );
        this.waitForElementAndClear
                (
                        MY_LIST_NAME_INPUT,
                        "Cannot find input to set name of articles folder",
                        5
                );
        this.waitForElementAndSendKeys
                (
                        MY_LIST_NAME_INPUT,
                        name_of_folder,
                        "Cannot put into articles folder input",
                        5
                );
        this.waitForElementAndClick
                (
                        MY_LIST_OK_BUTTON,
                        "Cannot find 'OK' button",
                        5
                );
    }

    //сохранение статьи в
    public void addArticleToMyExistingList(String substring) {
        this.waitForElementAndClick
                (
                        OPTIONS_BUTTON,
                        "Cannot find button to open article options",
                        5
                );
        this.waitForElementAndClick
                (
                        OPTIONS_ADD_TO_MY_LIST_BUTTON,
                        "Cannot find option to add article to reading list",
                        5
                );
        String name_of_folder_xpath = getMyListExistingName(substring);
        this.waitForElementAndClick(
                name_of_folder_xpath,
                "Cannot find '" + substring + "' list name",
                10
        );
    }

    //Клик на закрытие статьи
    public void closeArticle() {
        this.waitForElementAndClick
                (
                        CLOSE_ARTICLE_BUTTON,
                        "Cannot find article, cannot find X link",
                        5
                );
    }

    //Проверяет наличие тайтла без ожидания
    public void assertTitleNotWaiting() {
        this.assertElementsPresent(TITLE,
                "We supposed not find any results");
    }

    public void addArticleToMySaved(){
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 10);
    }
}
