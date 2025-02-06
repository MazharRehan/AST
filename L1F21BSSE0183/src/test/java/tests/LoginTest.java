package tests;

import base.BaseTest;
import config.ConfigReader;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.Index;
import pages.LoginPage;

import java.time.Duration;


public class LoginTest extends BaseTest {
    String username = ConfigReader.getProperty("username");
    String password = ConfigReader.getProperty("password");
    String indexUrl = ConfigReader.getProperty("indexURL");


    @Test
    public void loginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        loginPage.minimizeWindow();

        // Step 4: Retrieve all cookies after login
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitDuration));
        wait.until(ExpectedConditions.urlContains("index"));

        driver.get(indexUrl);
        Index index = new Index(driver);
        index.printCookies();
        index.printCookieDetails("orangehrm");
        System.out.println("Reason: This cookie is selected because it is the session cookie that is used to maintain the user's session.");

        // Step 7: Maximize the window again, perform a logout and then shut down the browser.
        driver.manage().window().maximize();
        index.logout();
        assert loginPage.verifyLogout(); // Verify that the user is logged out, if not, the test will fail.
        System.out.println("\nLogged out successfully");

        // Step 8: Try again to print the name and value of the cookie from Step 6 after a logout now.
        index.printCookieDetails("orangehrm");

        // Step 9: If it is still printing the name and the value, print the reason. Yes it still.
        System.out.println("\nReason: The cookie is still printing the name and value because the cookie is not deleted after logout. It is still present in the browser.");

        // Step 10: Print your Full name and complete Registration Number.
        System.out.println("\nFull Name: Muhammad Mazhar Rehan\nRegistration Number: L1F21BSSE0183");

    }
}
