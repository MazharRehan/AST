package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {
    @Test
    public void testSearchForDress() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.search("dress");
        searchPage.verifySearchResults("dress", 10); // Specify expected count here
    }
}
