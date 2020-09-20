package com.Draft.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import com.Draft.pages.*;



public class TestHomePage extends AbstractTestBase{

    HomePage homepage = new HomePage();

    @Test // BTOR-1_AC#3 Verify home tab is functional (abdu)
    public void homeTab_Verification() {
        driver.get(homepage.contact_US_Url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.findElement(By.xpath(homepage.homeTabXpath)).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        String expectedTitle = "PHPTRAVELS | Travel Technology Partner";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles does not match");
    }

    @Test //AC#9
    public void login_tab() {

        WebElement loginTab = driver.findElement(By.xpath(homepage.loginTabXpath));
        Assert.assertTrue(loginTab.isDisplayed(), "Login tab is not displayed");

        String expected = "MY ACCOUNT";
        String actual = loginTab.getText().trim();

        Assert.assertEquals(actual, expected, "default text does not match");
    }


}
