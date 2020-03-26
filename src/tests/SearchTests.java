package tests;

import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase {
    @Test
    public void testFindSearchPlaceholder() {
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia button",
                        5
                );
        WebElement placeholder = MainPageObject.waitForElementPresent
                (
                        By.xpath("//*[contains(@text,'Search…')]"),
                        "Cannot find 'Search...' placeholder on the screen",
                        5
                );

        String actual_placeholder = placeholder.getAttribute("text");

        assertEquals
                (
                        "Placeholder 'Search...' does not present on the screen",
                        "Search…",
                        actual_placeholder
                );
    }

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Discography";
        SearchPageObject.typeSearchLine(search_line);

        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();
        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        String search_line = "dfbdrfhbytejdhy";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testAssertTitleNotWaiting() {
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                        "Cannot find Search Wikipedia button",
                        5
                );

        MainPageObject.waitForElementAndSendKeys
                (
                        By.xpath("//*[contains(@text,'Search…')]"),
                        "Java",
                        "'Search...' is not found ",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@text='Object-oriented programming language']"),
                        "Cannot find Search Wikipedia input",
                        5
                );
        MainPageObject.assertElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
                "Cannot find title of article"
        );
    }
}
