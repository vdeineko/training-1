package com.ciklum.workshops.SelenidePages;

import com.codeborne.selenide.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byValue;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class BaseSelenidePage {

    public SelenideElement searchField = $("#twotabsearchtextbox");
    public SelenideElement searchBtn = $("#nav-search").find(byValue("Go"));
    public ElementsCollection searchResults = $$(".a-link-normal.s-access-detail-page.a-text-normal");
    public SelenideElement todayDeaLink = $("#nav-xshop").find(byText("Today's Deals"));
    public ElementsCollection todayDeals = $$("#dealTitle");
    public SelenideElement departments = $("#nav-link-shopall");
    public String depElemsID = "#nav-flyout-shopAll";


    public void setUp(){
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        //WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);
    }

    public void waitTimeout(int timeout) {
        Configuration.timeout = timeout;
    }

    public ElementsCollection getResults(String text){
        searchField.clear();
        searchField.sendKeys(text);
        searchBtn.click();
        return searchResults;
    }

    public ElementsCollection getTodayDeal(){
        todayDeaLink.click();
        return todayDeals;
    }

    public void getSubDepartment(String firstElem, String secondElem ){
        departments.hover();
        $(depElemsID).find(withText(firstElem)).hover();
        $(depElemsID).find(withText(secondElem)).click();
    }
}
