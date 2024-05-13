package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProvider {

    public static WebDriver getDriver() {
        var driver = new ChromeDriver();
        driver.manage().window().maximize();

        return driver;
    }
}
