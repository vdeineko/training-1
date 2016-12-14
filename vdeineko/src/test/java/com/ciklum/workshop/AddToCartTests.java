package com.ciklum.workshop;

import com.ciklum.workshops.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class AddToCartTests {

    private static WebDriver driver;
    private static SearchPage searchPage;
    private static DetailsPage detailsPage;
    private static CartPage cartPage;
    private static PreCartPage preCartPage;

    private String titleIphone = "Apple iPhone 6 16GB Factory Unlocked GSM Cell Phone - Space Grey";
    private String titleTable = "DHP Parsons Modern End Table, Black Wood Grain";
    private String chairTitle = "AmazonBasics Mid-Back Mesh Chair";


    @BeforeClass
    public static void set() {
        searchPage = new SearchPage();
        detailsPage = new DetailsPage();
        cartPage = new CartPage();
        driver = searchPage.setUp();
        preCartPage = new PreCartPage();
    }

    @Before
    public void before() {
        searchPage.openStartPage("https://www.amazon.com");
    }

    @Test
    public void testAddToCart() throws InterruptedException {
        searchPage.searchProduct("chair");
        List<WebElement> chairPageResults = searchPage.pageSearchResults();
        searchPage.findArticleByTitle(chairPageResults, chairTitle).click();
        detailsPage.buyBtn().click();
        assert preCartPage.addedTextElement().getText().equals("Added to Cart");
    }

    @Test
    public void testTotalCartPrice() throws InterruptedException {
        //Adding the iPhone to the cart
        searchPage.searchProduct("iPhone");
        List<WebElement> iPhonePageResults = searchPage.pageSearchResults();
        searchPage.findArticleByTitle(iPhonePageResults, titleIphone).click();
        float iPhonePrice = detailsPage.getProductPrice();
        detailsPage.buyBtn().click();
        Thread.sleep(1000);

        //Adding table to the cart
        searchPage.searchProduct("table");
        List<WebElement> TablePageResults = searchPage.pageSearchResults();
        searchPage.findArticleByTitle(TablePageResults, titleTable).click();
        float tablePrice = detailsPage.getProductPrice();
        detailsPage.buyBtn().click();
        detailsPage.cartBtn().click();

        //Verifying total price in the cart
        assert cartPage.getTotalPrice()==iPhonePrice + tablePrice;
    }

    @AfterClass
    public static void setDown() {
        driver.quit();
    }
}



