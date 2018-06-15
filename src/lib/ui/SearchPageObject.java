package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class SearchPageObject extends MainPageObject{

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[@text='Search…']",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text ='No results found']",
        SEARCH_INPUT_TEXT = "{SUBSTRING}",
        SEARCH_RESULT_CONTAINER ="org.wikipedia:id/page_list_item_title",
        SEARCH_RESULT_WHERE_TITLE_AND_SUBSTRING_TPL = "//*[android.widget.LinearLayout/*[@index=0 and @text='{TITLE}'] and android.widget.LinearLayout/*[@index=1 and @text='{SUBSTRING}']]";


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
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find click search init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find search input after clicking search init element.");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find search cancel button",5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search cancel button is still present",5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button.", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),search_line,"Cannot find and type into search input",5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring"+substring);
    }

    public void clickByArticleWishSubtring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring"+substring,10);
    }

    public int getAmountOfFoundArticls()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request ", 15
        );
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLable()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT),"Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any result.");
    }

    public void assertPresenceSearchFieldOnPage(String expected) {
        String expected_title = SearchInputElement(expected);
    this.assertPresenceTheElementTextByTitle(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find 'Поиск по Википедии'change xpath", 5, "We see unexpected title" + expected_title, expected_title);
    }

    public void waitEmptyResultsAfterCancelSearch()
    {
        this.waitForElementNotPresent(By.id(SEARCH_RESULT_CONTAINER),"We see unexpected search result.",10);
    }

    public void assertPresenceItemTitleElement(String expected) {
        String expected_title = SearchInputElement(expected);
        this.assertPresenceTheElementTextByTitle(By.id(SEARCH_RESULT_CONTAINER), "Cannot find RESULT change xpath", 5, "We see unexpected title " + expected_title, expected_title);
    }

    public void waitForAllHeadlinesResultInPage() {
        String title_element_text = this.waitForElementAndGetAttribute(By.id(SEARCH_RESULT_CONTAINER), "text", "Cannot find title name get attribute text", 5);
//        System.out.println(title_element_text);
        List<WebElement> article_title = driver.findElements(By.id(SEARCH_RESULT_CONTAINER));
//        String title_element_text = title_element.getText();

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
            this.waitForElementPresent(By.xpath(getResultSearchTitleAndSubstring(title, substring)),
                    "Cannot find search result where title '" + title + "' and substring " + substring,
                    10);
//            поиск xpath 2х элементов заголовка и описания
        }
}

