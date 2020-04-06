package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
    CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
    MY_LIST_EXISTING_NAME_TPL = "xpath://*[@text='{NAME_OF_FOLDER}']";


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
        return title_element.getAttribute("text");
    }

    //Свайпает до футера
    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
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
    public void closeArticle(){
        this.waitForElementAndClick
                (
                        CLOSE_ARTICLE_BUTTON,
                        "Cannot find article, cannot find X link",
                        5
                );
    }

    //Проверяет наличие тайтла без ожидания
    public void assertTitleNotWaiting(){
        this.assertElementsPresent(TITLE,
                "We supposed not find any results");
    }
}
