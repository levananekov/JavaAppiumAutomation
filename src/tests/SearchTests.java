package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
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
    public void testAmountNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results =SearchPageObject.getAmountOfFoundArticls();

        assertTrue(
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
    public void testSearchAndCancelSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Python");
        int item_title = SearchPageObject.getAmountOfFoundArticls();

//        System.out.println(item_title);
        assertTrue(
                "Not found elements and it is no good)",
                (item_title >= 2)
        );

        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitEmptyResultsAfterCancelSearch();
    }

    @Test
    public void testSearchTitleAndDescription()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Ruby");
        SearchPageObject.waitForElementByTitleAndDescription("Ruby",
                "Variety of corundum, mineral, gemstone");
        SearchPageObject.waitForElementByTitleAndDescription("Ruby Ridge",
                "Standoff in Idaho in 1992");
        SearchPageObject.waitForElementByTitleAndDescription("Ruby (programming language)",
                "Programming language"
                );
    }
}
