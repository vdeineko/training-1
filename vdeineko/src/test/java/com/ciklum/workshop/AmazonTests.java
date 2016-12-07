package com.ciklum.workshop;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


import static org.junit.Assert.assertEquals;

public class AmazonTests {

    static WebDriver driver;


    public void searchProduct(String product){
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
        driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input")).click();
    }

    public void verifyResults(int expectedNumber, List<WebElement> results){
        assertEquals("number of search results is not correct", expectedNumber, results.size());
    }

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
    }

    @Before
    public void before(){
        driver.get("https://www.amazon.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        searchProduct("toaster");
    }

    @Test
    public void testCountOvens() throws InterruptedException {
        Thread.sleep(10000);
        List<WebElement> results = driver.findElements(By.xpath(".//*[@id='s-results-list-atf']/li"));
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        verifyResults(28, results);
    }

    @Test
    public void testSorting(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("sort")).click();
        driver.findElement(By.xpath(".//*[@id='sort']/option[4]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String sortText = driver.findElement(By.xpath(".//*[@id='centerPlus']/h3/span[1]")).getText();
        assertEquals("Sorting is not 'Price: High to Low'", sortText, "Price: High to Low");
    }

    @Test
    public void testProductDetails(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li[4]/a/span[1]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@id='result_0']/div/div[4]/div[1]/a/h2")).click();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement buyBtn = driver.findElement(By.id("add-to-cart-button"));
        assert buyBtn.isEnabled();
    }

      @AfterClass
      public static void setDown(){
          driver.quit();
      }

}
