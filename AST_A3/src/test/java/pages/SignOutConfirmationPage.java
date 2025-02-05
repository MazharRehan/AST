package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignOutConfirmationPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//div[contains(text(), 'You signed out of your account')]")
    private WebElement signOutMessage;

    public SignOutConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Verify the sign-out confirmation message
    public boolean isSignOut() {
        return wait.until(ExpectedConditions.visibilityOf(signOutMessage)).isDisplayed();
    }

}
