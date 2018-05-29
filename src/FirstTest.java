import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {


    private AppiumDriver driver;

    @Before
    public void setUp ()throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","5.1");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/levananenkov/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//      http://localhost:4723)
    }


    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Поиск']"),
                "Java",
                "Cannot find search input.",
                5
        );

//    "//*[@text='Поиск']" - Строго.
//    "//*[contains(@text,'Поиск')]" - До первого совпадения.

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Язык программирования']"),
                "Cannot find 'Язык программирования' topic searching by 'Java'.",
                15
        );
        System.out.println("First test - OK");
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Поиск']"),
                "Java",
                "Cannot find search input.",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field.",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "'X' is still present on the page.",
                5
        );

        System.out.println("testCancelSearch - OK");
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Поиск']"),
                "Java",
                "Cannot find search input.",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Язык программирования']"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        WebElement titile_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find ArticleTitle",
                15
        );

        String article_title = titile_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java",
                article_title
        );
        System.out.println("testCompareArticleTitle - OK");
    }

    @Test
    public void assertTitleTest()
    {
        assertPresenceOfElement(
                By.xpath("//*[contains(@text,'Поиск по Википедии')]"),
                "Cannot find 'Поиск по Википедии'change xpath in newTest",
                5,
                "We see unexpected title assertTitleTest!",
                "Поиск по Википедии"
        );
        System.out.println("assertTitleTest - OK");
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by,error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear (By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }


    private void assertPresenceOfElement(By by, String error_message, long timeoutInSeconds, String assertMassage, String expected  )
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String article_title = element.getAttribute("text");
        Assert.assertEquals(
                assertMassage,
                expected,
                article_title);
    }

}