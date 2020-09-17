package com.phptravels.tests;

import com.phptravels.Util.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Signup {
    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";

    String firstNameXpath = "//input[@name='firstname']";
    String lastNameXpath = "//input[@name='lastname']";
    String phoneNumXpath = "//input[@name='phone']";
    String signupEmailXpath = "//input[@name='email']";
    String signupPasswordXpath = "//input[@name='password']";
    String confirmPasswordXpath = "//input[@name='confirmpassword']";
    String signupButoonXpath = "//button[contains(text(),' Sign Up')]";
    String firstName = "Demo";
    String lastName = "Junior";
    String phoneNum = "2828288";
    String signupEmail = "B22@gmail.com";
    String newPassword = "123456";
    String newGreetingMsgXpath = "//*[.='Hi, "+firstName+" "+lastName+"']";

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public void signuptest1(){
        // **DO NOT RUN** Need a parent class from Header
        driver.findElement(By.xpath(firstNameXpath)).sendKeys(firstName);
        driver.findElement(By.xpath(lastNameXpath)).sendKeys(lastName);
        driver.findElement(By.xpath(phoneNumXpath)).sendKeys(phoneNum);
        driver.findElement(By.xpath(signupEmailXpath)).sendKeys(signupEmail);
        driver.findElement(By.xpath(signupPasswordXpath)).sendKeys(newPassword);
        driver.findElement(By.xpath(confirmPasswordXpath)).sendKeys(newPassword);
        driver.findElement(By.xpath(signupButoonXpath)).click();

        String expected = "Hi, "+firstName+" "+lastName;
        String actual = driver.findElement(By.xpath("//*[.='Hi, "+firstName+" "+lastName+"']")).getText();

        Assert.assertEquals(actual,expected,"Account name does not match");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
