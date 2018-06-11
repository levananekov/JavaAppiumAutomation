package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        capabilities.setCapability("orientation", "PORTRAIT");
        // PORTRAIT/LANDSCAPE  https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
//        вот беда оно переворачивает с ног на голову эмулятор если менять оринетацию эмулятора в ручну (ее не трогать)

        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
//      http://localhost:4723)
    }

    @Override
    protected void tearDown () throws Exception
    {
        driver.quit();

        super.tearDown();
    }

}