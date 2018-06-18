package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObgect;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
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
    public void testSaveSecondArticleToMyList() {
//        addArticleToMyList

        SearchPageObject SearchPageObject =  SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWishSubtring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String article_title_second = "Python (programming language)";
        System.out.println(article_title);
        String name_of_folder ="Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.clouseArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        SearchPageObject.clickByArticleWishSubtring("General-purpose, high-level programming language");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToMyListAgain(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObgect MyListsPageObgect = new MyListsPageObgect(driver);
        MyListsPageObgect.openFolderByName(name_of_folder);
        MyListsPageObgect.swipeByArticleToDelete(article_title);
        MyListsPageObgect.waitForArticleToDisappearByTitle(article_title);
        MyListsPageObgect.waitForArticleToAppearByTitle(article_title_second);

    }
}
