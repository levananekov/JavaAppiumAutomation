package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle() {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
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

        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testAssertTitleTest() {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);
        SearchPageObject.assertPresenceSearchFieldOnPage("Search Wikipedia");
    }

    @Test
    public void testCompareAllHeadlines() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.assertPresenceItemTitleElement("Python");
        SearchPageObject.waitForAllHeadlinesResultInPage();
    }

    @Test
    public void testAssertTitle() {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
//        ArticlePageObject.waitForTitleElement(); - Для проверки работоспособности.
        ArticlePageObject.assertTitleElementPresent();
    }
}
