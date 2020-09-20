package com.Draft.tests;
import com.Draft.pages.*;
import com.phptravels.Util.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestHomePage {

    public static WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @Test
    public static void User_Story_1_AC3(){
        driver.get("https://www.phptravels.net/contact-us");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        DraftHomepage.homeTabXpath(driver).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        String expectedTitle = "PHPTRAVELS | Travel Technology Partner";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles does not match");
    }




    @Test //AC#1 - verify all footer anchor links (Arpat)
    public static void User_Story_3_AC1() throws InterruptedException {
        //SUPPLIER
        String[]supplierExpected = {"supplier Registration","Supplier Login"};
        DraftHomepage.companyList(driver).size();



        // COMPANY
        String[] companyExpected = {"Contact","How to Book","Booking Tips","About Us"};
        DraftHomepage.companyList(driver).size();

        //Support
        String[] supportExpected = {"FAQ","Our Partners","Privacy Policy","Terms of Use"};
        DraftHomepage.supportExpected(driver).size();



    }



}
