package com.Draft.pages;


import com.Draft.Utility.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AbstractPageBase {

    protected WebDriver driver = Driver.getDriver();

public AbstractPageBase(){
    PageFactory.initElements(driver,this);

}


}

