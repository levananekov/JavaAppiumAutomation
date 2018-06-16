package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class IOSTestCase extends TestCase {

    protected AppiumDriver driver;

    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {

        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iPhone 6 Plus");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app", "/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        capabilities.setCapability("orientation", "PORTRAIT");
        // PORTRAIT/LANDSCAPE  https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
//        вот беда оно переворачивает с ног на голову эмулятор если менять оринетацию эмулятора в ручну (ее не трогать)

        driver = new IOSDriver(new URL(AppiumURL), capabilities);
//      http://localhost:4723)
//
//
//      this.rotateScreenPortrait(); - чет сомнительно, а надо ли это, тоже после запуска переход в портрет
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


}
