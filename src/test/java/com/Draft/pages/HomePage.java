package com.Draft.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    //MAIN HEADER WEB ELEMENTS
    String URL = "https://www.phptravels.net/";
    public String loginTabXpath = "//div[contains(@class,'dropdown dropdown-login')]/a";

    public String contact_US_Url = "https://www.phptravels.net/contact-us";
    public String homeTabXpath = "//div[@id='mobileMenuMain']/nav/ul[1]/li/a";

    //HEADER DROPDOWNS MODULE WEB ELEMENTS
    String languageBarXpath = "//a[@id='dropdownLangauge']";
    String listOfLanguagesXpath = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";
    String currencyDropdownXPath = "//div[@class='dropdown dropdown-currency']";
    String currencyDropdownList = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";

    //My Account Dropdown tabs
    String myAccountXpath = "//div[@class='mini-menu']/ul/li[3]/div/a";
    // String loginTabXpath = "//div[@class='mini-menu']/ul/li[3]/div/div//a[1]";
    String signupTabXpath = "//div[@class='mini-menu']/ul/li[3]/div/div//a[2]";

    //Company tab
    String companyTabXpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/a";
    String getCompanyTabStyleXpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/ul";


    //FOOTER LINKS FEATURES WEB ELEMENTS
    String SubscribeButtonXpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]";
    String EnterEmailXpath = "//input[contains(@type,'email')]";


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


}
