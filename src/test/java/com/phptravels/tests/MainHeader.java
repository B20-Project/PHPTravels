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


    @Test //AC#8 - verify phone icon visible
    public void User_Story_1_AC8 (){
        WebElement phoneIcon = driver.findElement(By.xpath("//i[contains(@class,'material-icons')]"));

        Assert.assertTrue(phoneIcon.isDisplayed(),"Login tab is not displayed");
    

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
