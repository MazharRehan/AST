package pages;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

public class SignInPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "i0116")
    private WebElement emailField;

    @FindBy(id = "idSIButton9")
    private WebElement nextButton;

    @FindBy(id = "i0118")
    private WebElement passwordField;

    @FindBy(id = "idSIButton9")
    private WebElement loginButton;

    @FindBy(id = "idSIButton9")
    private WebElement staySignedIn;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        isAt();
    }

    public void isAt() {
        Assert.assertTrue(Objects.requireNonNull(driver.getTitle()).contains("Sign in"), "Login page title does not match.");
    }

    public void login() {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(ConfigReader.getProperty("email"));
        nextButton.click();
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(ConfigReader.getProperty("password"));
        loginButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(staySignedIn)).click();
    }
}
