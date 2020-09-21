package com.Draft.pages;


import com.Draft.Utility.Driver;
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

    public void navigateToHome(){

        driver.findElement(By.xpath("//a[.='Home']")).click();

    }
    /**
     *
     * @param tabName currency, language, account, company
     * @param moduleName Login, Sign Up, Vietnamese, Russian, English, Arabic, Farsi
     *                                   Turkish, French, Spanish, German
     *                                   USD, GBP, SAR, EUR, PKR, KWD, JPY,
     *                                   INR, CNY, TRY, RUB
     *                   company - About Us, Contact
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

            tabNameXpath = "//div[@class='dropdown dropdown-" + tabName + "']";
            moduleXpath = "//a[.='" + moduleName + "']";

        }else{
            tabNameXpath = "//a[.='"+tabName+"']";
            moduleXpath = "//a[.='"+moduleName+"']";
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





}

