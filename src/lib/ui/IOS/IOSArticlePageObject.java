package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "id:Java (programming language)";
        TITLE_SECOND = "id:Python (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";

        TITLE_ARTICLE_ELEMENT = "id:org.wikipedia:id/view_page_title_text";
        FOLDER_BY_NAME_TPL = "xpath://*[@resource-id = 'org.wikipedia:id/list_of_lists']//*[@text='{FOLDER_NAME}']";
    }

    public IOSArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
