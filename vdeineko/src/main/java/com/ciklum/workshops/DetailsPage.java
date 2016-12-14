package com.ciklum.workshops;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DetailsPage extends BasePage {

    public WebElement buyBtn(){
        return (new WebDriverWait(driver, 5))
               .until(ExpectedConditions.presenceOfElementLocated(By.id("add-to-cart-button")));
    }

    public WebElement noProtectedPlanBtn(){
        return (new WebDriverWait(driver, 5, (long) 0.1))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("siNoCoverage-announce")));
    }

    public float getProductPrice(){
        WebElement priceElement = new WebDriverWait(driver, 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("priceblock_ourprice")));
        String price = priceElement.getText().substring(1);
        return Float.parseFloat(price);
    }
}
