package com.Draft.tests;

import com.Draft.Utility.BrowserUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHomePage extends AbstractTestBase {

    @Test // BTOR-1_AC#3 Verify home tab is functional (abdu)
    public void verify_home_tab() {
        //navigate to anypage
        homepage.navigateTo("company","Contact");
        homepage.click_home_tab();
        BrowserUtils.wait(1);
        String expectedTitle = "PHPTRAVELS | Travel Technology Partner";
        String actualTitle = homepage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles does not match");
    }

    // BTOR-1_AC#4 Verify company tab is functional (abdu)
    @Test
    public void verify_company_tab() {
        homepage.click_company_tab();
        BrowserUtils.wait(1);
        String expected_Style_Value = "display: block;";
        String actual_Style_Value = homepage.get_companyTab_Style_text();
        Assert.assertEquals(actual_Style_Value, expected_Style_Value, "style attribute does not match");
    }

    // BTOR-12_AC#1 Company tab - contact & about us module (abdu)
    @Test
    public void verify_company_sub_modules() {
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
    public void verify_account_tab() {

        homepage.click_account_tab();
        String expected = "MY ACCOUNT";
        String actual = homepage.get_account_text();
        Assert.assertEquals(actual, expected, "default text does not match");

    }

    @Test  //UserStory#1(BTOR-9) AC#7 - verify phoneNumber is visible & correct (+1-234-56789) (Ahmet)
    public void verify_phone_number() {

        //NOTE: The WebElement always involved a phone text and not just a phone number, so I had to add phone to my expected result.
        String actual = homepage.get_phone_number();
        String expected = "phone +1-234-56789";

       // Assert.assertTrue(driver.findElement(By.xpath(phoneNumber)).isDisplayed(), "Phone number is not visible");
        Assert.assertEquals(actual, expected, "phone number does not match");
    }

    @Test
    public void verify_phone_icon() {
        String actual = this.homepage.get_phone_icon_text();
        String expected = "phone";
        Assert.assertEquals(actual, expected, "Phone icon is not displayed");
    }

    @Test //UserStory#3(BTOR-21) AC#2 - Verify all footer texts - (Ahmet)
    public void verify_footer_texts()  {
        //List<WebElement> actualFooterTexts = driver.findElements(By.xpath("//footer[@id='footer']"));

        System.out.println("===================================");
        String [] expectedList = {"phone +1-234-56789\n" + "INFO@TRAVELAGENCY.COM\n" + "SUPPLIER\n" + "Supplier Sign Up\n" + "Supplier Login\n" + "COMPANY\n" +
                "Contact\n" + "How to Book\n" + "Booking Tips\n" + "About Us\n" + "SUPPORT\n" + "FAQ\n" + "Our Partners\n" + "Privacy Policy\n" + "Terms of Use\n" +
                "NEWSLETTER\n" + "Subsribe to get our latest updates and oeffers\n" + "SUBSCRIBE\n" + "Powered by PHPTRAVELS\n" + "© All Rights Reserved by PHPTRAVELS"};

        ArrayList<String> expected = new ArrayList<>(Arrays.asList(expectedList));
        ArrayList<String> actual = homepage.get_footer_texts();


        System.out.println("===================================");

    }

    @Test //UserStory#3(BTOR-22) AC#3 - verify subscription function with valid email - (Ahmet)
    public void verify_valid_subscription() {

        int randomNumber = (int) (Math.random() * 1000);
        //System.out.println("randomNumber = " + randomNumber); //testing random #
        String firstTime = "test" + randomNumber + "@gmail.com";
        System.out.println(firstTime);
        String inValid = "@gmail.com";
        String secondTime = "test";

        homepage.click_subscribe(firstTime);
        String actual = homepage.get_subscription_text();
        String expected = "Subscribed Successfully";

        Assert.assertEquals(actual,expected,"default text does not match");
    }
//
    @Test// User story#1(BTOR-1) AC#6-Verify language bar visible & default language selected "ENGLISH" (Elvira)
    public void verify_default_language() {
        String defaultSelectedLanguage = "ENGLISH";
        String actualSelectedLanguage = homepage.get_default_language();
        Assert.assertEquals(defaultSelectedLanguage, actualSelectedLanguage, "default language ENGLISH is not selected");
    }

    @Test //User Story#3(BTOR-12) AC#3 - Language DropDown - verify each language (Elvira)
    public void veriy_each_language() {
        homepage.click_language_bar();
        List<WebElement> languageDropdownList = homepage.get_language_list();

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

    @Test //User Story#1 (BTOR-1) //AC#5 verify currency is visible and default selected (Zeliha)
    public void verify_default_currency() {
            String defaultSelectedCurrency = "USD";
            String actualSelectedCurrency = homepage.get_default_currency();
            Assert.assertTrue(defaultSelectedCurrency.equals(actualSelectedCurrency), "Currency default USD is not displayed ");
                }

    @Test //User Story#3(BTOR-12) AC#2 - Currency DropDown - verify each currency(Zeliha)
    public void verify_each_currency () {
        homepage.click_currency_tab();
        List<WebElement> actualCurrencyList = homepage.get_currency_list();

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
    public void verify_footer_links() {
        String expected = "supplier Registration";
        String actual= homepage.click_footer_links("Supplier Sign Up");
        Assert.assertEquals(expected,actual);
    }

    @Test //Dilyar //manual
    public void verify_login_Tab(){
        homepage.click_login_tab();
        String expected = "Login";
        String actual = homepage.getTitle();
        Assert.assertTrue(!actual.equals(expected),"Page title does not match");
    }

    @Test //Dilyar
    public void verify_signUp_tab(){
        homepage.click_signUp_tab();
        String expected = "Register";
        String actual = homepage.getTitle();
        Assert.assertTrue(actual.equals(expected),"Page title does not match");
    }
}
