package com.Draft.Test;

import com.phptravels.Util.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class  AbstractTestBase {

    WebDriver driver;
    String browserType = "chrome";
    String URL = "https://www.phptravels.net/";

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }




}
