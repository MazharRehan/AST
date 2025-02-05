package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignOutPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "mectrl_main_trigger") // account profile button
    private WebElement accountButton;

    @FindBy(xpath = "//a[text()='Sign out']") // Sign out" button
    private WebElement signOutButton;

    @FindBy(xpath = "//h1[contains(text(),'Sign in')]") // Locator to confirm the sign-in page appears
    private WebElement signInPageHeading;

    public SignOutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Method to perform sign out
    public void signOut() {
        wait.until(ExpectedConditions.visibilityOf(accountButton)).click(); // Click account profile button
        wait.until(ExpectedConditions.visibilityOf(signOutButton)).click(); // Click sign out button
    }

}
