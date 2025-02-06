package pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class Index {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@class='oxd-userdropdown-tab']")
    WebElement userDropdown;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logoutButton;

    public Index(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void printCookies() {
        // Step 4: Retrieve all cookies after login
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("\nRetrieved Cookies:");

        // Step 5: Print cookie details including name, value, domain, Path, and Expiry
        for (Cookie cookie : cookies) {
            System.out.println("Name: " + cookie.getName());
            System.out.println("Value: " + cookie.getValue());
            System.out.println("Domain: " + cookie.getDomain());
            System.out.println("Path: " + cookie.getPath());
            System.out.println("Expiry: " + cookie.getExpiry());
            System.out.println("-----------------------------");
        }
    }

    // Step 6: Get and then print the name and value of a cookie of your own choice. Also print the reason about its selection.
    public void printCookieDetails(String cookieName) {
        Cookie cookie = driver.manage().getCookieNamed(cookieName);
        assert cookie != null;
        System.out.println("\n-----------------------------");
        System.out.println("Name: " + cookie.getName());
        System.out.println("Value: " + cookie.getValue());
//        System.out.println("Domain: " + cookie.getDomain());
//        System.out.println("Path: " + cookie.getPath());
//        System.out.println("Expiry: " + cookie.getExpiry());
        System.out.println("-----------------------------");
    }

    // Step 7: perform a logout and then shut down the browser.
    public void logout() {
        wait.until(ExpectedConditions.visibilityOf(userDropdown));
        userDropdown.click();
        logoutButton.click();
    }

}
