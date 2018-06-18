package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndriodArticlePageObject;
import lib.ui.ArticlePageObject;
import lib.ui.IOS.IOSArticlePageObject;

public class ArticlePageObjectFactory
{
    public static ArticlePageObject get(AppiumDriver driver)
    {
        if (Platform.getInstance().isAndroid()){
            return new AndriodArticlePageObject(driver);
        }else {
            return new IOSArticlePageObject(driver);
        }
    }
}
