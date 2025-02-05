package pages;

import config.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

public class OutlookHomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[@aria-label='New mail']")
    private WebElement newMessageButton;

    @FindBy(xpath = "//div[@role='textbox' and @aria-label='To']")
    private WebElement toField;

    // Wait for the "CC" field and enter the CC email
    // Wait for the "CC" field and enter the CC email
//    WebElement ccField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='textbox' and @aria-label='Cc']")));
    //        actions.click(ccField).sendKeys("l1f21bsse0539@ucp.edu.pk;").sendKeys(Keys.TAB).perform(); // Press Tab after entering CC email

    @FindBy(xpath = "//div[@role='textbox' and @aria-label='Cc']")
    private WebElement ccField;


    @FindBy(css = "input[placeholder='Add a subject']")
    private WebElement subjectField;

    @FindBy(xpath = "//div[@role='textbox' and contains(@aria-label, 'Message body')]")
    private WebElement bodyField;

    public OutlookHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void composeEmail(String recipient, String cc, String subject, String body) {
        wait.until(ExpectedConditions.visibilityOf(newMessageButton)).click();
        wait.until(ExpectedConditions.visibilityOf(toField)).sendKeys(recipient);
        wait.until(ExpectedConditions.visibilityOf(ccField)).sendKeys(cc);
        wait.until(ExpectedConditions.visibilityOf(subjectField)).sendKeys(subject);
        wait.until(ExpectedConditions.visibilityOf(bodyField)).sendKeys(body);
//        Press Ctrl + S to save the email to drafts
        bodyField.sendKeys(Keys.chord(Keys.CONTROL, "s"));
    }

}