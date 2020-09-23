package com.phptravels.tests;

import com.phptravels.Utility.Driver;
import com.phptravels.pages.HomePage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class  AbstractTestBase {

    HomePage homepage;

    @BeforeMethod
    public void setUp() {
        String URL = "https://www.phptravels.net/";
        Driver.getDriver().get(URL);
        //Driver.getDriver().manage().window().maximize();
        homepage = new HomePage();
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }




}
