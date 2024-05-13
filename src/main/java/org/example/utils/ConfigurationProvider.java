package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.io.File;
import java.io.FileNotFoundException;

public class ConfigurationProvider {
    
    private final Properties properties;

    public ConfigurationProvider() {
        var configFilePath = "config//config.properties";
        var ConfigFile = new File(configFilePath);
        try {
            FileInputStream configFileReader = new FileInputStream(ConfigFile);
            properties = new Properties();
            
            try {
                properties.load(configFileReader);
                configFileReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("config.properties not found at config file path " + configFilePath);
        }
    }

    public String getApplicationUrl() {
        var applicationUrl = properties.getProperty("url.base");
        if (applicationUrl != null)
            return applicationUrl;
        else
            throw new RuntimeException("Application url not specified in the config.properties file.");
    }
}