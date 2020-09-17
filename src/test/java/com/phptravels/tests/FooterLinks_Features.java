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
    
    String SubscribeButtonXpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]";
    String EnterEmailXpath = "//input[contains(@type,'email')]";


    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }
    
        @Test //AC#1 - verify all footer anchor links (Arpat)
    public void User_Story_3_AC1() throws InterruptedException {
        //SUPPLIER
        String[]supplierExpected = {"supplier Registration","Supplier Login"};
        List<WebElement> supplierList = driver.findElements(By.xpath("//footer[@id='footer']//div//div//div//ul[@class='footer-menu go-right go-text-right']/li"));// it should give me 3

        for (int i = 1; i <supplierList.size() ; i++) {// start with 1 bec first one is not clickable
            driver.findElement(By.xpath(            "//footer[@id='footer']//div//div//div//ul[@class='footer-menu go-right go-text-right']/li["+i+"]/a"));
            driver.get(driver.findElement(By.xpath("//footer[@id='footer']//div//div//div//ul[@class='footer-menu go-right go-text-right']/li["+(i+1)+"]/a")).getAttribute("href"));
            Thread.sleep(3000);
            //get expectResult
            Assert.assertEquals(driver.getTitle(),supplierExpected[i-1]);
            driver.navigate().back();
        }

        // COMPANY
        String[] companyExpected = {"Contact","How to Book","Booking Tips","About Us"};
        List<WebElement> companyList = driver.findElements(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[1][@class='text-center']//ul//li"));// it should give me 4 result
        for (int i = 0; i <companyList.size() ; i++) {
            driver.findElement(By.xpath(           "//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[1][@class='text-center']//ul//li["+(i+1)+"]//a"));// re fined when page get refresh
            driver.get(driver.findElement(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[1][@class='text-center']//ul//li["+(i+1)+"]//a")).getAttribute("href"));
            Thread.sleep(3000);
            Assert.assertEquals(driver.getTitle(), companyExpected[i]);
            driver.navigate().back();
        }

        //SUPPORT
        String[] supportExpected = {"FAQ","Our Partners","Privacy Policy","Terms of Use"};
        List<WebElement> supportList = driver.findElements(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[2][@class='text-center']//ul//li"));// it should give me 4 result
        for (int i = 0; i <companyList.size() ; i++) {
            driver.findElement(By.xpath(           "//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[2][@class='text-center']//ul//li["+(i+1)+"]//a"));// re fined when page get refresh
            driver.get(driver.findElement(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[2][@class='text-center']//ul//li["+(i+1)+"]//a")).getAttribute("href"));
            Thread.sleep(3000);
            Assert.assertEquals(driver.getTitle(), supportExpected[i]);
            driver.navigate().back();
        }

        //webs
        String [] websExpected = {"Happy To Serve Travel - Home | Facebook",
                          "PHPTRAVELS (@PHPTRAVELS) / Twitter",
                          "PHPTRAVELS - YouTube",
                          "Share on WhatsApp",
                          "Your location to phptravels google maps - Google Maps",
                          "Page Not Found • Instagram"};
        List<WebElement> websList = driver.findElements(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='col-inner mt-25-sm']//div[@class='footer-socials go-right']/a"));// it should give me 4 result
        for (int i = 0; i < websList.size() ; i++) {
            driver.findElement(By.xpath(           "//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='col-inner mt-25-sm']//div[@class='footer-socials go-right']/a"));// re fined when page get refresh
            driver.get(driver.findElement(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='col-inner mt-25-sm']//div[@class='footer-socials go-right']/a["+(i+1)+"]")).getAttribute("href"));
            Thread.sleep(3000);
            Assert.assertEquals(driver.getTitle(),websExpected[i]);
            driver.navigate().back();
        }
        
        //Logo
        String logoExpected ="PHPTRAVELS booking script and system for hotels airline flights tours cars online application - PHPTRAVELS";
        driver.get(driver.findElement(By.cssSelector("#footer > div.container > div.d-none.d-md-block > div > div > a")).getAttribute("href"));
        driver.getTitle();
        Assert.assertEquals(driver.getTitle(),logoExpected);
        driver.navigate().back();
     
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

        driver.findElement(By.xpath(EnterEmailXpath)).sendKeys(firstTime + Keys.ENTER);
        Thread.sleep(3000);

        driver.findElement(By.xpath(SubscribeButtonXpath)).click();
        Thread.sleep(1000);

        WebElement SubscribedSuccessfully = driver.findElement(By.xpath("//ul[@class='nav navbar-nav']"));
        String expected = "Subscribed Successfully";
        String actual = SubscribedSuccessfully.getText().trim();

        Assert.assertEquals(actual,expected,"default text does not match");
    }

    

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
