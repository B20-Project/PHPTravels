package com.Draft.tests;

import com.Draft.Utility.Driver;
import com.Draft.pages.HomePage;
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
