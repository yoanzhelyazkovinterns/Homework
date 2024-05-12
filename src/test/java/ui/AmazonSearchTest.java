package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AmazonHomePage;
import org.example.pages.AmazonSearchResultsPage;
import org.example.utils.DriverProvider;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class AmazonSearchTest {
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private AmazonSearchResultsPage amazonSearchResultsPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = DriverProvider.getDriver();
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResultsPage = new AmazonSearchResultsPage(driver);
    }

    @Test
    public void searchForBook() {
        final String searchCriteria = "Harry Potter and the Cursed Child - Parts One and Two";

        driver.navigate().to("https://www.amazon.co.uk/");
        amazonHomePage.clickAcceptCookiesButton();
        amazonHomePage.clickSearchDropDown();
        amazonHomePage.clickBooksOption();
        amazonHomePage.enterSearchCriteria(searchCriteria);
        amazonHomePage.clickSearchButton();

        var firstResultHeader = amazonSearchResultsPage.getResultHeaders()
                .stream()
                .findFirst()
                .get();

        var firstResultType = amazonSearchResultsPage.getResultTypes()
                .stream()
                .findFirst()
                .get();

        var firstResultSellingPrice = amazonSearchResultsPage.getBookPrices()
                .stream()
                .findFirst()
                .get();


        assertAll(
                () -> assertTrue(firstResultHeader.contains(searchCriteria)),
                () -> assertEquals("Paperback", firstResultType),
                () -> assertFalse(firstResultSellingPrice.isEmpty())
        );

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
