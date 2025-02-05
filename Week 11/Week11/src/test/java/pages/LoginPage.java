package pages;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    // Locate the email input field using @FindBy
    @FindBy(id = "email")
    private WebElement emailField;

    // Locate the password input field using @FindBy
    @FindBy(id = "passwd")
    private WebElement passwordField;

    // Locate the login button using @FindBy
    @FindBy(id = "SubmitLogin")
    private WebElement loginButton;

    // Constructor to initialize WebElements
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize WebElements using PageFactory
    }

    // Method to perform login
    public void login(String email, String password) {
        emailField.sendKeys(email); // Enter email
        passwordField.sendKeys(password); // Enter password
        loginButton.click(); // Click the login button
    }
}
