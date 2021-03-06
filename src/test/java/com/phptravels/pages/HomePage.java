package com.phptravels.pages;
import com.phptravels.Utility.BrowserUtils;
import com.phptravels.Utility.GlobalDataUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    @FindBy(xpath = "//ul[@class='select2-results']/li[1]//li")
    private List<WebElement> list_by_hotel;
    @FindBy(xpath = "//ul[@class='select2-results']/li[2]//li" )
    private List<WebElement> list_by_location;
    @FindBy(xpath = "//div[@id='hotels']//span[@class='select2-chosen']")
    private WebElement current_hotel_selection;


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

        active_tab.setActiveModule("Destination");
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
     *
     * @param name enter input value
     */
    public void enter_dest(String name){
        String xpath = String.format("//div[@id='%s']//a[ancestor::div[label[contains(.,'%s')]]]",
                active_tab.getTabName(),active_tab.getActiveModule());
        driver.findElement(By.xpath(xpath)).sendKeys(name);

        active_tab.setCurrentDest(name);

    }

    /**
     *
     * @return text from all list items
     */
    public List<String> get_search_result(){
        String xpath = String.format("//li[child::div[contains(.,'%s')]]",active_tab.getCurrentDest());
        List<WebElement> searchList = driver.findElements(By.xpath(xpath));
        List<String> searchTxt = new ArrayList<>();
        for (WebElement each: searchList){
            searchTxt.add(each.getText());
        }
        return searchTxt;
    }

    public String invalid_result(){
        String msg = "";
        try {
            driver.findElement(By.xpath(String.format("//li[child::div[contains(.,'%s')]]",active_tab.getCurrentDest())));
        }catch (Exception e){
            WebElement invalid_msg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ul[.='No matches found']")));
            msg = invalid_msg.getText();
        }
        return msg;
    }

    /**
     *
     * @return true if destination dropdown menu displays
     */
    public boolean destination_list_Display(){
        By select2_list = By.xpath("//ul[@class='select2-results']/li[1]");
        wait.until(ExpectedConditions.presenceOfElementLocated(select2_list));
        return driver.findElement(select2_list).isDisplayed();
    }

    /**
     * pick random item by hotel name
     */
    public void pick_random_by_hotel(){
        Random rand = new Random();
        int index = rand.nextInt(list_by_hotel.size());
        wait.until(ExpectedConditions.elementToBeClickable(list_by_hotel.get(index)));
        active_tab.setRand_dest_txt(list_by_hotel.get(index).getText());
        list_by_hotel.get(index).click();
    }

    /**
     * pick random item by location
     */
    public void pick_random_by_location(){
        Random rand = new Random();
        int index = rand.nextInt(list_by_location.size());
        wait.until(ExpectedConditions.elementToBeClickable(list_by_location.get(index)));
        active_tab.setRand_dest_txt(list_by_location.get(index).getText());
        list_by_location.get(index).click();
    }

    /**
     *
     * @return text from randomly selected item
     */
    public String getRand_dest_txt(){
        return active_tab.getRand_dest_txt();
    }

    /**
     *
     * @return text from already selected item
     */
    public String getSelected_dest_txt(){
        return current_hotel_selection.getText();
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
     * move to element
     * @param name
     */
    public void move_to_dest(String name){
        WebElement lastItem = driver.findElement(By.xpath(String.format("//li[child::div[contains(.,'%s')]]",name)));
        Actions action = new Actions(driver);
        action.moveToElement(lastItem).perform();
        action.click().perform();
    }

    /**
     * scroll to element
     * @param name
     */
    public void scroll_to_dest(String name){
        Actions action = new Actions(driver);
        String xpath = String.format("//div[@id='%s']//" +
                "child::a[span[@class='select2-chosen']]",active_tab.getTabName());
        WebElement given_item = driver.findElement(By.xpath(String.format("//li[child::div[contains(.,'%s')]]",name)));
        do {
            action.sendKeys(driver.findElement(By.xpath(xpath)),Keys.DOWN).perform();
        }while (!given_item.getAttribute("class").contains("select2-highlighted"));
        action.click(given_item).perform();
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
    public void add_people(int num, String person){
        for (int i = 0; i < num; i++) {
            add_person(person);
        }
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
    public void remove_people(int num, String person){
        for (int i = 0; i < num; i++) {
            remove_person(person);
        }
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
