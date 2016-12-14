package com.ciklum.workshops;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    static WebDriver driver;

    public WebDriver setUp(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
        driver = new FirefoxDriver();
        return driver;
    }

    public void openStartPage(String url){
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void searchProduct(String product){
        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        search.clear();
        search.sendKeys(product);
        driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input")).click();
    }

    public WebElement cartBtn(){
        return (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("nav-cart-count")));
    }
}
