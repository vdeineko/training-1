package com.ciklum.workshop;

import com.ciklum.workshops.DetailsPage;
import com.ciklum.workshops.SearchPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchProductTests {

    private static WebDriver driver;
    private static SearchPage searchPage;
    private static DetailsPage detailsPage;

    @BeforeClass
    public static void set() {
        searchPage = new SearchPage();
        detailsPage = new DetailsPage();
        driver = searchPage.setUp();
    }

    @Before
    public void before() {
        searchPage.openStartPage("https://www.amazon.com");
        searchPage.searchProduct("toaster");
    }

    @Test
    public void testCountResults() throws InterruptedException {
        List <WebElement> results = searchPage.pageSearchResults();
        searchPage.verifyResultsNumber(28, results);
    }

    @Test
    public void testSorting(){
        searchPage.sortOptions("price-desc-rank");
        assertEquals("Sorting is not 'Price: High to Low'", searchPage.getSortText(), "Price: High to Low");
    }

    @Test
    public void testProductDetails() throws InterruptedException {
        searchPage.getToasterCategory().click();
        List <WebElement> results = searchPage.pageSearchResults();
        results.get(0).click();
        assert detailsPage.buyBtn().isDisplayed();
    }

      @AfterClass
      public static void setDown(){
          driver.quit();
      }
}
