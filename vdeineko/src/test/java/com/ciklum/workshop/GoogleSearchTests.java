package com.ciklum.workshop;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;


public class GoogleSearchTests {
    @Before
    public void before(){
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
    }

    @Test
    public void testOpenGoogle() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        driver.quit();
    }

    @Test
    public void testSearch() {
        WebDriver driver = new FirefoxDriver();
        driver.get("http://www.google.com");
        driver.manage().window().maximize();

        driver.findElement(By.id("gs_htif0")).sendKeys("test");
        driver.findElement(By.name("btnG")).click();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        List<WebElement> results = driver.findElements(By.className("g"));
        assertEquals("Search results are not equal to 10", 10, results.size());
        driver.quit();
    }
}

