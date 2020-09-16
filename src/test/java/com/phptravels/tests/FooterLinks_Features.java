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

public class FooterLinks_Features {
    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";


    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }
    
    @Test //AC#2 - Verify all footer texts
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

        /*MANUAL WAY OF IDENTIFYING ONE BY ONE
        
        //SUPPLIER SIGN UP
        WebElement SupplierSignUp = driver.findElement(By.xpath("//a[contains(@href,'https://www.phptravels.net/supplier-register/')]"));
        String text1 = SupplierSignUp.getText();
        System.out.println(text1);

        String expected1 = "Supplier Sign Up";
        String actual1 = SupplierSignUp.getText().trim();
        
        Assert.assertTrue(SupplierSignUp.isDisplayed(),"Supplier Sign up text is not displayed");
        Assert.assertEquals(actual1,expected1,"default text does not match");

        // SUPPLIER LOGIN
        WebElement SupplierLogin = driver.findElement(By.xpath("//a[contains(@href,'https://www.phptravels.net/supplier/')]"));
        String text2 = SupplierLogin.getText();
        System.out.println(text2);

        String expected2 = "Supplier Login";
        String actual2 = SupplierLogin.getText().trim();

        Assert.assertTrue(SupplierLogin.isDisplayed(), "Supplier Login text is not displayed");
        Assert.assertEquals(actual2, expected2, "default text does not match");
*/
    }
    

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
