package com.phptravels.tests;

import com.phptravels.Util.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MainHeader {
    WebDriver driver;
    String URL = "https://www.phptravels.net/";

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void login_tab(){
        WebElement loginTab = driver.findElement(By.xpath("//div[contains(@class,'dropdown dropdown-login')]/a"));
        Assert.assertTrue(loginTab.isDisplayed(),"Login tab is not displayed");

        String expected = "MY ACCOUNT";
        String actual = loginTab.getText().trim();

        Assert.assertEquals(actual,expected,"default text does not match");
        System.out.println("test3");
        System.out.println("test4");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
