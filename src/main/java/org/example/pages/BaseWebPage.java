package org.example.pages;

import org.openqa.selenium.WebDriver;

public class BaseWebPage {
    protected WebDriver driver;

    public BaseWebPage(WebDriver driver) {
        this.driver = driver;
    }
}
