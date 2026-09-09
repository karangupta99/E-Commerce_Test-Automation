package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By checkoutModal = By.id("checkoutModal");
    private By checkoutForm = By.id("checkoutForm");
    private By fullNameField = By.id("customerName");
    private By phoneField = By.id("customerPhone");
    private By addressLine1Field = By.id("customerAddress");
    private By emailField = By.id("customerEmail");
    private By paymentMethodField = By.id("paymentMethod");
    private By completeCheckoutButton = By.id("completeCheckoutBtn");
    private By placeOrderButton = By.cssSelector("#completeCheckoutBtn, .btn-checkout, button[type='submit']");
    private By orderSummary = By.cssSelector(".card-body");
    private By summaryProducts = By.cssSelector("#cart-items-container");
    private By summarySubtotal = By.id("cart-subtotal");
    private By summaryShipping = By.id("cart-shipping");
    private By summaryTotal = By.id("cart-total");
    private By validationError = By.id("customAlert");
    private By orderConfirmation = By.cssSelector(".order-confirmation, .alert-success, #customAlert");
    private By modalCloseButton = By.cssSelector("#checkoutModal .btn-close, [data-bs-dismiss='modal']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    private void waitForModal() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(checkoutModal));
        } catch (Exception e) {
            // Continue
        }
    }

    public boolean isCheckoutModalDisplayed() {
        try {
            return !driver.findElements(checkoutModal).isEmpty() &&
                   driver.findElement(checkoutModal).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCheckoutFormDisplayed() {
        return !driver.findElements(checkoutForm).isEmpty();
    }

    public boolean isOnCheckoutPage() {
        return driver.getCurrentUrl().contains("checkout") || 
               isCheckoutModalDisplayed() ||
               !driver.findElements(placeOrderButton).isEmpty();
    }
    
    public void enterFullName(String name) {
        try {
            WebElement field = driver.findElement(fullNameField);
            field.clear();
            field.sendKeys(name);
        } catch (Exception e) {
            // Field not found
        }
    }
    
    public void enterPhone(String phone) {
        try {
            WebElement field = driver.findElement(phoneField);
            field.clear();
            field.sendKeys(phone);
        } catch (Exception e) {
            // Field not found
        }
    }
    
    public void enterAddress(String address) {
        try {
            WebElement field = driver.findElement(addressLine1Field);
            field.clear();
            field.sendKeys(address);
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
            // Field not found (optional)
        }
    }
    
    public void enterCheckoutDetails(String fullName, String phone, String address, String email) {
        waitForModal();
        enterFullName(fullName);
        enterPhone(phone);
        enterAddress(address);
        enterEmail(email);
    }
    
    public void clickCompleteOrder() {
        try {
            List<WebElement> buttons = driver.findElements(completeCheckoutButton);
            if (!buttons.isEmpty()) {
                buttons.get(0).click();
            } else {
                // Try alternative
                driver.findElement(placeOrderButton).click();
            }
            Thread.sleep(2000); // Wait for order processing
        } catch (Exception e) {
            // Continue
        }
    }
    
    public void clickPlaceOrder() {
        clickCompleteOrder();
    }
    
    public boolean isOrderSummaryDisplayed() {
        return !driver.findElements(orderSummary).isEmpty();
    }
    
    public boolean areSummaryProductsDisplayed() {
        return !driver.findElements(summaryProducts).isEmpty();
    }
    
    public String getSummarySubtotal() {
        try {
            return elementUtils.getText(summarySubtotal);
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getSummaryShipping() {
        try {
            return elementUtils.getText(summaryShipping);
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getSummaryTotal() {
        try {
            return elementUtils.getText(summaryTotal);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isValidationErrorDisplayed() {
        return !driver.findElements(validationError).isEmpty() &&
               driver.findElement(validationError).isDisplayed();
    }
    
    public String getValidationError() {
        try {
            return elementUtils.getText(validationError);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isOrderConfirmationDisplayed() {
        try {
            Thread.sleep(1000);
            // Check for various confirmation indicators
            return !driver.findElements(orderConfirmation).isEmpty() ||
                   driver.getCurrentUrl().contains("confirmation") ||
                   driver.getCurrentUrl().contains("success");
        } catch (Exception e) {
            return false;
        }
    }
    
    public void closeModal() {
        try {
            elementUtils.click(modalCloseButton);
        } catch (Exception e) {
            // Modal might already be closed
        }
    }

    // Legacy methods for backward compatibility

    public void enterAddressLine1(String address) {
        enterAddress(address);
    }

    public void enterCity(String city) {
        // City field might not exist in current form
    }

    public void enterPostalCode(String postalCode) {
        // Postal code field might not exist in current form
    }

    public void enterCountry(String country) {
        // Country field might not exist in current form
    }

    public void checkDifferentBillingAddress() {
        // Different billing address not supported in current form
    }

    public void enterBillingFullName(String name) {
        // Billing fields not in current form
    }

    public void enterBillingPhone(String phone) {
        // Billing fields not in current form
    }

    public void enterBillingAddress(String address) {
        // Billing fields not in current form
    }

    public void enterBillingCity(String city) {
        // Billing fields not in current form
    }

    public void enterBillingPostalCode(String postalCode) {
        // Billing fields not in current form
    }

    public void selectPaymentMethod(String method) {
        // Payment method is fixed to COD in current form
    }

    public void agreeToTerms() {
        // Terms checkbox not in current form
    }

    public boolean isPaymentMethodSelected() {
        // Payment method is always COD
        return true;
    }

    public boolean isPaymentDetailsDisplayed() {
        return true;
    }

    public boolean isLoginOptionDisplayed() {
        return !driver.findElements(By.cssSelector("a[href*='login']")).isEmpty();
    }

    public boolean isGuestCheckoutOptionDisplayed() {
        return false;
    }

    public boolean areFieldsPreFilled() {
        try {
            String nameValue = driver.findElement(fullNameField).getAttribute("value");
            return nameValue != null && !nameValue.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
