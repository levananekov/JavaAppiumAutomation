package tests;

import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    public void testPassThroughWelcome()
    {
        if (this.Platform.isAndroid()){
            return;
        }
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.wiatForLearnMoreLinc();
        WelcomePage.clickNextButtom();

        WelcomePage.wiatForNewWaysToExploreText();
        WelcomePage.clickNextButtom();

        WelcomePage.wiatForAddOrEditPreferredLanguagesText();
        WelcomePage.clickNextButtom();

        WelcomePage.wiatForLearnMoreAboutDataCollectedText();
        WelcomePage.clickClickGetStaredButtom();

    }
}
