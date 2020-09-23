package com.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPage {
    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";

    String loginEmail = "user@phptravels.com";
    String loginPassword = "demouser";
    String LoginEmailXpath = "//div[@class='form-group']//input[@type='email']";
    String LoginPasswordXpath = "//div[@class='form-group']//input[@type='password']";
    String loginButtonXpath = "//form[@id='loginfrm']/button[@type='submit']";
    String greetingMsgXpath = "//*[.='Hi, Demo User']";

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
}
