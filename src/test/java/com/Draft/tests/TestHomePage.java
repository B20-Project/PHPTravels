package com.Draft.tests;

import com.Draft.Utility.BrowserUtils;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestHomePage extends AbstractTestBase {


    @Test // BTOR-1_AC#3 Verify home tab is functional (abdu)
    public void homeTab_Verification() {
        homepage.navigateTo("company","Contact");
        homepage.click_home_tab();
        BrowserUtils.wait(1);
        String expectedTitle = "PHPTRAVELS | Travel Technology Partner";
        String actualTitle = homepage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles does not match");
    }

    // BTOR-1_AC#4 Verify company tab is functional (abdu)
    @Test
    public void company_DropDown_Verification() {
        homepage.click_company_tab();
        BrowserUtils.wait(1);
        String expected_Style_Value = "display: block;";
        String actual_Style_Value = homepage.get_companyTab_Style_text();
        Assert.assertEquals(actual_Style_Value, expected_Style_Value, "style attribute does not match");
    }

    // BTOR-12_AC#1 Company Module - contact & about us tabs (abdu)
    @Test
    public void company_DropDown_SubMenu_Verification() {
        homepage.navigateTo("company","Contact");
        String expected_title_Value = "Contact";
        String actual_title_Value = homepage.getTitle();
        Assert.assertEquals(actual_title_Value, expected_title_Value, "Contact page title does not match");

        homepage.navigateToHome();
        BrowserUtils.wait(1);
        homepage.navigateTo("company","About Us");
        String expected_title = "About Us";
        String actual_title = homepage.getTitle();
        Assert.assertEquals(actual_title, expected_title, "About US page title does not match");
    }

    @Test //AC#9 Dilyar
    public void account_tab_Verification() {

        homepage.click_account_tab();
        String expected = "MY ACCOUNT";
        String actual = homepage.get_account_text();
        Assert.assertEquals(actual, expected, "default text does not match");

    }

    @Test  //UserStory#1(BTOR-9) AC#7 - verify phoneNumber is visible & correct (+1-234-56789) (Ahmet)
    public void User_Story_1_AC7() {

        //NOTE: The WebElement always involved a phone text and not just a phone number, so I had to add phone to my expected result.
        String actual = homepage.verify_number();
        String expected = "phone +1-234-56789";

       // Assert.assertTrue(driver.findElement(By.xpath(phoneNumber)).isDisplayed(), "Phone number is not visible");
        Assert.assertEquals(actual, expected, "phone number does not match");
    }

    @Test
    public void User_Story_1_AC8() {
        String actual = this.homepage.verify_phoneIcon();
        String expected = "phone";
        Assert.assertEquals(actual, expected, "Phone icon is not displayed");
    }


    @Test //UserStory#3(BTOR-21) AC#2 - Verify all footer texts - (Ahmet)
    public void User_Story_3_AC2()  {
        //List<WebElement> actualFooterTexts = driver.findElements(By.xpath("//footer[@id='footer']"));
        List<WebElement> actualFooterTexts = homepage.verifyFooterTexts();

        System.out.println("===================================");
        String [] expectedList = {"phone +1-234-56789\n" + "INFO@TRAVELAGENCY.COM\n" + "SUPPLIER\n" + "Supplier Sign Up\n" + "Supplier Login\n" + "COMPANY\n" +
                "Contact\n" + "How to Book\n" + "Booking Tips\n" + "About Us\n" + "SUPPORT\n" + "FAQ\n" + "Our Partners\n" + "Privacy Policy\n" + "Terms of Use\n" +
                "NEWSLETTER\n" + "Subsribe to get our latest updates and oeffers\n" + "SUBSCRIBE\n" + "Powered by PHPTRAVELS\n" + "© All Rights Reserved by PHPTRAVELS"};

        int i = 0;
        for (WebElement eachElement : actualFooterTexts) {
            System.out.println(eachElement.getText());
            Assert.assertEquals(expectedList [i],eachElement.getText(), "Actual List of Footer Texts do not match expected list of footer lists" );
            i++;
        }
        System.out.println("===================================");

    }

    @Test //UserStory#3(BTOR-22) AC#3 - verify subscription function with valid email - (Ahmet)
    public void User_Story_3_AC3() throws InterruptedException {

        int randomNumber = (int) (Math.random() * 1000);
        //System.out.println("randomNumber = " + randomNumber); //testing random #
        String firstTime = "test" + randomNumber + "@gmail.com";
        System.out.println(firstTime);
        String inValid = "@gmail.com";
        String secondTime = "test";

        homepage.subscribe(firstTime);
        String actual = homepage.verify_subscribe();
        String expected = "Subscribed Successfully";

        Assert.assertEquals(actual,expected,"default text does not match");
    }
//
    @Test// User story#1(BTOR-1) AC#6-Verify language bar visible & default language selected "ENGLISH" (Elvira)
    public void language_bar() {
        String defaultSelectedLanguage = "ENGLISH";
        String actualSelectedLanguage = homepage.verify_default_language();
        Assert.assertEquals(defaultSelectedLanguage, actualSelectedLanguage, "default language ENGLISH is not selected");
    }

    @Test //User Story#3(BTOR-12) AC#3 - Language DropDown - verify each language (Elvira)
    public void language_dropDown() {
        homepage.click_language_bar();
        List<WebElement> languageDropdownList = homepage.verify_each_language();

       String[] expectedListOfLanguages = {"Vietnamese","Russian","English","Arabic","Farsi","Turkish","French","Spanish","German"};
        int i = 0;
        for(WebElement each : languageDropdownList){
            System.out.println(each.getText());

           Assert.assertEquals(each.getText(), expectedListOfLanguages[i]);
           i++;
       }
        int sizeOfExpectedListOfLanguages = expectedListOfLanguages.length;
        int sizeOfListOfLanguages = languageDropdownList.size();
      Assert.assertTrue(sizeOfExpectedListOfLanguages == sizeOfListOfLanguages);
   }
//
    @Test //User Story#1 (BTOR-1) //AC#5 verify currency is visible and default selected (Zeliha)

    public void global_currency_bar() {
            String defaultSelectedCurrency = "USD";
            String actualSelectedCurrency = homepage.verify_default_currency();
            Assert.assertTrue(defaultSelectedCurrency.equals(actualSelectedCurrency), "Currency default USD is not displayed ");
                }

    @Test //User Story#3(BTOR-12) AC#2 - Currency DropDown - verify each currency(Zeliha)
    public void currency_dropdown () {
        homepage.currency_dropdown_click();
        List<WebElement> actualCurrencyList = homepage.verify_currency_list();

        String[] expectedCurrencyList = {"USD", "GBP", "SAR", "EUR", "PKR", "KWD", "JPY", "INR", "CNY", "TRY", "RUB"};

        int index = 0;

        for (WebElement eachCurrency : actualCurrencyList) {
            System.out.println(eachCurrency.getText());
            Assert.assertTrue(expectedCurrencyList[index].equals(eachCurrency.getText()));
            index++;
        }

        int sizeOfExpectedCurrencyList = expectedCurrencyList.length;
        int sizeOfActualCurrencyList = actualCurrencyList.size();

        Assert.assertTrue(sizeOfExpectedCurrencyList == sizeOfActualCurrencyList);
    }


    @Test//Arpat --> User Story #3 AC#1 Verify all footer Links
    public void footerLinks() {
        String expected = "supplier Registration";
        String actual= homepage.click_footer_links("Supplier Sign Up");
        Assert.assertEquals(expected,actual);
    }

    @Test //Dilyar //manual
    public void login_Tab(){
        homepage.click_login_tab();
        String expected = "Login";
        String actual = homepage.getTitle();
        Assert.assertTrue(!actual.equals(expected),"Page title does not match");
    }

    @Test //Dilyar
    public void signUp_tab(){
        homepage.click_signUp_tab();
        String expected = "Register";
        String actual = homepage.getTitle();
        Assert.assertTrue(actual.equals(expected),"Page title does not match");
    }

    @Test //practice
    public void TestNavigateToMethod(){
        homepage.navigateTo("language","Russian");
        BrowserUtils.wait(2);
        homepage.navigateToHome();
        BrowserUtils.waitForLoad();
    }

    @Test //practice
    public void TestsearchFor(){

        BrowserUtils.wait(3);
        homepage.searchFor("flights");
        BrowserUtils.wait(3);
        homepage.searchFor("cars");
        BrowserUtils.wait(3);
        homepage.searchFor("boats");
        BrowserUtils.wait(3);

    }

}
