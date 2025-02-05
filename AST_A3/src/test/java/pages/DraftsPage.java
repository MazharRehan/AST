package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DraftsPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[text()='Drafts']")
    private WebElement draftsFolder;

    @FindBy(xpath = "//span[text()='Test Subject']")
    private WebElement savedDraft;

    public DraftsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void openDraftsFolder() {
        draftsFolder.click();
    }

    public boolean isEmailSavedInDrafts() {
        return wait.until(ExpectedConditions.visibilityOf(savedDraft)).isDisplayed();
    }
}
