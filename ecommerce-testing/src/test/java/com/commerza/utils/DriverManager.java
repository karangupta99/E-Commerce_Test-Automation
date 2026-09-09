package com.commerza.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverManager {
    
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initializeDriver();
        }
        return driver.get();
    }
    
    private static void initializeDriver() {
        // Ensure any machine-level proxy settings don't break driver downloads
        System.setProperty("java.net.useSystemProxies", "false");
        System.clearProperty("http.proxyHost");
        System.clearProperty("http.proxyPort");
        System.clearProperty("https.proxyHost");
        System.clearProperty("https.proxyPort");

        String browser = ConfigReader.getBrowser().toLowerCase();
        boolean headless = ConfigReader.isHeadless();
        
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver()
                        .avoidShutdownHook() // keep cache between runs
                        .setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-popup-blocking");
                driver.set(new ChromeDriver(chromeOptions));
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver()
                        .avoidShutdownHook()
                        .setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
                
            case "edge":
                WebDriverManager.edgedriver()
                        .avoidShutdownHook()
                        .setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                driver.set(new EdgeDriver(edgeOptions));
                break;
                
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.get().manage().window().maximize();
    }
    
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
