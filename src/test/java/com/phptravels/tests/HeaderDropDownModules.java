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
    String URL = "https://www.phptravels.net/";
    String languageBarXpath = "//a[@id='dropdownLangauge']";
    String listOfLanguagesXpath = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
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



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
