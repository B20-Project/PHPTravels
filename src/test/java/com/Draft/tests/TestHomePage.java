package com.Draft.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.Draft.Pages.*;



public class TestHomePage extends com.Draft.Tests.AbstractTestBase {

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



    @Test //UserStory#3(BTOR-21) AC#2 - Verify all footer texts - (Ahmet)
    public void User_Story_3_AC2()  {
        List<WebElement> actualListOfFooterTexts = driver.findElements(By.xpath("//footer[@id='footer']"));

        System.out.println("===================================");
        String [] expectedList = {"phone +1-234-56789\n" + "INFO@TRAVELAGENCY.COM\n" + "SUPPLIER\n" + "Supplier Sign Up\n" + "Supplier Login\n" + "COMPANY\n" +
                "Contact\n" + "How to Book\n" + "Booking Tips\n" + "About Us\n" + "SUPPORT\n" + "FAQ\n" + "Our Partners\n" + "Privacy Policy\n" + "Terms of Use\n" +
                "NEWSLETTER\n" + "Subsribe to get our latest updates and oeffers\n" + "SUBSCRIBE\n" + "Powered by PHPTRAVELS\n" + "© All Rights Reserved by PHPTRAVELS"};

        int i = 0;
        for (WebElement eachElement : actualListOfFooterTexts) {
            System.out.println(eachElement.getText());
            Assert.assertEquals(expectedList [i],eachElement.getText(), "Actual List of Footer Texts do not match expected list of footer lists" );
            i++;
        }
        System.out.println("===================================");

    }

    @Test //UserStory#3(BTOR-22) AC#3 - verify subscription function with valid email - (Ahmet)
    public void User_Story_3_AC3() throws InterruptedException {

        int randomNumber = (int) (Math.random() * 1000);
        //System.out.println("randomNumber = " + randomNumber); //testing random #
        String firstTime = "test" + randomNumber + "@gmail.com";
        System.out.println(firstTime);
        String inValid = "@gmail.com";
        String secondTime = "test";

        driver.findElement(By.xpath(homepage.EnterEmailXpath)).sendKeys(firstTime + Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.xpath(homepage.SubscribeButtonXpath)).click();
        Thread.sleep(1000);

        String expected = "Subscribed Successfully";
        String actual = driver.findElement(By.xpath(homepage.SubscribedSuccessfully)).getText().trim();

        Assert.assertEquals(actual,expected,"default text does not match");
    }


}
