package tests;

import base.BaseTest;
import config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class ComposeEmailTest extends BaseTest {

    @Test
    public void testSaveEmailToDrafts() {
        // Step 1: Launch Browser and Open Login Page

        // Initialize the required pages
        SignInPage signInPage = new SignInPage(driver);

        System.out.println("Title: " + driver.getTitle());

        // Using Values from config.properties
        String signInPageUrl = ConfigReader.getProperty("signInPageUrl");
        String staySignedInPageUrl = ConfigReader.getProperty("staySignedInPageUrl");
        String homePageUrl = ConfigReader.getProperty("homePageUrl");
        String draftsPageUrl = ConfigReader.getProperty("draftsPageUrl");
        String signOutConfirmationPageUrl = ConfigReader.getProperty("signOutConfirmationPageUrl");

        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        String testEmailSubject = ConfigReader.getProperty("testEmailSubject");
        String testEmailBody = ConfigReader.getProperty("testEmailBody");
        String recipientEmail = ConfigReader.getProperty("recipientEmail");
        String ccEmail = ConfigReader.getProperty("ccEmail");


        // Step 2: Enter Credentials and Log In
        signInPage.login();

        driver.get(staySignedInPageUrl);
        StaySignedInPage staySignedInPage = new StaySignedInPage(driver);
        staySignedInPage.isStaySignedIn(true);

        System.out.println("Outlook Opened");

        // Step 3: Compose a new email
        driver.get(homePageUrl);
        OutlookHomePage outlookHomePage = new OutlookHomePage(driver);
        outlookHomePage.composeEmail(recipientEmail, ccEmail, testEmailSubject, testEmailBody);

        System.out.println("Email Composed");

        // Step 4: Verify the email is saved in drafts
        driver.get(draftsPageUrl);
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openDraftsFolder();
        Assert.assertTrue(draftsPage.isEmailSavedInDrafts(), "Draft email not found!");

        System.out.println("Email Saved to Drafts");

        // Step 5: Sign out of the application
        driver.get(homePageUrl);
        SignOutPage signOutPage = new SignOutPage(driver);
        signOutPage.signOut();

        // Verify the user is signed out
        driver.get(signOutConfirmationPageUrl);
        SignOutConfirmationPage signOutConfirmationPage = new SignOutConfirmationPage(driver);
        Assert.assertTrue(signOutConfirmationPage.isSignOut(), "Sign out failed!");

        System.out.println("User Signed Out");
        System.out.println("Test Completed");
    }
}
