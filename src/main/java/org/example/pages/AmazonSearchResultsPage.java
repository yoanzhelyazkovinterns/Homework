package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AmazonSearchResultsPage extends BaseWebPage {

    private final By ResultHeaders = By.xpath("//h2/a/span");
    private final By BookTypes = By.xpath("//div[@data-cy]/div/a");
    private final By BookPrices = By.xpath("//span[@class='a-price']");

    public AmazonSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getResultHeaders() {
        return driver.findElements(ResultHeaders)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getResultTypes() {
        return driver.findElements(BookTypes)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getBookPrices() {
        return driver.findElements(BookPrices)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
