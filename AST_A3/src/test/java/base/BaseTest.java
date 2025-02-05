/*
// Author: Muhammad Mazhar Rehan
// Date: 7th January 2025 2:36 AM

-----------------------------------------------------
Muhammad Mazhar Rehan -     L1F21BSSE0183
Usama Bin Naseer      -     L1F21BSSE0540
Zain Bin Imran        -     L1F21BSSE0539
-----------------------------------------------------
 */

package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;



public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Setup method to initialize WebDriver and navigate to base URL
    @BeforeMethod
    public void setup() {
        // Fetching WebDriver path and base URL from config
        String driverPath = ConfigReader.getProperty("webdriverPath"); // Fetching driver path dynamically
        String outlookAppUrl = ConfigReader.getProperty("signInPageUrl");

        if (driverPath != null && !driverPath.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        } else {
            throw new RuntimeException("WebDriver path is not specified in config.properties file.");
        }

        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize WebDriverWait with a configurable timeout
        int explicitWaitTime = Integer.parseInt(ConfigReader.getProperty("explicitWait"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWaitTime));

        // Navigate to the base URL
        if (!outlookAppUrl.isEmpty()) {
            driver.get(outlookAppUrl);
        } else {
            throw new RuntimeException("Base URL is not specified in config.properties file.");
        }
    }

    // Tear down method to quit the WebDriver after tests
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
