package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text,'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */

    //Изменение подстроки для поиска нужного заголовка
    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

//Клик и ожидание строки поиска
    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find and click search init element",
                10);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),
                "Cannot find search input after clicking search input element",
                5);
    }

    //Ввод текста в строку поиска
    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),
                search_line,
                "Cannot find and type into search input",
                10);
    }

    //Ожидание появления нужного результата
    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),
                "Cannot find search result with substring " + substring);
    }

    //Ожидание появления списка с результатами
    public void waitForSearchResult() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find search result with substring " );
    }

    //Поиск кнопки закрытия поиска
    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find search cancel button",
                10);
    }

    //Проверка, что кнопка закрытия поиска исчезла
    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),
                "Search cancel button is still present",
                10);
    }

    //Клик по пнопке закрытия поиска
    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),
                "Cannot find and click search cancel button",
                10);
    }

    //Клик по нужному заголовку среди результатов
    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),
                "Cannot find and click search result with substring " + substring ,
                10);
    }

    //Возвращает кол-во элементов в результатах поиска
    public int getAmountOfFoundArticles(){
        this.waitForElementPresent
                (
                        By.xpath(SEARCH_RESULT_ELEMENT),
                        "Cannot find anything by the request ",
                        15
                );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    //проверка наличия сообщения при отсутствии результатов
    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(
                By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15
        );
    }

    //Проверка, что результатов поиска нет
    public void assertThereIsNoResultOfSearch(){
        this.assertElementsNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "We supposed not find any results");
    }

    //Проверка, что есть хоть один результат поиска
    public void assertThereIsResultOfSearch(){
        this.assertElementsPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find search results");
    }

    //Извлечение текста плейсхолдера в строке поиска
    public String getArticleSearchPlaceholder() {
        WebElement title_element = this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find placeholder on page", 15);
        return title_element.getAttribute("text");
    }
}
