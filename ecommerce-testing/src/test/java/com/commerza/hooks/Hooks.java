package com.commerza.hooks;

import com.commerza.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    static {
        // Quit the driver once when the JVM shuts down (end of suite), not after every scenario.
        Runtime.getRuntime().addShutdownHook(new Thread(DriverManager::quitDriver));
    }

    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting Scenario: " + scenario.getName());
        // Ensure driver is created
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null && scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            } catch (Exception ignored) {
                // If the browser is already gone, don't fail teardown
            }
        }
        System.out.println("Completed Scenario: " + scenario.getName());
        // NOTE:
        // We intentionally do NOT delete cookies / clear storage / quit driver here.
        // Many scenarios depend on an authenticated session, and clearing state after every
        // scenario forces repeated UI logins and slows the suite dramatically.
    }

    // Kept for future use when you want explicit reset between scenarios.
    @SuppressWarnings("unused")
    private void resetBrowserStorage(WebDriver driver) {
        if (driver instanceof JavascriptExecutor jsExecutor) {
            try {
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl != null && currentUrl.startsWith("http")) {
                    jsExecutor.executeScript("window.sessionStorage.clear(); window.localStorage.clear();");
                }
            } catch (Exception ignored) {
                // Some browsers block storage on about:blank or data URLs; skip cleanup in that case
            }
        }
    }
}
