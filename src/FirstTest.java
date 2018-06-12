import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import java.util.List;


public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    //    "//*[@text='Поиск']" - Строго.
//    "//*[contains(@text,'Поиск')]" - До первого совпадения.
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
        System.out.println("testCompareArticleTitle - OK");
    }

    @Test
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Appium");
        SearchPageObject.clickByArticleWishSubtring("Appium");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

        MainPageObject.swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article.",
                20
        );
    }

    @Test
    public void testAssertTitleTest() {
        MainPageObject.assertPresenceOfElement(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии'change xpath in newTest",
                5,
                "We see unexpected title assertTitleTest!",
                "Search Wikipedia"
        );
        System.out.println("assertTitleTest - OK");
    }

    @Test
    public void testSearchAndCancelSearch() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                "Python",
                "Cannot find search input.",
                10
        );

        MainPageObject.waitForElementPresent(
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

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find 'X' to cancel search",
                5
        );

        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "'page_list_item_title' is still present on the page.",
                5
        );

        System.out.println("testSearchAndCancelSearch - OK");
    }

    @Test
    public void testCompareAllHeadlines() {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                "pentium",
                "Cannot find search input.",
                5
        );

        WebElement titile_element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find 'page_list_item_title' in title",
                10
        );

        MainPageObject.assertPresenceOfElement(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find 'Pentium'please change id.",
                5,
                "We see unexpected value!",
                "Pentium"
        );

        List<WebElement> article_title = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
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
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.clouseArticle();

        NavigationUI NavigationUI = new NavigationUI (driver);
        NavigationUI.clickMyLists();

        MyListsPageObgect MyListsPageObgect = new MyListsPageObgect(driver);
        MyListsPageObgect.openFolderByName(name_of_folder);
        MyListsPageObgect.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testAmountNotEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results =SearchPageObject.getAmountOfFoundArticls();

        Assert.assertTrue(
                "We found too few result",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "fasdfasdfsd";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLable();
        SearchPageObject.assertThereIsNoResultOfSearch();


    }

    @Test
    public void testChangeOrientationOnSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();

        Assert.assertEquals("Article title have been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void saveSecondArticleToMyList()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                "Java",
                "Cannot find search input.",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' input.",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find ArticleTitle",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc= 'More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "Cannot find options to add article to reading list ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text= 'OK']"),
                "Cannot press 'ok' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close article, cannot find 'X' link.",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                "Python",
                "Cannot find search input.",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='General-purpose, high-level programming language']"),
                "Cannot find 'General-purpose, high-level programming language' input.",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'Python' ArticleTitle.",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc= 'More options']"),
                "Cannot find button to open article options",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text= 'Add to reading list']"),
                "Cannot find options to add article to reading list ",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/list_of_lists']//*[@text='"+ name_of_folder +"']"),
                "Cannot find 'General-purpose, high-level programming language' input.",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc= 'Navigate up']"),
                "Cannot close article, cannot find 'X' link.",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc= 'My lists']"),
                "Cannot find navigation button to My list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/item_container']//*[@text= '" + name_of_folder + "']"),
                "Cannot find creation folder'Learning programming'",
                5
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text= 'Java (programming language)']"),
                "Cannot find saved article 'Java (programming language)'"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text= 'Java (programming language)']"),
                "Cannot delete  saved article ",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Python (programming language)']"),
                "Cannot find saved article 'Python' in save folder",
                5

        );
    }

    @Test
    public void testAssertTitle()
    {
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Поиск по Википедии' input.",
                5
        );

        String article_title = "Java";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@text='Search…']"),
                article_title,
                "Cannot find search input.",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' input.",
                15
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find 'page_title_text' input",
                5);

        MainPageObject.assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find ArticleTitle"
        );
    }


}

