package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    public AppiumDriver getDriver() throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()){
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }else if (this.isIOS()){
            return new IOSDriver(URL,this.getIOSDesiredCapabilities());
        }else throw new Exception("Cannot detect type of the Driver. Platform value " +this.getPlatformVar());
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }


    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");
        // PORTRAIT/LANDSCAPE  https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iPhone 6 Plus");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app", "/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        // PORTRAIT/LANDSCAPE  https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/caps.md

        return capabilities;
    }

    private boolean isPlatform(String my_platform)
    {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");

    }

}
