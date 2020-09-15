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
        System.out.println("test1");
    }
    
    @Test//AC#1 - verify phptravels logo is visible 
    public void User_Story_1_AC1(){
        // locate logo element and assign it
        WebElement logo =  driver.findElement(By.cssSelector("div[class='header-logo go-right']"));
        Assert.assertTrue(logo.isDisplayed(),"Logo is not visible");
    }

    Test //AC#7 - verify phoneNumber is visible & correct (+1-234-56789)
    public void User_Story_1_AC7 (){

        WebElement phoneNumber = driver.findElement(By.xpath("//span[contains(@class,'d-block footer-phone text-white')]"));
        String text = phoneNumber.getText();
        System.out.println(text); //Testing to see the actual result the web element is giving "phone +1-234-56789"

        //NOTE: The WebElement always involved a phone text and not just a phone number, so I had to add phone to my expected result.
        String expected = "phone +1-234-56789";
        String actual = phoneNumber.getText().trim();

        Assert.assertTrue(phoneNumber.isDisplayed(),"Login tab is not displayed");
        Assert.assertEquals(actual,expected,"default text does not match");
    }
    
    @Test //AC#8 - verify phone icon visible
    public void User_Story_1_AC8 (){
        WebElement phoneIcon = driver.findElement(By.xpath("//i[contains(@class,'material-icons')]"));
        Assert.assertTrue(phoneIcon.isDisplayed(),"Login tab is not displayed");
    }

    @Test //AC#5 verify currency is visible and default selected

    public void User_Story_1_AC5(){
        WebElement currency = driver.findElement(By.xpath("//a[contains(.,'USD   ')]"));
        Assert.assertTrue(currency.isDisplayed(),"Currency default USD is not displayed ");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
