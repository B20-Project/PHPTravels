package com.phptravels.pages;
import com.phptravels.Utility.HelperUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HomePage extends AbstractPageBase{

    //language
    @FindBy(xpath = "//a[@id='dropdownLangauge']")
    private WebElement languageBar;
    @FindBy (xpath= "//div[@class='dropdown-menu dropdown-menu-right show']/div/a")
    private List<WebElement> languageDropdownList;
    //currency
    @FindBy (xpath ="//div[@class='dropdown-menu dropdown-menu-right show']/div/a" )
    private List <WebElement> currencyDropdownList;
    @FindBy (xpath ="//a[contains(.,'USD   ')]")
    private WebElement currencyText;
    //account
    @FindBy(xpath = "//div[@class='mini-menu']/ul/li[3]/div/a")
    private WebElement accountTab;
    // company
    @FindBy (xpath = "//div[@id='mobileMenuMain']/nav/ul[2]/li/ul")
    private WebElement companyTabStyle;
    //phone #
    @FindBy(xpath = "//span[contains(@class,'d-block footer-phone text-white')]")
    private WebElement phoneNumber;
    @FindBy(xpath = "//i[contains(@class,'material-icons')]")
    private WebElement phoneIcon;
    //FOOTER
    @FindBy(xpath = "//footer[@id='footer']//a")
    private List <WebElement> actualFooterTexts;
    @FindBy(xpath = "//button[contains(@class,'btn btn-secondary sub_newsletter')]")
    private WebElement SubscribeButton;
    @FindBy(xpath = "//input[contains(@type,'email')]")
    private WebElement EnterEmail;
    @FindBy(xpath = "//ul[@class='nav navbar-nav']")
    private WebElement SubscribedSuccessfully;


    //Language
    public String get_default_language(){
        String actual = languageBar.getText();
        return actual;
    }
    public ArrayList<String> get_language_list(){
        ArrayList<String> actual = new ArrayList<>();
        for(WebElement eachLanguage : languageDropdownList) {
            actual.add(eachLanguage.getText());
        }
        return actual;
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

    //My Account
    public String get_account_text(){
        return accountTab.getText();
    }

    // Company
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

    //These two not used
    public void click_subscribe(String email){
        EnterEmail.sendKeys(email, Keys.ENTER);
        SubscribeButton.click();
    }
    public String get_subscription_text(){

       return SubscribedSuccessfully.getText().trim();
    }
    public ArrayList<String> get_footer_texts (){
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement eachElement : actualFooterTexts) {
            actual.add(eachElement.getText());
        }
        return actual;
    }



    /**Search
     *
     * @param name Hotels, Flights, Boats, Rentals, Tours, Cars, Visa
     */
    public void searchFor(String name){

        String search = String.format("//a[contains(text(),'%s')]",name);
        driver.findElement(By.xpath(search)).click();

    }

    /**Latest Blog
     *
     * @param num 1-2-3
     */
    public void navigate_to_blog(int num){

        String blog = String.format("//div[@class='mt-30 mb-40']//div[@class='col'][%s]//a",num);

        driver.findElement(By.xpath(blog)).click();
    }

    /**Featured Items
     *
     * @param item | Hotels | Flights | Tours | Rentals | Boats | Offers |
     * @param num  |  1-8   |  1-12   |  1-8  |   1-8   |  1-8  |   1    |
     */
    public void navigate_to_featured(String item, int num){
        String featured = String.format("//h2[contains(text(),'Featured %s')]/.." +
                                        "/following-sibling::div//div[%s]//a",item,num);

        driver.findElement(By.xpath(featured)).click();

    }

    public void click_hotel_destination(){
        driver.findElement(By.xpath("//div[@id='hotels']//child::a")).click();
    }

    public void pick_hotel_by_name(String name){
        String xpath = String.format("//li[child::div[contains(.,'%s')]]",name);
        driver.findElement(By.xpath(xpath)).click();
    }

    public void enter_hotel_checkIn_date(){
        WebElement startDate = driver.findElement(By.xpath("//div[@id='hotels']//child::input[contains(@name,'checkin')]"));
        startDate.clear();
        startDate.sendKeys(HelperUtil.getStartDate());
    }

    public void enter_hotel_checkOut_date(){
        WebElement endDate = driver.findElement(By.xpath("//div[@id='hotels']//child::input[contains(@name,'checkout')]"));
        endDate.clear();
        endDate.sendKeys(HelperUtil.getEndDate(7));
    }

    public void add_adult(){
        driver.findElement(By.xpath("//div[@id='hotels']//child::input[contains(@name,'adults')]//" +
                "following-sibling::span/button[.='+']")).click();
    }

    public void add_child(){
        driver.findElement(By.xpath("//div[@id='hotels']//child::input[contains(@name,'child')]//" +
                "following-sibling::span/button[.='+']")).click();
    }

    public void hotel_search_button(){
        driver.findElement(By.xpath("//div[@id='hotels']//button[@type='submit']")).click();
    }
}
