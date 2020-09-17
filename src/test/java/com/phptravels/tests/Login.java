package com.phptravels.tests;

import com.phptravels.Util.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Login {

    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";

    String loginEmail = "user@phptravels.com";
    String loginPassword = "demouser";
    String LoginEmailXpath = "//div[@class='form-group']//input[@type='email']";
    String LoginPasswordXpath = "//div[@class='form-group']//input[@type='password']";
    String loginButtonXpath = "//form[@id='loginfrm']/button[@type='submit']";
    String greetingMsgXpath = "//*[.='Hi, Demo User']";


    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void logintest1(){
        // **DO NOT RUN** Need a parent class from Header
        driver.findElement(By.xpath(LoginEmailXpath)).sendKeys(loginEmail);
        driver.findElement(By.xpath(LoginPasswordXpath)).sendKeys(loginPassword);
        driver.findElement(By.xpath(loginButtonXpath)).click();

        String expected = "Hi, Demo User";
        String actual = driver.findElement(By.xpath(greetingMsgXpath)).getText();
        Assert.assertEquals(actual,expected,"Account name does not match");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
