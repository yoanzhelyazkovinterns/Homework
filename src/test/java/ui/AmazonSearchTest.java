package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.AmazonExpandedResultPage;
import org.example.pages.AmazonHomePage;
import org.example.pages.AmazonSearchResultsPage;
import org.example.pages.AmazonShoppingBasketPage;
import org.example.utils.ConfigurationProvider;
import org.example.utils.DriverProvider;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.example.constants.GenericConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class AmazonSearchTest {
    private ConfigurationProvider configurationProvider;
    private WebDriver driver;
    private AmazonHomePage amazonHomePage;
    private AmazonSearchResultsPage amazonSearchResultsPage;
    private AmazonExpandedResultPage amazonExpandedResultPage;
    private AmazonShoppingBasketPage amazonShoppingBasketPage;

    @BeforeAll
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        configurationProvider = new ConfigurationProvider();
        driver = DriverProvider.getDriver();
        amazonHomePage = new AmazonHomePage(driver);
        amazonSearchResultsPage = new AmazonSearchResultsPage(driver);
        amazonExpandedResultPage = new AmazonExpandedResultPage(driver);
        amazonShoppingBasketPage = new AmazonShoppingBasketPage(driver);

        driver.navigate().to(configurationProvider.getApplicationUrl());
        amazonHomePage.clickAcceptCookiesButton();
    }

    @Test
    public void searchForBook() {
        SearchForSpecificBook();

        var firstResultHeader = getFirstElementInCollection(amazonSearchResultsPage.getResultHeaders());
        var firstResultType = getFirstElementInCollection(amazonSearchResultsPage.getResultTypes());
        var firstResultSellingPrice = getFirstElementInCollection(amazonSearchResultsPage.getBookPrices());
        
        assertAll(
                () -> assertTrue(firstResultHeader.contains(SEARCH_CRITERIA)),
                () -> assertEquals(BOOK_TYPE, firstResultType),
                () -> assertFalse(firstResultSellingPrice.isEmpty())
        );
    }

    @Test
    public void searchForBookPaperback() {
        SearchForSpecificBook();

        var firstResultHeader = getFirstElementInCollection(amazonSearchResultsPage.getResultHeaders());
        var firstResultSellingPrice = getFirstElementInCollection(amazonSearchResultsPage.getBookPrices());

        amazonSearchResultsPage.clickFirstResultPaperbackCopy();

        var productTitle = amazonExpandedResultPage.getProductTitle();
        var productSubtitle = amazonExpandedResultPage.getProductSubtitle();
        var productPrice = amazonExpandedResultPage.getProductPrice();

        assertAll(
                () -> assertEquals(firstResultHeader, productTitle),
                () -> assertTrue(productSubtitle.startsWith(BOOK_TYPE)),
                () -> assertEquals(firstResultSellingPrice, productPrice)
        );
    }

        @Test
        public void addBookToBasket() {
            SearchForSpecificBook();

            var firstResultHeader = getFirstElementInCollection(amazonSearchResultsPage.getResultHeaders());
            var firstResultSellingPrice = getFirstElementInCollection(amazonSearchResultsPage.getBookPrices());

            amazonSearchResultsPage.clickFirstResultPaperbackCopy();
            amazonExpandedResultPage.clickOnAddToBasketButton();
            amazonHomePage.clickBasketButton();
            amazonShoppingBasketPage.clickMarkAsGiftCheckbox();

            var basketItemsCount = amazonShoppingBasketPage.getItemsInShoppingBasket().size();
            var firstBasketItemTitle = getFirstElementInCollection(amazonShoppingBasketPage.getAddedItemsTitle());
            var firstBasketItemType = getFirstElementInCollection(amazonShoppingBasketPage.getAddedItemsTypes());
            var basketSubTotal = amazonShoppingBasketPage.getSubTotal();
            var isMarkedAsGiftCheckboxSelected = amazonShoppingBasketPage.isItemAddedAsAGiftCheckboxTicked();

            assertAll(
                    () -> assertEquals(firstResultHeader, firstBasketItemTitle),
                    () -> assertEquals(BOOK_TYPE, firstBasketItemType),
                    () -> assertEquals(firstResultSellingPrice, basketSubTotal),
                    () -> assertEquals(basketItemsCount, 1),
                    () -> assertTrue(isMarkedAsGiftCheckboxSelected)
            );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    private void SearchForSpecificBook() {
        amazonHomePage.clickSearchDropDown();
        amazonHomePage.clickBooksOption();
        amazonHomePage.enterSearchCriteria(SEARCH_CRITERIA);
        amazonHomePage.clickSearchButton();
    } 
    
    private String getFirstElementInCollection(List<String> collection) {
        return collection
                .stream()
                .findFirst()
                .get();
    }
}