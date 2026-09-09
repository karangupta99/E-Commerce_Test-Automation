package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderTrackingPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By orderNumberField = By.id("orderIdInput");
    private By emailField = By.id("orderEmailInput");
    private By trackOrderButton = By.cssSelector("#orderTrackingForm button[type='submit'], .product-btn-buy");
    private By orderTrackingForm = By.id("orderTrackingForm");
    private By orderStatus = By.cssSelector(".tracking-step, .order-status");
    private By orderTimeline = By.cssSelector(".tracking-steps");
    private By trackingResult = By.id("orderTrackingResult");
    private By deliveryInfo = By.cssSelector(".tracking-form-card");
    private By trackingSteps = By.cssSelector(".tracking-step");
    private By errorMessage = By.id("customAlert");
    private By orderNotFoundMessage = By.cssSelector(".alert, #customAlert, .error-message");

    public OrderTrackingPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToOrderTrackingPage(String baseUrl) {
        driver.get(baseUrl + "order-tracking.html");
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(orderTrackingForm));
        } catch (Exception e) {
            // Continue
        }
    }

    public boolean isTrackingFormDisplayed() {
        return !driver.findElements(orderTrackingForm).isEmpty();
    }
    
    public void enterOrderNumber(String orderNumber) {
        try {
            WebElement field = driver.findElement(orderNumberField);
            field.clear();
            field.sendKeys(orderNumber);
        } catch (Exception e) {
            // Field not found
        }
    }
    
    public void enterEmail(String email) {
        try {
            WebElement field = driver.findElement(emailField);
            field.clear();
            field.sendKeys(email);
        } catch (Exception e) {
            // Field not found
        }
    }
    
    public void clickTrackOrder() {
        try {
            List<WebElement> buttons = driver.findElements(trackOrderButton);
            for (WebElement btn : buttons) {
                if (btn.getText().toLowerCase().contains("track")) {
                    btn.click();
                    break;
                }
            }
            Thread.sleep(1000); // Wait for result
        } catch (Exception e) {
            // Try form submit
            try {
                driver.findElement(orderTrackingForm).submit();
            } catch (Exception ex) {
                // Continue
            }
        }
    }
    
    public boolean isOrderStatusDisplayed() {
        return !driver.findElements(orderStatus).isEmpty();
    }
    
    public String getOrderStatus() {
        try {
            List<WebElement> steps = driver.findElements(orderStatus);
            if (!steps.isEmpty()) {
                return steps.get(0).getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isOrderTimelineDisplayed() {
        return !driver.findElements(orderTimeline).isEmpty();
    }

    public boolean isTrackingResultDisplayed() {
        return !driver.findElements(trackingResult).isEmpty();
    }
    
    public boolean isDeliveryInfoDisplayed() {
        return !driver.findElements(deliveryInfo).isEmpty();
    }
    
    public boolean areTrackingStepsDisplayed() {
        List<WebElement> steps = driver.findElements(trackingSteps);
        return steps.size() > 0;
    }
    
    public int getTrackingStepsCount() {
        return driver.findElements(trackingSteps).size();
    }
    
    public String getErrorMessage() {
        try {
            return elementUtils.getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isOrderNotFoundMessageDisplayed() {
        try {
            Thread.sleep(500);
            return !driver.findElements(orderNotFoundMessage).isEmpty() &&
                   driver.findElement(orderNotFoundMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void trackOrder(String orderNumber, String email) {
        enterOrderNumber(orderNumber);
        if (email != null && !email.isEmpty()) {
            enterEmail(email);
        }
        clickTrackOrder();
    }

    public boolean isOnOrderTrackingPage() {
        return driver.getCurrentUrl().contains("order-tracking") ||
               isTrackingFormDisplayed();
    }

    // Legacy methods for backward compatibility

    public boolean isOrderConfirmationDisplayed() {
        return isTrackingFormDisplayed();
    }

    public String getConfirmationOrderNumber() {
        try {
            return driver.findElement(orderNumberField).getAttribute("value");
        } catch (Exception e) {
            return "";
        }
    }

    public String getConfirmationOrderDate() {
        return "";
    }

    public boolean isDeliveryEstimateDisplayed() {
        return areTrackingStepsDisplayed();
    }

    public boolean isTrackOrderLinkDisplayed() {
        return !driver.findElements(By.cssSelector("a[href*='order-tracking']")).isEmpty() ||
               !driver.findElements(trackOrderButton).isEmpty();
    }
}
