package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonShoppingBasketPage extends BaseWebPage {

    private final By ItemsAdded = By.xpath("//div[@class='sc-list-item-content']");
    private final By MarkAsGiftCheckbox = By.xpath("//span[@class='a-list-item']//input[@type='checkbox']");
    private final By BasketContainsGiftItemsCheckbox = By.xpath("//span[@class='a-list-item']//input[@type='checkbox']");
    private final By ItemsTitle = By.xpath("//span[@class='a-truncate sc-grid-item-product-title a-size-base-plus']");
    private final By ItemsType = By.xpath("//span[@class='a-size-small sc-product-binding a-text-bold']");
    private final By SubTotal = By.id("sc-subtotal-amount-activecart");

    public AmazonShoppingBasketPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getItemsInShoppingBasket() {
        return driver.findElements(ItemsAdded);
    }

    public void clickMarkAsGiftCheckbox() {
        driver.findElement(MarkAsGiftCheckbox).click();
    }

    public boolean isItemAddedAsAGiftCheckboxTicked() {
        return driver.findElement(BasketContainsGiftItemsCheckbox).isSelected();
    }

    public List<String> getAddedItemsTitle() {
        return driver.findElements(ItemsTitle)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getAddedItemsTypes() {
        return driver.findElements(ItemsType)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getSubTotal() {
        return driver.findElement(SubTotal).getText().trim();
    }
}
