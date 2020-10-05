package com.phptravels.pages;
import com.phptravels.Utility.BrowserUtils;
import com.phptravels.Utility.GlobalDataUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class HomePage extends AbstractPageBase{

    GlobalDataUtil active_tab = new GlobalDataUtil();

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

    public ArrayList<String> get_footer_texts (){
        ArrayList<String> actual = new ArrayList<>();
        for (WebElement eachElement : actualFooterTexts) {
            actual.add(eachElement.getText());
        }
        return actual;
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

    /**Search
     *
     * @param name Hotels, Flights, Boats, Rentals, Tours, Cars, Visa
     */
    public void searchFor(String name){
        active_tab.setTabName(name);
        String search = String.format("//a[contains(text(),'%s')]",name);
        driver.findElement(By.xpath(search)).click();
    }

    /**
     * click destination input box
     */
    public void click_destination(){
        String xpath = String.format("//div[@id='%s']//" +
                "child::a[span[@class='select2-chosen']]",active_tab.getTabName());
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * pick item from the list
     * @param name hotel / location name
     */
    public void pick_destination(String name){
        String xpath = String.format("//li[child::div[.='%s']]",name);
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * click_date box
     * @param type checkin/checkout - depart/return - pickup/dropoff
     */
    public void click_date(String type){
        active_tab.setDateType(type);
        String xpath = String.format("//div[@id='%s']//child::input[contains(@name,'%s')]",
                active_tab.getTabName(),active_tab.getDateType());
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     *move to correct month
     * @param month
     * @param year
     */
    public void select_monthYear(String month, String year){
        String monthYear_slot = String.format("//div[@id='datepickers-container']/div[%s]//div[contains(.,'%s, %s')]",
                active_tab.getIndex(),month,year);

        active_tab.setMonth(month);
        boolean month_found = false;
        while(!month_found) {
            try {
                driver.findElement(By.xpath(monthYear_slot));
                month_found = true;
            } catch (Exception e) {
                click_next_month_button();
            }
        }
    }

    /**
     * select day
     * @param day
     */
    public void select_day(int day){

        String day_slot = String.format("//div[@id='datepickers-container']/div[%s]" +
                "//div[@data-date='%s'][@data-month='%s']",active_tab.getIndex(),day,active_tab.monthValue());

        driver.findElement(By.xpath(day_slot)).click();
    }

    /**
     * next button on calendar
     */
    public void click_next_month_button(){
        String xpath = String.format("//div[@id='datepickers-container']/div[%s]//div[@data-action='next']",active_tab.getIndex());
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * add person
     * @param person adult, child, infant
     */
    public void add_person(String person){
        active_tab.setPerson(person);
        String xpath = String.format("//div[@id='%s']//child::input[contains(@name,'%s')]//" +
                "following-sibling::span/button[.='+']",active_tab.getTabName(),active_tab.getPerson());
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     * remove person
     * @param person adult, child, infant
     */
    public void remove_person(String person){
        active_tab.setPerson(person);
        String xpath = String.format("//div[@id='%s']//child::input[contains(@name,'%s')]//" +
                "following-sibling::span/button[.='-']",active_tab.getTabName(),active_tab.getPerson());
        driver.findElement(By.xpath(xpath)).click();
    }

    /** boatsType, toursType
     *
     *
     */
    public void click_boatTour_type(){
        String xpath = String.format("//div[@id='%s']//div[@class='chosen-container chosen-container-single chosen-container-single-nosearch']/a", active_tab.getTabName().toLowerCase());
        driver.findElement(By.xpath(xpath)).click();
    }

    public void select_boatTour_Type(String typeName){
        String xpath = String.format(" //div[@id='%s']//ul/li[contains(text(),'%s')]",active_tab.getTabName().toLowerCase(), typeName);
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     *
     * @param tripType: Round Trip, One Way"
     */
    public void tripType(String tripType){
        String xpath = String.format("//label[.='%s']",tripType);
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     *
     * @param flightClass: Business, Economy, First
     */
    public void flightClass(String flightClass){
        driver.findElement(By.xpath("//div[@class='form-icon-left flightclass']")).click();
        String xpath = String.format("//li[.='%s']",flightClass);
        driver.findElement(By.xpath(xpath)).click();

    }

    /**
     *
     * @param locationType From, To, Pick up, Drop off
     * @param locationName toronto, london, ...
     */
    public void enter_location(String locationType, String locationName){
        String xpath = String.format("//div[@id='%s']//a[ancestor::div[label[contains(.,'%s')]]]"
                ,active_tab.getTabName().toLowerCase(),locationType);

        WebElement location = driver.findElement(By.xpath(xpath));
        Actions action = new Actions(driver);

        action.moveToElement(location).click().perform();
        action.pause(2000).perform();
        action.sendKeys(location,locationName).perform();
        action.pause(2000).perform();
    }

    /**
     * @param timeType  Depart, Return
     * @param timeValue 12 || 12:30     can be exact or partial
     *
     * this method will send the given timeValue inside the input box
     */
    public void enter_time(String timeType, String timeValue) {

        String xpath = String.format("//div[@id='cars']//a[ancestor::div[label[contains(.,'%s  Time')]]]", timeType);
        String xpath_result = xpath + "/..//ul/li[1]";

        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.xpath(xpath + "/..//input")).sendKeys(timeValue);
        driver.findElement(By.xpath(xpath_result)).click();
    }

    /**
     * @param timeType  Depart, Return
     * @param timeValue 12:00 format
     *
     * this method will directly select the give timeValue
     */
    public void select_time(String timeType, String timeValue) {
        String xpath = String.format("//div[@id='cars']//a[ancestor::div[label[contains(.,'%s  Time')]]]", timeType);
        String xpath_result = String.format(xpath + "/..//ul/li[contains(.,'%s')]",timeValue);

        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.xpath(xpath_result)).click();
    }

    public void enter_country(String toFrom, String countryName){
        active_tab.setToFrom(toFrom);
        String xpath = String.format("//label[.='%s Country']/.. ",toFrom);
        driver.findElement(By.xpath(xpath)).click();
        String countryXpath = String.format("//label[.='%s Country']/.. //input",toFrom);
        driver.findElement(By.xpath(countryXpath)).sendKeys(countryName);
        BrowserUtils.wait(1);
        String xpath2 = String.format("//label[.='%s Country']/.. //ul",active_tab.getToFrom());
        driver.findElement(By.xpath(xpath2)).click();

    }


    /**
     * submit/search button
     */
    public void submit_search() {
        String xpath = String.format("//div[@id='%s']//button[@type='submit']", active_tab.getTabName());
        driver.findElement(By.xpath(xpath)).click();
    }

    public void select_firstAvailable_result(){
        String xpath = "//div[@id='select2-drop']//ul/li[1]";
        driver.findElement(By.xpath(xpath)).click();
    }

    public List<String> search_result() {
        String xpath = "//div[@id='select2-drop']//ul/li";
        List<WebElement> elementList = driver.findElements(By.xpath(xpath));

        System.out.println(elementList.size());
        List<String> stringList = BrowserUtils.getTextFromWebElements(elementList);
        for (String each : stringList) {
            System.out.println(each);
        }
        return stringList;
    }

}
