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
    String browserType = "chrome";
    private String URL = "https://www.phptravels.net/";
    private String loginTabXpath = "//div[contains(@class,'dropdown dropdown-login')]/a";

    private String contact_US_Url = "https://www.phptravels.net/contact-us";
    private String homeTabXpath = "//div[@id='mobileMenuMain']/nav/ul[1]/li/a";


    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test //AC#9
    public void login_tab() {
        WebElement loginTab = driver.findElement(By.xpath(loginTabXpath));
        Assert.assertTrue(loginTab.isDisplayed(), "Login tab is not displayed");

        String expected = "MY ACCOUNT";
        String actual = loginTab.getText().trim();

        Assert.assertEquals(actual, expected, "default text does not match");
    }

    @Test//AC#1 - verify phptravels logo is visible 
    public void User_Story_1_AC1() {
        // locate logo element and assign it
        WebElement logo = driver.findElement(By.cssSelector("div[class='header-logo go-right']"));
        Assert.assertTrue(logo.isDisplayed(), "Logo is not visible");
    }

    @Test// AC#2 - verify phptravels logo is clickable - arpat
    public void User_Story_1_AC2() {

        driver.navigate().to("https://www.phptravels.net/blog/Fiercely-Independent-Cultures");

        WebElement logo = driver.findElement(By.xpath("//div[@class='header-logo go-right']/a"));
        logo.click();

        String expectedResult = "https://www.phptravels.net/";
        String actuallyResult = driver.getCurrentUrl();

        Assert.assertTrue(expectedResult.equalsIgnoreCase(actuallyResult));
    }

    @Test// AC#6-Verify language bar visible & default language selected (ENGLISH)
    public void global_language_bar() {
        WebElement languageBar = driver.findElement(By.xpath("//a[@id='dropdownLangauge']"));
        Assert.assertTrue(languageBar.isDisplayed());

        String defaultSelectedLanguage = "ENGLISH";
        String actualSelectedLanguage = languageBar.getText();
        Assert.assertEquals(defaultSelectedLanguage, actualSelectedLanguage, "default language ENGLISH is not selected");
    }

    @Test  //AC#7 - verify phoneNumber is visible & correct (+1-234-56789)
    public void User_Story_1_AC7() {

        WebElement phoneNumber = driver.findElement(By.xpath("//span[contains(@class,'d-block footer-phone text-white')]"));
        String text = phoneNumber.getText();
        System.out.println(text); //Testing to see the actual result the web element is giving "phone +1-234-56789"

        //NOTE: The WebElement always involved a phone text and not just a phone number, so I had to add phone to my expected result.
        String expected = "phone +1-234-56789";
        String actual = phoneNumber.getText().trim();

        Assert.assertTrue(phoneNumber.isDisplayed(), "Login tab is not displayed");
        Assert.assertEquals(actual, expected, "default text does not match");
    }

    @Test //AC#8 - verify phone icon visible
    public void User_Story_1_AC8() {
        WebElement phoneIcon = driver.findElement(By.xpath("//i[contains(@class,'material-icons')]"));
        Assert.assertTrue(phoneIcon.isDisplayed(), "Login tab is not displayed");
    }

    @Test //AC#5 verify currency is visible and default selected

    public void User_Story_1_AC5() {
        WebElement currency = driver.findElement(By.xpath("//a[contains(.,'USD   ')]"));
        Assert.assertTrue(currency.isDisplayed(), "Currency default USD is not displayed ");

    }

    @Test // BTOR-1_AC#3 Verify home tab is functional (abdu)
    public void homeTab_Verification() {
        driver.get(contact_US_Url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath(homeTabXpath)).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        String expectedTitle = "PHPTRAVELS | Travel Technology Partner";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles does not match");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}