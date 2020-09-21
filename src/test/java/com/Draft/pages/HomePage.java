package com.Draft.pages;
;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends AbstractPageBase{
    //MAIN HEADER WEB ELEMENTS

    private String contact_US_Url = "https://www.phptravels.net/contact-us";

    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[1]/li/a")
    private WebElement homeTab;

    //HEADER DROPDOWNS MODULE WEB ELEMENTS
    public String languageBarXpath = "//a[@id='dropdownLangauge']";
    public String listOfLanguagesXpath = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";
    public String currencyDropdownXPath = "//div[@class='dropdown dropdown-currency']";
    public String currencyDropdownList = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";
    public String currencyTextXpath = "//a[contains(.,'USD   ')]";

    //Ahmet header
    @FindBy (xpath = "//span[contains(@class,'d-block footer-phone text-white')]")
    private WebElement PhoneNumber;

    @FindBy (xpath = "//i[contains(@class,'material-icons')]")
    private WebElement PhoneIcon;

    private String phoneNumber = "//span[contains(@class,'d-block footer-phone text-white')]";
    private String phoneIcon = "//i[contains(@class,'material-icons')]";


    //My account Tab
    @FindBy(xpath = "//div[@class='mini-menu']/ul/li[3]/div/a")
    private WebElement accountTab;

    @FindBy(xpath = "/div[@class='mini-menu']/ul/li[3]/div/div//a[2]")
    private WebElement signUpTab;

    @FindBy(xpath = "//div[contains(@class,'dropdown dropdown-login')]/a")
    private WebElement loginTab;

    //Company tab
    public String companyTabXpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/a";
    public String getCompanyTabStyleXpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/ul";

    //FOOTER LINKS FEATURES WEB ELEMENTS

    @FindBy(xpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]")
    private WebElement SubscribeButton;

    @FindBy(xpath = "//input[contains(@type,'email')]")
    private WebElement EnterEmail;

    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    private WebElement SubscribedSuccessfully;

   // public  String SubscribeButtonXpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]";
   // public  String EnterEmailXpath = "//input[contains(@type,'email')]";
   // public  String SubscribedSuccessfully = "//ul[@class='nav navbar-nav']";

    // Arpat
    /////////////////////////////////////////////////////////////////////////////
    private static List<WebElement> supplierList=null;
    public static  List<WebElement> footer_ListOf_supplier_Sign_Up(WebDriver driver){
        supplierList= driver.findElements(By.xpath("//footer[@id='footer']//div//div//div//ul[@class='footer-menu go-right go-text-right']/li"));
        return supplierList;
    }

    private static List<WebElement>companyList=null;
    public static List<WebElement> companyList(WebElement driver){
        companyList = driver.findElements(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[1][@class='text-center']//ul//li"));// it should give me 4 result
        return companyList;
    }

    private static List<WebElement> supportList=null;
    public static List<WebElement>supportExpected(WebDriver driver){
        supportList = driver.findElements(By.xpath("//footer[@id='footer']//div//div//div[@class='col-12 col-lg-9']//div//div//div[@class='footer_menu col-12 col-md-6']//ul[@class='main-nav']//li[2][@class='text-center']//ul//li"));// it should give me 4 result
        return supportList;
    }
    //Home tab method
    public void click_home_tab(){
        driver.get(contact_US_Url);
        homeTab.click();
    }
    public String getTitle(){
        String actualTitle = driver.getTitle();

        return actualTitle;
    }
    //My Account tab methods
    public void click_account_tab() {
        accountTab.click();
    }
    public String get_account_text(){
        return accountTab.getText();
    }
    public void click_login_tab(){
        loginTab.click();
    }
    public void click_signUp_tab(){
        signUpTab.click();
    }

    public void searchFor(String name){

        driver.findElement(By.xpath("//a[@data-name='"+name+"']")).click();

    }

}
