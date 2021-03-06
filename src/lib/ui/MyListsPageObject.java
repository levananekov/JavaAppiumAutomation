package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject
{
    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TMP,
            LABEL_BY_XPATH_TITLE_TMP;


    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}",name_of_folder);
    }

//    private static String getSavedArticleNameXpathTitle(String title_name)
//    {
//        return LABEL_BY_XPATH_TITLE_TMP.replace("{TITLE}",title);
//    }




    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name"+name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved by title" +article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getFolderXpathByName( article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article till present wish title"+ article_title,
                15
        );
    }


    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);

        this.swipeElementToLeft(
                article_xpath,
                "Cannot find saved article "+article_title
        );

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath,"Cannot find saved article IOS "+article_title);
        }
        System.out.println(article_xpath);

        this.waitForArticleToDisappearByTitle(article_title);
    }

}
