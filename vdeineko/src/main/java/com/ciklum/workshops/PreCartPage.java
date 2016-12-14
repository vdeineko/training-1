package com.ciklum.workshops;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class PreCartPage extends BasePage{

    public WebElement preCartBtn(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElement(By.id("hlb-view-cart-announce"));
    }

    public WebElement addedTextElement(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver.findElement(By.cssSelector(".a-size-medium.a-text-bold"));
    }
}
