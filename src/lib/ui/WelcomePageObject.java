package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{

    private static final String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started",
    SKIP = "id:Skip";







    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void wiatForLearnMoreLinc()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK,
                "Cannot find by 'Learn more about Wikipedia' linc ",
                5);
    }

    public void wiatForNewWaysToExploreText()
    {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find by 'New ways to explore' linc",
                5);
    }

    public void wiatForAddOrEditPreferredLanguagesText()
    {
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
                "Cannot find by 'Add or edit preferred languages linc'",
                5);
    }

    public void wiatForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find by 'Learn more about data collected'",
                5);
    }

    public void clickNextButtom()
    {
        this.waitForElementAndClick(NEXT_LINK,
                "Cannot find and click 'Next' linc ",
                5);
    }

    public void clickClickGetStaredButtom()
    {
        this.waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find and click 'Get started' linc ",
                5);
    }

    public void clickSkip()
    {
        this.waitForElementAndClick(SKIP,
                "Cannot find and click skip button",
                5);
    }
}
