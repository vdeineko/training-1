package com.ciklum.workshop.SelenideTests;


import com.ciklum.workshops.SelenidePages.BaseSelenidePage;
import com.ciklum.workshops.SelenidePages.ResultsSelenidePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;

public class AmazonSelenideTests {

    private static ResultsSelenidePage resultsSelenidePage;

    @BeforeClass
    public static void set() {
        resultsSelenidePage = new ResultsSelenidePage();
        resultsSelenidePage.setUp();
    }

    @Before
    public void before() {
        open("https://www.amazon.com");
    }


    @Test
    public void testSearch(){
        //Finding the products and verifying the result's quantity on 1st page
        ElementsCollection results = resultsSelenidePage.getResults("camera");
        results.shouldHaveSize(26);
    }

    @Test
    public void testDealsList(){
        //Navigation to today's deals
        resultsSelenidePage.getTodayDeal();
        $(byText("DEAL OF THE DAY")).shouldBe(visible);
    }

    @Test
    public void testSortByPrice() throws InterruptedException {
        //Finding the products and sorting the results by price
        resultsSelenidePage.getResults("toaster");
        resultsSelenidePage.sortPage("Price: High to Low");
        //Getting price of 2 results and compare it
        Thread.sleep(3000);
        ElementsCollection prices = resultsSelenidePage.getPrices();
        float firstPrice = Float.parseFloat(prices.get(1).getText());
        float secondPrice = Float.parseFloat(prices.get(2).getText());
        assert firstPrice > secondPrice;
    }

    @Test
    public void testDepartments(){
        //Getting the department and navigation to it
        resultsSelenidePage.getSubDepartment("Kindle E-readers & Books","Kindle Voyage");
        $("#bb_to_cfg_button").should(visible);
    }

    @AfterClass
    public static void setDown(){
        WebDriverRunner.getWebDriver().quit();
    }
}
