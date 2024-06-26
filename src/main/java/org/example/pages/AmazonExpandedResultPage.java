package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonExpandedResultPage extends BaseWebPage {

    private final By ProductTitle = By.xpath("//span[@id='productTitle']");
    private final By ProductSubtitle = By.xpath("//span[@id='productSubtitle']");
    private final By ProductPrice = By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']");
    private final By AddToBasketButton = By.xpath("//span/input[@id='add-to-cart-button']");

    public AmazonExpandedResultPage(WebDriver driver) {
        super(driver);
    }
    
    public String getProductTitle() {
        return driver.findElement(ProductTitle).getText();
    }

    public String getProductSubtitle() {
        return driver.findElement(ProductSubtitle).getText();
    }

    public String getProductPrice() {
        return driver.findElement(ProductPrice).getText().replace('\n', '.').trim();
    }

    public void clickOnAddToBasketButton() {
        driver.findElement(AddToBasketButton).click();
    }
}
