package org.example.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverProvider {

    public static WebDriver getDriver() {
        return new ChromeDriver();
    }
}
