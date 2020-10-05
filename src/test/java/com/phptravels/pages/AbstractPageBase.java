package com.phptravels.pages;


import com.phptravels.Utility.Driver;
import com.phptravels.Utility.GlobalDataUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPageBase {

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

    /**
     *
     * @param dropdown currency, language, account
     */
    public void select_header_dropdown(String dropdown){
        if (dropdown.equalsIgnoreCase("account")){
            dropdown="login";
        }
        String xpath = String.format("//div[contains(@class,'%s')]/a",dropdown);
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     *
     * @param selection all languages, all currency, Login Sign Up
     */
    public void select_header_dropdown_item(String selection){
        String xpath = String.format("//div[contains(@class,'dropdown-menu dropdown-menu-right')]//" +
                "a[contains(.,'%s')]",selection);
        driver.findElement(By.xpath(xpath)).click();
    }

    /**
     *
     * @param tab home, company, contact, about us
     */
    public void navigateTo(String tab){
        String xpath = String.format("//div[@class='header-nav']//a[.='%s']",tab);
        driver.findElement(By.xpath(xpath)).click();
    }
}

