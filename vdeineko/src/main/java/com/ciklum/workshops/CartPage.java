package com.ciklum.workshops;

import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage {

    public float getTotalPrice(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String price = driver.findElement(By.cssSelector(".a-size-medium.a-color-price.sc-price.sc-white-space-nowrap.sc-price-sign")).getText().substring(1);
        return Float.parseFloat(price);
    }

}
