package pages;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StaySignedInPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "idBtn_Back")
    private WebElement noBtn;

    @FindBy(id = "idSIButton9")
    private WebElement yesBtn;

    public StaySignedInPage(WebDriver driver) {
        this.driver = driver;
        String outlookAppUrl = ConfigReader.getProperty("signInPageUrl");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void isStaySignedIn(@org.jetbrains.annotations.NotNull Boolean staySignedIn) {
        if (staySignedIn) {
            wait.until(driver -> yesBtn.isDisplayed());
        } else {
            wait.until(driver -> noBtn.isDisplayed());
        }
    }
}
