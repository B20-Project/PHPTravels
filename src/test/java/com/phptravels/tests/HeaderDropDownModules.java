package com.phptravels.tests;

import com.phptravels.Util.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HeaderDropDownModules {
    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";

    String languageBarXpath = "//a[@id='dropdownLangauge']";
    String listOfLanguagesXpath = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";
    String currencyDropdownXPath = "//div[@class='dropdown dropdown-currency']";
    String currencyDropdownList = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";


    //My Account Dropdown tabs
    String myAccountXpath = "//div[@class='mini-menu']/ul/li[3]/div/a";
    String loginTabXpath = "//div[@class='mini-menu']/ul/li[3]/div/div//a[1]";
    String signupTabXpath = "//div[@class='mini-menu']/ul/li[3]/div/div//a[2]";

    //Company tab
    private String companyTabXpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/a";
    private String getCompanyTabStyleXpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/ul";


    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test //AC#2 - Currency DropDown - verify each currency(Zeliha)
    public void currency_dropdown (){

        WebElement currencyBar = driver.findElement(By.xpath(currencyDropdownXPath));
        currencyBar.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String [] expectedCurrencyList = {"USD", "GBP","SAR","EUR","PKR","KWD","JPY","INR","CNY","TRY","RUB"};

        int index=0;
        List<WebElement> listOfCurrencies =driver.findElements(By.xpath(currencyDropdownList));
        for(WebElement eachCurrency : listOfCurrencies){
            Assert.assertEquals(eachCurrency.getText(),expectedCurrencyList[index]);
            index++;
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        int sizeOfExpectedCurrencyList = expectedCurrencyList.length;
        int sizeOfListOfCurrencies = listOfCurrencies.size();

        Assert.assertTrue(sizeOfExpectedCurrencyList == sizeOfListOfCurrencies);
    }

    @Test //User Story#3(BTOR-12) AC#3 - Language DropDown - verify each language (Elvira)
    public void language_dropDown(){

        WebElement languageBar = driver.findElement(By.xpath(languageBarXpath));
        languageBar.click();

        String[] expectedListOfLanguages = {"Vietnamese","Russian","English","Arabic","Farsi","Turkish","French","Spanish","German"};

        int i = 0;
        List<WebElement> listOfLanguages = driver.findElements(By.xpath(listOfLanguagesXpath));
        for(WebElement each : listOfLanguages){
            //System.out.println(each.getText());
            Assert.assertEquals(each.getText(), expectedListOfLanguages[i]);
                i++;
        }
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        int sizeOfExpectedListOfLanguages = expectedListOfLanguages.length;
        int sizeOfListOfLanguages = listOfLanguages.size();
        Assert.assertTrue(sizeOfExpectedListOfLanguages == sizeOfListOfLanguages);
    }

    @Test
    public void login_Tab(){
        driver.findElement(By.xpath(myAccountXpath)).click();
        driver.findElement(By.xpath(loginTabXpath)).click();

        String expected = "Login";
        String actual = driver.getTitle();
        Assert.assertEquals(actual,expected,"page title does not match");
    }

    @Test
    public void signUp_tab(){
        driver.findElement(By.xpath(myAccountXpath)).click();
        driver.findElement(By.xpath(signupTabXpath)).click();

        String expected = "Register";
        String actual = driver.getTitle();
        Assert.assertEquals(actual,expected,"page title does not match");
    }

    // BTOR-1_AC#4 Verify company tab is functional (abdu)
    @Test
    public void company_DropDown_Verification() throws InterruptedException {
        driver.findElement(By.xpath(companyTabXpath)).click();
        Thread.sleep(1000);
        String actual_Style_Value = driver.findElement(By.xpath(getCompanyTabStyleXpath)).getAttribute("style");
        // System.out.println("actual_Style_Value = " + actual_Style_Value);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String expected_Style_Value = "display: block;";
        Assert.assertEquals(actual_Style_Value, expected_Style_Value, "style attribute does not match");
    }

    // BTOR-12_AC#1 Verify company dropdown Contact tab is functional (abdu)
    @Test
    public void company_DropDown_contact_Verification() throws InterruptedException {
        driver.findElement(By.xpath(companyTabXpath)).click();
        Thread.sleep(1000);
        driver.findElement((By.linkText("Contact"))).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        String expected_title_Value = "Contact";
        String actual_title_Value = driver.getTitle();
        Assert.assertEquals(actual_title_Value, expected_title_Value, "page title does not match");
    }

    // BTOR-12_AC#1 Verify company dropdown About Us tab is functional(abdu)
    @Test
    public void company_DropDown_AboutUs_Verification() throws InterruptedException {
        driver.findElement(By.xpath(companyTabXpath)).click();
        Thread.sleep(1000);
        driver.findElement((By.linkText("About Us"))).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String expected_title_Value = "About Us";
        String actual_title_Value = driver.getTitle();
        Assert.assertEquals(actual_title_Value, expected_title_Value, "page title does not match");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }




}
