package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

abstract public class SearchPageObject extends MainPageObject{

     protected static String
        SEARCH_INIT_ELEMENT,
        SEARCH_INPUT,
        SEARCH_RESULT_BY_SUBSTRING_TPL,
        SEARCH_CANCEL_BUTTON,
        SEARCH_RESULT_ELEMENT,
        SEARCH_EMPTY_RESULT_ELEMENT,
        SEARCH_INPUT_TEXT,
        SEARCH_RESULT_CONTAINER,
        SEARCH_RESULT_WHERE_TITLE_AND_SUBSTRING_TPL;


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}",substring);
    }

    private static String SearchInputElement(String expected)
    {
        return SEARCH_INPUT_TEXT.replace("{SUBSTRING}",expected);
    }

    private static String getResultSearchTitleAndSubstring(String title, String substring) {
        return SEARCH_RESULT_WHERE_TITLE_AND_SUBSTRING_TPL.replace("{TITLE}", title).replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */


    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find click search init element", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT,"Cannot find search input after clicking search init element.");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON,"Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"Search cancel button is still present",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button.", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT,search_line,"Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(search_result_xpath,"Cannot find search result with substring"+substring);
    }

    public void clickByArticleWishSubtring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(search_result_xpath,"Cannot find and click search result with substring"+substring,10);
    }

    public int getAmountOfFoundArticls()
    {
        this.waitForElementPresent(SEARCH_RESULT_ELEMENT, "Cannot find anything by the request ", 15
        );
        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);
    }

    public void waitForEmptyResultsLable()
    {
        this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT,"Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any result.");
    }

    public void assertPresenceSearchFieldOnPage(String expected) {
        String expected_title = SearchInputElement(expected);
    this.assertPresenceTheElementTextByTitle(SEARCH_INIT_ELEMENT, "Cannot find 'Поиск по Википедии'change xpath", 5, "We see unexpected title" + expected_title, expected_title);
    }

    public void waitEmptyResultsAfterCancelSearch()
    {
        this.waitForElementNotPresent(SEARCH_RESULT_CONTAINER,"We see unexpected search result.",10);
    }

    public void assertPresenceItemTitleElement(String expected) {
        String expected_title = SearchInputElement(expected);
        this.assertPresenceTheElementTextByTitle(SEARCH_RESULT_CONTAINER, "Cannot find RESULT change xpath", 5, "We see unexpected title " + expected_title, expected_title);
    }

    public void waitForAllHeadlinesResultInPage() {
        String title_element_text = this.waitForElementAndGetAttribute(SEARCH_RESULT_CONTAINER, "text", "Cannot find title name get attribute text", 5);
        System.out.println(title_element_text);

//        String locator = SEARCH_RESULT_CONTAINER;
        By by = this.getLocatorByString(SEARCH_RESULT_CONTAINER);
        List<WebElement> article_title = driver.findElements(by);
        for (WebElement title : article_title) {
            String display_title = title.getAttribute("text");
//            System.out.println(display_title);
            assertTrue("Cannot find " + title_element_text + " title for each article.",
                    display_title.contains(title_element_text));
//            Метод contains() - сверяет с тем что в скобках(не строго)
        }
    }

    public void waitForElementByTitleAndDescription(String title, String substring)
        {
            this.waitForElementPresent(getResultSearchTitleAndSubstring(title, substring),
                    "Cannot find search result where title '" + title + "' and substring " + substring,
                    10);
//            поиск xpath 2х элементов заголовка и описания
        }
}

