package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchPage {
    WebDriver driver;

    // Locate search box using @FindBy
    @FindBy(id = "search_query_top")
    private WebElement searchBox;

    // Locate search button using @FindBy
    @FindBy(name = "submit_search")
    private WebElement searchButton;

    // Locate all product containers
    @FindBy(css = ".product-container")
    private List<WebElement> productContainers;

    // Locate all product titles within the containers
    @FindBy(css = ".product-name")
    private List<WebElement> productTitles;

    // Constructor to initialize WebElements
    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Perform a search operation
    public void search(String keyword) {
        searchBox.clear();
        searchBox.sendKeys(keyword);
        searchButton.click();
    }

    // Verify product titles contain the keyword
    public void verifySearchResults(String keyword, int expectedCount) {
        int count = 0;

        for (WebElement title : productTitles) {
            String productName = title.getText().toLowerCase();
//            System.out.println("Product Name: " + productName); // Debugging log

            // Check if the product name contains the keyword
            if (productName.contains(keyword.toLowerCase())) {
                count++;
            }
        }

        System.out.println("Number of products containing '" + keyword + "': " + count);

        // Throw an exception if the count does not match the expected count
        if (count != expectedCount) {
            throw new RuntimeException("Expected " + expectedCount + " products, but found " + count);
        }
    }
}