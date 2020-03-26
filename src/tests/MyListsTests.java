package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToMyList(name_of_folder);

        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);

        MyListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void saveTwoArticlesInMyListAndDelete() {
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
        MainPageObject.waitForElementPresent
                (
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Cannot find article title.",
                        15
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                        "Cannot find button to open article options",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@text='Add to reading list']"),
                        "Cannot find option to add article to reading list",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.id("org.wikipedia:id/onboarding_button"),
                        "Cannot find 'Go it' tip overlay",
                        5
                );
        MainPageObject.waitForElementAndClear
                (
                        By.id("org.wikipedia:id/text_input"),
                        "Cannot find input to set name of articles folder",
                        5
                );

        String name_of_folder = "Learning programming";
        MainPageObject.waitForElementAndSendKeys
                (
                        By.id("org.wikipedia:id/text_input"),
                        name_of_folder,
                        "Cannot put into articles folder input",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@text='OK']"),
                        "Cannot find 'OK' button",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//*[@class='android.widget.ImageButton']"),
                        "Cannot find article, cannot find X link",
                        20
                );

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
                        By.xpath("//*[@text='JavaScript']"),
                        "Cannot find 'JavaScript' title",
                        5
                );
        MainPageObject.waitForElementPresent
                (
                        By.id("org.wikipedia:id/view_page_title_text"),
                        "Cannot find article title.",
                        15
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                        "Cannot find button to open article options",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@text='Add to reading list']"),
                        "Cannot find option to add article to reading list",
                        5
                );
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' list name",
                10
        );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar']//android.widget.ImageButton[@content-desc='Navigate up']"),
                        "Cannot find article, cannot find X link",
                        20
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                        "Cannot find navigation button to My list",
                        5
                );
        MainPageObject.waitForElementAndClick
                (
                        By.xpath("//*[@text='" + name_of_folder + "']"),
                        "Cannot find created folder",
                        10
                );
        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete save article",
                5
        );
    }
}
