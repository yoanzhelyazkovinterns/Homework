package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage extends BaseWebPage {

    private final By AcceptCookiesButton = By.id("sp-cc-accept");
    private final By SearchDropDown = By.id("searchDropdownBox");
    private final By BooksOption = By.xpath("//option[text()=\"Books\"]");
    private final By SearchField = By.id("twotabsearchtextbox");
    private final By SearchButton = By.id("nav-search-submit-button");
    private final By BasketButton = By.id("nav-cart-count-container");

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public void clickAcceptCookiesButton() {
        driver.findElement(AcceptCookiesButton).click();
    }

    public void clickSearchDropDown() {
        driver.findElement(SearchDropDown).click();
    }

    public void clickBooksOption() {
        driver.findElement(BooksOption).click();
    }

    public void enterSearchCriteria(String searchCriteria) {
        driver.findElement(SearchField).sendKeys(searchCriteria);
    }

    public void clickSearchButton() {
        driver.findElement(SearchButton).click();
    }

    public void clickBasketButton() {
        driver.findElement(BasketButton).click();
    }
}
