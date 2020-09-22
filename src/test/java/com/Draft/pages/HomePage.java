package com.Draft.pages;
;
import com.Draft.Utility.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
    @FindBy(xpath = "//a[@id='dropdownLangauge']")
    private WebElement languageBar;

    @FindBy (xpath= "//div[@class='dropdown dropdown-currency']")
    private WebElement listOfLanguages;
    //public String listOfLanguagesXpath = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";

    @FindBy (xpath ="//div[@class='dropdown dropdown-currency']" )
    private WebElement currencyDropdown;
//public String currencyDropdownXPath = "//div[@class='dropdown dropdown-currency']";

    @FindBy (xpath ="//div[@class='dropdown-menu dropdown-menu-right show']/div/a" )
    private WebElement currencyDropdownList;
//public String currencyDropdownList = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";

    @FindBy (xpath ="//a[contains(.,'USD   ')]")
    private WebElement currencyText;
//public String currencyTextXpath = "//a[contains(.,'USD   ')]";


    @FindBy(xpath = "//span[contains(@class,'d-block footer-phone text-white')]")
    private WebElement phoneNumber;

    @FindBy(xpath = "//i[contains(@class,'material-icons')]")
    private WebElement phoneIcon;

    @FindBy(xpath = "//footer[@id='footer']")
    private WebElement actualFooterTexts;


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
    //GIT COMMAND PRACTICE
    //GIT PRACTICE AGAIN

    @FindBy(xpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]")
    private WebElement SubscribeButton;
    @FindBy(xpath = "//input[contains(@type,'email')]")
    private WebElement EnterEmail;
    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    private WebElement SubscribedSuccessfully;

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

    //Language bar
    public String verify_default_language(){
        String actual = languageBar.getText();
        return actual;
    }

    //Subscription bar
    public void subscribe(String email){
        EnterEmail.sendKeys(email, Keys.ENTER);
        SubscribeButton.click();
    }

    public String verify_subscribe(){
        BrowserUtils.wait(1);
       return SubscribedSuccessfully.getText().trim();
    }

    //Phone number
    public String verify_number (){
        return phoneNumber.getText().trim();
    }

    public void verify_phoneIcon (){ //need to fix
        phoneIcon.isDisplayed();
    }

    //Footer Texts
    public String verifyFooterTexts (){ //need to fix
        return actualFooterTexts.getText();
    }

    public void searchFor(String name){

        driver.findElement(By.xpath("//a[@data-name='"+name+"']")).click();

    }

    //Currency bar

    public String verify_default_currency(){
        String actualText = currencyText.getText();
        return actualText;
    }


}
