import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

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

    @Test
    public void testSearchAndCancelSearch()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Поиск']"),
                "Python",
                "Cannot find search input.",
                10
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find 'page_list_item_title' in title",
                10
        );

        int item_title = driver.findElements(By.id("org.wikipedia:id/page_list_item_title")).size();
        System.out.println(item_title);
        Assert.assertEquals(
                "Not found elements and it is no good)",
                (item_title >= 2),
                true
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5
        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "'page_list_item_title' is still present on the page.",
                5
        );

        System.out.println("testSearchAndCancelSearch - OK");
    }

    @Test
    public void testCompareAllHeadlines()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Поиск']"),
                "pentium",
                "Cannot find search input.",
                5
        );

        WebElement titile_element = waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find 'page_list_item_title' in title",
                10
        );

        assertPresenceOfElement(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find 'Pentium'please change id.",
                5,
                "We see unexpected value!",
                "Pentium"
        );

        List <WebElement> article_title = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
//        System.out.println("штук: " + article_title.size());
//        System.out.println("Название: " + titile_element.getText());

        String titile_element_text = titile_element.getText();
        for (WebElement title : article_title) {
            String display_title = title.getAttribute("text");
            Assert.assertTrue("Cannot find 'Pentium' title for each article.",
                    display_title.contains(titile_element_text));
//            Метод contains() - сверяет с тем что в скобках(не строго)
        }
    }


    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                "Appium",
                "Cannot find search input.",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id ='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' in search.",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find ArticleTitle",
                15
        );

        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article.",
                20
        );

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

    private void assertPresenceOfElement(By by, String error_message, long timeoutInSeconds, String assertMassage, String expected)
    {
        WebElement element = waitForElementPresent(by, error_message, 5);
        String article_title = element.getAttribute("text");
        Assert.assertEquals(
                assertMassage,
                expected,
                article_title);
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction  action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipUpQuick ()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swip = 0;
        while (driver.findElements(by).size() == 0){
        if (already_swip > max_swipes){
            waitForElementPresent(by, "Cannot find element by swipingUP. \n" + error_message, 0);
            return;
        }
        swipUpQuick();
        ++already_swip;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction  action = new TouchAction(driver);
        action.press(right_x,middle_y)
                .waitAction(300)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
    }


    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find search input.",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find ArticleTitle",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc= 'More options']"),
                        "Cannot find button to open article options",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "Cannot find options to add article to reading list ",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text= 'OK']"),
                "Cannot press 'ok' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close article, cannot find 'X' link.",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc= 'My lists']"),
                "Cannot find navigation button to My list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text= '"+name_of_folder+"']"),
                "Cannot find creation folder'Learning programming'",
                5
        );

        swipeElementToLeft(
                By.xpath("//*[@text= 'Java (programming language)']"),
                "Cannot find saved article "
        );

        waitForElementNotPresent(
                By.xpath("//*[@text= 'Java (programming language)']"),
                "Cannot delete  saved article ",
                5
        );


    }
}