package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEny();
        driver = new AndroidDriver(new URL(AppiumURL), capabilities);
      this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown () throws Exception
    {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(int seconds)
    {
        driver.runAppInBackground(seconds);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEny() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "5.1");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("app", "/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
            // PORTRAIT/LANDSCAPE  https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
//
        }else if (platform.equals(PLATFORM_IOS)) {

            capabilities.setCapability("platformName", "IOS");
            capabilities.setCapability("deviceName", "iPhone 6 Plus");
            capabilities.setCapability("platformVersion", "11.4");
            capabilities.setCapability("app", "/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
            // PORTRAIT/LANDSCAPE  https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
        }else {
            throw new Exception("Cannot get run platform from eny variable. Platform value"+ platform);
        }

        return capabilities;
    }


}
