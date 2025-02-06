package base;

import config.ConfigReader;
import io.restassured.RestAssured;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    public long waitDuration;

    @BeforeClass
    public void setUp() throws InterruptedException {
        String loginUrl = ConfigReader.getProperty("loginUrl");
        waitDuration = Long.parseLong(ConfigReader.getProperty("waitDuration"));

        // Step 1: Set up WebDriver and apply an implicit wait
        RestAssured.baseURI = ConfigReader.getProperty("loginUrl");
        System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("chromeDriverPath"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitDuration));

        // Step 2: Navigate to OrangeHRM’s and print that it has been launched.
        driver.get(loginUrl);
        System.out.println("OrangeHRM launched");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}