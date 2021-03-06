package com.phptravels.tests;

import com.phptravels.Utility.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHomePage extends AbstractTestBase {

    @Test
    public void verify_home_tab() {
        //navigate to anypage
        homepage.navigateTo("company");
        homepage.navigateTo("Contact");
        homepage.navigateTo("Home");
        String expectedTitle = "PHPTRAVELS | Travel Technology Partner";
        String actualTitle = homepage.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Titles does not match");
    }

    @Test
    public void verify_default_language() {
        String defaultSelectedLanguage = "ENGLISH";
        String actualSelectedLanguage = homepage.get_default_language();
        Assert.assertEquals(defaultSelectedLanguage, actualSelectedLanguage, "default language ENGLISH is not selected");
    }

    @Test
    public void verify_each_language() {
        String[] expectedListOfLanguages = {"Vietnamese", "Russian", "English", "Arabic", "Farsi", "Turkish", "French", "Spanish", "German"};
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(expectedListOfLanguages));
        homepage.select_header_dropdown("language");
        ArrayList<String> actual = homepage.get_language_list();

        if (expected.size() != actual.size()) {
            throw new RuntimeException("expected size does not match actual size");
        }
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(actual.get(i), expected.get(i), actual.get(i) + " does not match with expected");
        }
    }

    @Test
    public void verify_default_currency() {
        String defaultSelectedCurrency = "USD";
        String actualSelectedCurrency = homepage.get_default_currency();
        Assert.assertTrue(defaultSelectedCurrency.equals(actualSelectedCurrency), "Currency default USD is not displayed ");
    }

    @Test
    public void verify_each_currency() {
        String[] expectedCurrencyList = {"USD", "GBP", "SAR", "EUR", "PKR", "KWD", "JPY", "INR", "CNY", "TRY", "RUB"};

        ArrayList<String> expected = new ArrayList<>(Arrays.asList(expectedCurrencyList));
        homepage.select_header_dropdown("currency");
        ArrayList<String> actual = homepage.get_currency_List();

        if (expected.size() != actual.size()) {
            throw new RuntimeException("expected size does not match actual size");
        }
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(actual.get(i), expected.get(i), actual.get(i) + " does not match with expected");
        }
    }

    @Test
    public void verify_account_tab() {

        homepage.select_header_dropdown("Account");
        String expected = "MY ACCOUNT";
        String actual = homepage.get_account_text();
        Assert.assertEquals(actual, expected, "default text does not match");

    }

    @Test
    public void verify_login_Tab() {
        homepage.select_header_dropdown("account");
        BrowserUtils.wait(2);
        homepage.select_header_dropdown_item("Login");
        String expected = "Login";
        BrowserUtils.wait(1);
        String actual = homepage.getTitle();
        Assert.assertEquals(actual, expected, "Page title does not match");
    }

    @Test
    public void verify_signUp_tab() {
        homepage.select_header_dropdown("account");
        homepage.select_header_dropdown_item("Sign Up");
        BrowserUtils.wait(1);
        String expected = "Register";
        String actual = homepage.getTitle();
        Assert.assertTrue(actual.equals(expected), "Page title does not match");
    }

    @Test
    public void verify_company_tab() {
        homepage.navigateTo("company");
        BrowserUtils.wait(1);
        String expected_Style_Value = "display: block;";
        String actual_Style_Value = homepage.get_companyTab_Style_text();
        Assert.assertEquals(actual_Style_Value, expected_Style_Value, "style attribute does not match");
    }

    @Test
    public void verify_company_sub_modules() {
        homepage.navigateTo("company");
        homepage.navigateTo("Contact");
        String expected_title_Value = "Contact";
        String actual_title_Value = homepage.getTitle();
        Assert.assertEquals(actual_title_Value, expected_title_Value, "Contact page title does not match");

        homepage.navigateTo("Home");
        homepage.navigateTo("company");
        homepage.navigateTo("About Us");
        String expected_title = "About Us";
        String actual_title = homepage.getTitle();
        Assert.assertEquals(actual_title, expected_title, "About US page title does not match");
    }

    @Test
    public void verify_phone_number() {
        String actual = homepage.get_phone_number();
        String expected = "phone +1-234-56789";
        Assert.assertEquals(actual, expected, "phone number does not match");
    }

    @Test
    public void verify_phone_icon() {
        String actual = this.homepage.get_phone_icon_text();
        String expected = "phone";
        Assert.assertEquals(actual, expected, "Phone icon is not displayed");
    }

    @Test
    public void verify_eachText_footer() {
        String[] expectedList = {"INFO@TRAVELAGENCY.COM", "SUPPLIER", "Supplier Sign Up", "Supplier Login", "COMPANY", "Contact", "How to Book", "Booking Tips", "About Us", "SUPPORT", "FAQ", "Our Partners", "Privacy Policy", "Terms of Use", "", "", "", "", "", "", "", "PHPTRAVELS"};
        System.out.println(homepage.get_footer_texts());
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(expectedList));
        ArrayList<String> actual = homepage.get_footer_texts();

        Assert.assertEquals(actual, expected, "footer texts does not match");
    }

    /**
     * we will be utilizing an excel sheet that has all the expected
     * text you need to test this method with.
     */
    @Test
    public void verify_footer_links() {
        String expected = "supplier Registration";
        String actual = homepage.click_footer_links("Supplier Sign Up");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void Hotels_List_Display(){
        homepage.searchFor("Hotels");
        homepage.click_destination();
        boolean isDisplayed = homepage.destination_list_Display();
        Assert.assertTrue(isDisplayed,"list is not displayed");
    }

    @Test
    public void pick_any_hotel(){
        homepage.searchFor("Hotels");
        homepage.click_destination();
        homepage.pick_random_by_hotel();

        String actual = homepage.getSelected_dest_txt();
        String expected = homepage.getRand_dest_txt();
        System.out.println("actual = " + actual);
        System.out.println("expected = " + expected);
        Assert.assertEquals(actual,expected,"selection does not match");
    }

    @Test
    public void verify_dest_filter(){
        homepage.searchFor("Hotels");
        homepage.click_destination();
        homepage.enter_dest("London");
        List<String> searchResult = homepage.get_search_result();

        String expected = "London";
        for (String each: searchResult){
            System.out.println("each = " + each);
            Assert.assertTrue(each.contains(expected),"search result does not match");
        }
    }

    @Test
    public void move_to_dest(){
        homepage.searchFor("Hotels");
        homepage.click_destination();
        homepage.move_to_dest("Singapore, Singapore");
    }

    @Test
    public void scroll_to_dest(){
        homepage.searchFor("Hotels");
        homepage.click_destination();
        homepage.scroll_to_dest("Singapore, Singapore");
    }

    @Test
    public void invalid_dest(){
        homepage.searchFor("Hotels");
        homepage.click_destination();
        homepage.enter_dest("fdasf");
        String actual = homepage.invalid_result();
        String expected = "No matches found";
        System.out.println("actual = " + actual);
        System.out.println("expected = " + expected);
        Assert.assertEquals(actual,expected,"no match found msg does not match");
    }

}
