package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@value='Search Wikipedia']";
        SEARCH_CANCEL_BUTTON = "id:Close";
//        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath:XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_INPUT_TEXT = "{SUBSTRING}";
        SEARCH_RESULT_CONTAINER = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULT_WHERE_TITLE_AND_SUBSTRING_TPL ="xpath://XCUIElementTypeLink[@name='{TITLE}\n{SUBSTRING}']";
        CLEAR_SEARCH_INPUT_WITH_TEXT = "id:clear mini";

    }

    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
