package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;

    @FindBy(name = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(className = "orangehrm-login-button")
    WebElement loginButton;

    // Step 3: Login to the application and then minimize the window
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void minimizeWindow() {
        driver.manage().window().minimize();
    }

    //    verifyLogout
    public boolean verifyLogout() {
        return loginButton.isDisplayed();
    }
}
