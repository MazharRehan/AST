package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    // Test login functionality
    @Test
    public void testLogin() {
        HomePage homePage = new HomePage(driver); // Initialize HomePage;
        LoginPage loginPage= new LoginPage(driver); // Initialize LoginPage;

        // Using Values from config.properties
        String loginUrl = ConfigReader.getProperty("loginUrl");

        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        String query = ConfigReader.getProperty("searchQuery");

        homePage.search(query); // Search for "dress"


        driver.navigate().to(loginUrl);
        loginPage.login(email, password); // Perform login
        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"), "Login failed!"); // Assert login success
    }
}
