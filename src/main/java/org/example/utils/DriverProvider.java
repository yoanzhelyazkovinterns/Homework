package org.example.utils;

import org.example.constants.GenericConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.example.constants.GenericConstants.IMPLICIT_TIMEOUT;

public class DriverProvider {

    public static WebDriver getDriver() {
        var driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        
        return driver;
    }
}
