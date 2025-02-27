package base;

import io.restassured.RestAssured;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.Set;

public class BaseTest {
    protected WebDriver driver;
    protected Cookie sessionCookie;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Step 1: Set up WebDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); // Create a new instance of ChromeDriver
        driver.manage().window().maximize();

        // Step 2: Navigate to the Swagger Petstore Login Page
        driver.get("https://petstore.swagger.io/#/user/login");

        // Step 3: Log in with username and password (passed as query parameters) and then minimize the window
        String username = "username";  // Example username
        String password = "password";  // Example password
        driver.get("https://petstore.swagger.io/v2/user/login?username=" + username + "&password=" + password);
        Thread.sleep(2000);
        driver.manage().window().minimize();

        // Step 4: Retrieve all the cookies after login
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Retrieved Cookies:");
//        System.out.println(cookies); // Print all cookies

        // Step 5: Print cookie details including name, value, domain, Path, and Expiry
        for (Cookie cookie : cookies) {
            System.out.println("Name: " + cookie.getName());
            System.out.println("Value: " + cookie.getValue());
            System.out.println("Domain: " + cookie.getDomain());
            System.out.println("Path: " + cookie.getPath());
            System.out.println("Expiry: " + cookie.getExpiry());
            System.out.println("-----------------------------");
        }

        // Step 6: Use the desired cookie for authenticated API call
        sessionCookie = driver.manage().getCookieNamed("userId");
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        //assert sessionCookie != null; // It checks whether the variable sessionCookie is not null.

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}