package com.commerza.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    
    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    
    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration file");
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }
    
    public static String getAdminUrl() {
        return properties.getProperty("admin.url");
    }
    
    public static String getBrowser() {
        return properties.getProperty("browser");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }
    
    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }
    
    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait"));
    }
    
    public static String getTestUserEmail() {
        return properties.getProperty("test.user.email");
    }
    
    public static String getTestUserPassword() {
        return properties.getProperty("test.user.password");
    }
    
    public static String getAdminEmail() {
        return properties.getProperty("admin.email");
    }
    
    public static String getAdminPassword() {
        return properties.getProperty("admin.password");
    }
}
