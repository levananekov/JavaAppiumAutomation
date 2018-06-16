package tests.IOS;

import lib.IOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends IOSTestCase {

    @Test
    public void testPassThroughWelcome()
    {
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
