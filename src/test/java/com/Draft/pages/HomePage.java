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
    public String currencyDropdownXPath = "//div[@class='dropdown dropdown-currency']";
    public String currencyDropdownList = "//div[@class='dropdown-menu dropdown-menu-right show']/div/a";
    public String currencyTextXpath = "//a[contains(.,'USD   ')]";

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

}
