package com.Draft.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePage extends AbstractPageBase{
    // Home tab
    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[1]/li/a")
    private WebElement homeTab;

    // company tab
    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/a")
    private WebElement companyTab;

    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/ul")
    private WebElement companyTabStyle;


    //HEADER DROPDOWNS MODULE WEB ELEMENTS
    @FindBy(xpath = "//a[@id='dropdownLangauge']")
    private WebElement languageBar;

    @FindBy (xpath= "//div[@class='dropdown-menu dropdown-menu-right show']/div/a")
    private List<WebElement> languageDropdownList;


    @FindBy (xpath ="//div[@class='dropdown dropdown-currency']" )
    private WebElement currencyDropdown;


    @FindBy (xpath ="//div[@class='dropdown-menu dropdown-menu-right show']/div/a" )
    private List <WebElement> currencyDropdownList;


    @FindBy (xpath ="//a[contains(.,'USD   ')]")
    private WebElement currencyText;



    @FindBy(xpath = "//span[contains(@class,'d-block footer-phone text-white')]")
    private WebElement phoneNumber;

    @FindBy(xpath = "//i[contains(@class,'material-icons')]")
    private WebElement phoneIcon;

    @FindBy(xpath = "//footer[@id='footer']")
    private List <WebElement> actualFooterTexts;

    //My account Tab
    @FindBy(xpath = "//div[@class='mini-menu']/ul/li[3]/div/a")
    private WebElement accountTab;

    @FindBy(xpath = "/div[@class='mini-menu']/ul/li[3]/div/div//a[2]")
    private WebElement signUpTab;

    @FindBy(xpath = "//div[contains(@class,'dropdown dropdown-login')]/a")
    private WebElement loginTab;


    //FOOTER LINKS FEATURES WEB ELEMENTS
    //GIT COMMAND PRACTICE
    //GIT PRACTICE AGAIN

    @FindBy(xpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]")
    private WebElement SubscribeButton;
    @FindBy(xpath = "//input[contains(@type,'email')]")
    private WebElement EnterEmail;
    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    private WebElement SubscribedSuccessfully;

    //FooterLinks -- > Arpat
    public String click_footer_links(String linkName)  {
        String homePageTitle = driver.getTitle();
        WebElement target  = driver.findElement(By.xpath("//a[.='"+linkName+"']"));
        target.click();
        String targetPageTitle =driver.getTitle();
        if (homePageTitle.equals(targetPageTitle)){
            Set<String> windowsIds = driver.getWindowHandles();
            Iterator<String> itera = windowsIds.iterator();
            String mainWindow = itera.next();
            String childWindow = itera.next();
            driver.switchTo().window(childWindow);
            targetPageTitle =driver.getTitle();
            driver.close();
            driver.switchTo().window(mainWindow);
        }
        return targetPageTitle;

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
    public String get_default_language(){
        String actual = languageBar.getText();
        return actual;
    }

    public List<WebElement> get_language_list(){
        return languageDropdownList;
    }

    public void click_language_bar(){
        languageBar.click();
    }

    //Subscription bar
    public void click_subscribe(String email){
        EnterEmail.sendKeys(email, Keys.ENTER);
        SubscribeButton.click();
    }

    public String get_subscription_text(){

       return SubscribedSuccessfully.getText().trim();
    }

    //Phone number
    public String get_phone_number(){
        return phoneNumber.getText().trim();
    }

    public String get_phone_icon_text() {
        return this.phoneIcon.getText();
    }

    //Footer Texts
    public ArrayList<String> get_footer_texts (){ //need to fix
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement eachElement : actualFooterTexts) {
            actual.add(eachElement.getText());
        }

        return actual;
    }

    public void searchFor(String name){

        driver.findElement(By.xpath("//a[@data-name='"+name+"']")).click();

    }

    //Currency bar

    public String get_default_currency(){
        String actualText = currencyText.getText();
        return actualText;
    }

    public List <WebElement> get_currency_list(){

        return currencyDropdownList;
    }

    public void click_currency_tab(){
        currencyDropdown.click();
    }

    //Home tab
    public void click_home_tab(){
        homeTab.click();
    }
    public String getTitle(){
        String actualTitle = driver.getTitle();
        return actualTitle;
    }

    // Company Tab
    public void click_company_tab(){
        companyTab.click();
    }

    public String get_companyTab_Style_text(){
        return companyTabStyle.getAttribute("Style");
    }

}
