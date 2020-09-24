package com.phptravels.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePage extends AbstractPageBase{
    // Home
    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[1]/li/a")
    private WebElement homeTab;
    //language
    @FindBy(xpath = "//a[@id='dropdownLangauge']")
    private WebElement languageBar;
    @FindBy (xpath= "//div[@class='dropdown-menu dropdown-menu-right show']/div/a")
    private List<WebElement> languageDropdownList;
    //currency
    @FindBy (xpath ="//div[@class='dropdown dropdown-currency']" )
    private WebElement currencyDropdown;
    @FindBy (xpath ="//div[@class='dropdown-menu dropdown-menu-right show']/div/a" )
    private List <WebElement> currencyDropdownList;
    @FindBy (xpath ="//a[contains(.,'USD   ')]")
    private WebElement currencyText;
    //account
    @FindBy(xpath = "//div[@class='mini-menu']/ul/li[3]/div/a")
    private WebElement accountTab;
    @FindBy(xpath = "//a[.='Sign Up']")
    private WebElement signUpTab;
    @FindBy(xpath = "//div[contains(@class,'dropdown dropdown-login')]/a")
    private WebElement loginTab;
    // company
    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/a")
    private WebElement companyTab;
    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/ul")
    private WebElement companyTabStyle;
    //phone #
    @FindBy(xpath = "//span[contains(@class,'d-block footer-phone text-white')]")
    private WebElement phoneNumber;
    @FindBy(xpath = "//i[contains(@class,'material-icons')]")
    private WebElement phoneIcon;
    //FOOTER
    @FindBy(xpath = "//footer[@id='footer']")
    private List <WebElement> actualFooterTexts;
    @FindBy(xpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]")
    private WebElement SubscribeButton;
    @FindBy(xpath = "//input[contains(@type,'email')]")
    private WebElement EnterEmail;
    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    private WebElement SubscribedSuccessfully;

    //Home
    public void click_home_tab() {
        homeTab.click();
    }

    //Language
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

    //Currency
    public String get_default_currency(){
        String actualText = currencyText.getText();
        return actualText;
    }
    public ArrayList<String> get_currency_List(){

        ArrayList<String> actual = new ArrayList<>();
        for (WebElement eachCurrency : currencyDropdownList) {
            actual.add(eachCurrency.getText());
        }

        return actual;
    }
    public void click_currency_tab(){
        currencyDropdown.click();
    }

    //My Account
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

    // Company
    public void click_company_tab(){
        companyTab.click();
    }
    public String get_companyTab_Style_text(){
        return companyTabStyle.getAttribute("Style");
    }

    /**phone#
     *
     * @return
     */
    public String get_phone_number(){
        return phoneNumber.getText().trim();
    }
    public String get_phone_icon_text() {
        return this.phoneIcon.getText();
    }

    /**Footer
     *
     * @param linkName
     * @return
     */
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
    public void click_subscribe(String email){
        EnterEmail.sendKeys(email, Keys.ENTER);
        SubscribeButton.click();
    }
    public String get_subscription_text(){

       return SubscribedSuccessfully.getText().trim();
    }
    public ArrayList<String> get_footer_texts (){ //need to fix
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement eachElement : actualFooterTexts) {
            actual.add(eachElement.getText());
        }

        return actual;
    }

    //search module
    public void searchFor(String name){

        driver.findElement(By.xpath("//a[@data-name='"+name+"']")).click();

    }







}
