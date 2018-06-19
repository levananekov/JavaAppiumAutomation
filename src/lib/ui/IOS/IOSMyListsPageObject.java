package lib.ui.IOS;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject {

    static {
//        FOLDER_BY_NAME_TPL = "xpath://XCUIElementTypeLink[@name='Java (programming language) Object-oriented programming language']";
        FOLDER_BY_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{FOLDER_NAME}')]";

    }

    public IOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
