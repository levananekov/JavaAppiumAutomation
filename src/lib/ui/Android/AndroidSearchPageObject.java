package lib.ui.Android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static {
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
                SEARCH_INPUT = "xpath://*[@text='Search…']";
                SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
                SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
                SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']/*[@resource-id = 'org.wikipedia:id/page_list_item_container']";
                SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text ='No results found']";
                SEARCH_INPUT_TEXT = "{SUBSTRING}";
                SEARCH_RESULT_CONTAINER = "id:org.wikipedia:id/page_list_item_title";
                SEARCH_RESULT_WHERE_TITLE_AND_SUBSTRING_TPL = "xpath://*[android.widget.LinearLayout/*[@index=0 and @text='{TITLE}'] and android.widget.LinearLayout/*[@index=1 and @text='{SUBSTRING}']]";
    }
    public AndroidSearchPageObject (AppiumDriver driver)
    {
        super(driver);
    }
}
