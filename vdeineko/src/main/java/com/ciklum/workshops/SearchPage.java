package com.ciklum.workshops;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class SearchPage extends BasePage{

    public List <WebElement> pageSearchResults() throws InterruptedException {
        Thread.sleep(10000);
        return driver.findElements(By.cssSelector(".a-link-normal.s-access-detail-page.a-text-normal"));
    }

    public void verifyResultsNumber(int expectedNumber, List<WebElement> results){
        assertEquals("number of search results is not correct", expectedNumber, results.size());
    }

    public void sortOptions(String option) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Select dropdown = new Select(driver.findElement(By.id("sort")));
        dropdown.selectByValue(option);
    }

    public String getSortText() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElement(By.xpath(".//*[@id='centerPlus']/h3/span[1]")).getText();
    }

    public WebElement getToasterCategory(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElement(By.xpath(".//*[@id='refinements']/div[2]/ul[1]/li[4]/a/span[1]"));
    }

    public WebElement findArticleByTitle(List <WebElement> results, String productTitle ){
        for (WebElement result : results) {
            if (result.getAttribute("title").equals(productTitle)) return result;
        }
        return null;
    }
}
