package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
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
    }

    @Test
    public void testAssertTitleTest() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.assertPresenceSearchFieldOnPage("Search Wikipedia");
    }

    @Test
    public void testCompareAllHeadlines() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.assertPresenceItemTitleElement("Python");
        SearchPageObject.waitForAllHeadlinesResultInPage();
    }

    @Test
    public void testAssertTitle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
//        ArticlePageObject.waitForTitleElement(); - Для проверки работоспособности.
        ArticlePageObject.assertTitleElementPresent();
    }
}
