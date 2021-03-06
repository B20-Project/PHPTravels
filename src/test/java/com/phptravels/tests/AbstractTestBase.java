package com.phptravels.tests;

import com.phptravels.Utility.ConfigurationReader;
import com.phptravels.Utility.Driver;
import com.phptravels.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public abstract class  AbstractTestBase {

    HomePage homepage;

    @BeforeMethod
    public void setUp() {
        String URL = ConfigurationReader.getProperty("phptravelsUrl");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        homepage = new HomePage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }




}
