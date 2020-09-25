package com.phptravels.pages;


import com.phptravels.Utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPageBase {

    protected WebDriver driver = Driver.getDriver();
    protected WebDriverWait wait = new WebDriverWait(driver,10);

    public AbstractPageBase() {
        PageFactory.initElements(driver, this);
    }

    /**Get Current Title
     *
     * @return
     */
    public String getTitle(){
        return driver.getTitle();
    }

    /**NavigateTo
     *
     * @param tabName currency, language, account, company
     * @param moduleName Login, Sign Up, Vietnamese, Russian, English, Arabic, Farsi
     *                                   Turkish, French, Spanish, German
     *                                   USD, GBP, SAR, EUR, PKR, KWD, JPY,
     *                                   INR, CNY, TRY, RUB
     *                   company - About Us, Contact
     *                   "//button[contains(text(),'%s')]" //dynamic xpath
     */
    public void navigateTo(String tabName,String moduleName){

        String tabNameXpath="";
        String moduleXpath="";
        if (!tabName.equals("company")) {
            if (tabName.equals("account")) {
                tabName = "login dropdown-tab";
            } else if (tabName.equals("language")) {
                moduleName = " " + moduleName;
            }

            tabNameXpath = String.format("//div[@class='dropdown dropdown-%s']",tabName);
            moduleXpath = String.format("//a[.='%s']",moduleName);

        }else{
            tabNameXpath = String.format("//a[.='%s']",tabName);
            moduleXpath = String.format("//a[.='%s']",moduleName);
        }

        WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));
        WebElement moduleElement = driver.findElement(By.xpath(moduleXpath));

        Actions actions = new Actions(driver);

        actions.moveToElement(tabElement).
                pause(1000).
                click(tabElement).
                pause(1000).
                click(moduleElement).
                build().perform();

    }

    /**NavigateTotab
     *
     * @param tabName home, currency, language, account, company
     */
    public void navigateTo(String tabName){
        String tabNameXpath="";

        if (!tabName.equals("company")) {
            if (tabName.equals("account")) {
                tabName = "login dropdown-tab";
            }
            tabNameXpath = String.format("//div[@class='dropdown dropdown-%s']",tabName);

        }else{
            tabNameXpath = String.format("//a[.='%s']",tabName);
        }

        WebElement tabElement = driver.findElement(By.xpath(tabNameXpath));

        Actions actions = new Actions(driver);

        actions.moveToElement(tabElement).
                pause(1000).
                click(tabElement).
                build().perform();
    }

}

