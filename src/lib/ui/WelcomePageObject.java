package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject
{

    private static final String
    STEP_LEARN_MORE_LINK = "Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "New ways to explore",
    STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK = "Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "Learn more about data collected",
    NEXT_LINK = "Next",
    GET_STARTED_BUTTON = "Get started";







    public WelcomePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void wiatForLearnMoreLinc()
    {
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_LINK),
                "Cannot find by 'Learn more about Wikipedia' linc ",
                5);
    }

    public void wiatForNewWaysToExploreText()
    {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE_TEXT),
                "Cannot find by 'New ways to explore' linc",
                5);
    }

    public void wiatForAddOrEditPreferredLanguagesText()
    {
        this.waitForElementPresent(By.id(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK),
                "Cannot find by 'Add or edit preferred languages linc'",
                5);
    }

    public void wiatForLearnMoreAboutDataCollectedText()
    {
        this.waitForElementPresent(By.id(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK),
                "Cannot find by 'Learn more about data collected'",
                5);
    }

    public void clickNextButtom()
    {
        this.waitForElementAndClick(By.id(NEXT_LINK),
                "Cannot find and click 'Next' linc ",
                5);
    }

    public void clickClickGetStaredButtom()
    {
        this.waitForElementAndClick(By.id(GET_STARTED_BUTTON),
                "Cannot find and click 'Get started' linc ",
                5);
    }
}
