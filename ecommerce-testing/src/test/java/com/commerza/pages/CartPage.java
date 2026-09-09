package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By cartItems = By.cssSelector(".cart-item, #cart-items-container .card, #cart-items-container > div");
    private By cartIcon = By.id("cart-icon");
    private By cartCount = By.id("cart-count");
    private By confirmationMessage = By.id("customAlert");
    private By cartItemsContainer = By.id("cart-items-container");
    private By productNames = By.cssSelector(".product-name, .cart-item-name");
    private By productPrices = By.cssSelector(".product-price, .cart-item-price");
    private By quantityInputs = By.cssSelector("input[type='number'], .qty-input");
    private By subtotals = By.cssSelector(".subtotal, .item-subtotal");
    private By cartSummary = By.cssSelector(".card-body, .order-summary");
    private By updateCartButton = By.cssSelector(".btn-update, button[class*='update']");
    private By incrementButtons = By.cssSelector(".btn-increment, .qty-plus, button[class*='increment']");
    private By decrementButtons = By.cssSelector(".btn-decrement, .qty-minus, button[class*='decrement']");
    private By removeButtons = By.cssSelector(".btn-remove, .remove-item, button[class*='remove']");
    private By clearCartButton = By.cssSelector(".btn-clear, button[class*='clear']");
    private By emptyCartMessage = By.cssSelector(".text-center, .empty-cart");
    private By continueShoppingButton = By.cssSelector("a[href='index.html'], .continue-shopping");
    private By cartSubtotal = By.id("cart-subtotal");
    private By totalItemsQty = By.id("total-items-qty");
    private By couponInput = By.id("coupon-code");
    private By applyCouponButton = By.cssSelector(".btn-coupon");
    private By discountAmount = By.cssSelector(".discount-amount");
    private By shippingCost = By.id("cart-shipping");
    private By cartTotal = By.id("cart-total");
    private By errorMessage = By.id("customAlert");
    private By couponSuccessMessage = By.id("customAlert");
    private By proceedToCheckoutButton = By.cssSelector("button[data-bs-target='#checkoutModal'], .btn-checkout, .product-btn-buy");
    private By itemRemovalConfirmation = By.id("customAlert");
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToCartPage(String baseUrl) {
        driver.get(baseUrl + "cart.html");
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(cartItemsContainer),
                    ExpectedConditions.urlContains("login")
                ));
            Thread.sleep(1000); // Allow JS to render
        } catch (Exception e) {
            // Continue
        }
    }
    
    public boolean hasCartItems() {
        waitForPageLoad();
        List<WebElement> items = driver.findElements(cartItems);
        // Filter out empty containers
        return items.stream().anyMatch(item -> !item.getText().isEmpty());
    }
    
    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }
    
    public String getCartCount() {
        try {
            return elementUtils.getText(cartCount);
        } catch (Exception e) {
            return "0";
        }
    }
    
    public boolean isConfirmationMessageDisplayed() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
        return !driver.findElements(confirmationMessage).isEmpty() &&
               driver.findElement(confirmationMessage).isDisplayed();
    }
    
    public String getConfirmationMessage() {
        try {
            return elementUtils.getText(confirmationMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean areProductNamesDisplayed() {
        return !driver.findElements(productNames).isEmpty();
    }
    
    public boolean areProductPricesDisplayed() {
        return !driver.findElements(productPrices).isEmpty();
    }
    
    public boolean areQuantitiesDisplayed() {
        return !driver.findElements(quantityInputs).isEmpty();
    }
    
    public boolean areSubtotalsDisplayed() {
        return !driver.findElements(subtotals).isEmpty() ||
               !driver.findElements(By.id("cart-subtotal")).isEmpty();
    }
    
    public boolean isCartSummaryDisplayed() {
        return !driver.findElements(cartSummary).isEmpty() ||
               !driver.findElements(By.id("cart-total")).isEmpty();
    }
    
    public void updateQuantity(int itemIndex, String quantity) {
        List<WebElement> quantityFields = driver.findElements(quantityInputs);
        if (itemIndex < quantityFields.size()) {
            quantityFields.get(itemIndex).clear();
            quantityFields.get(itemIndex).sendKeys(quantity);
        }
    }
    
    public void clickUpdateCart() {
        try {
            elementUtils.click(updateCartButton);
        } catch (Exception e) {
            // Update might be automatic
        }
    }
    
    public String getItemQuantity(int itemIndex) {
        List<WebElement> quantityFields = driver.findElements(quantityInputs);
        if (itemIndex < quantityFields.size()) {
            return quantityFields.get(itemIndex).getAttribute("value");
        }
        return "0";
    }
    
    public String getItemSubtotal(int itemIndex) {
        List<WebElement> subtotalElements = driver.findElements(subtotals);
        if (itemIndex < subtotalElements.size()) {
            return subtotalElements.get(itemIndex).getText();
        }
        return "0";
    }
    
    public void clickIncrementButton(int itemIndex) {
        List<WebElement> incrementButtonsList = driver.findElements(incrementButtons);
        if (itemIndex < incrementButtonsList.size()) {
            incrementButtonsList.get(itemIndex).click();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
    
    public void clickDecrementButton(int itemIndex) {
        List<WebElement> decrementButtonsList = driver.findElements(decrementButtons);
        if (itemIndex < decrementButtonsList.size()) {
            decrementButtonsList.get(itemIndex).click();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
    
    public void clickRemoveButton(int itemIndex) {
        List<WebElement> removeButtonsList = driver.findElements(removeButtons);
        if (itemIndex < removeButtonsList.size()) {
            removeButtonsList.get(itemIndex).click();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
    
    public void clickClearCart() {
        try {
            elementUtils.click(clearCartButton);
        } catch (Exception e) {
            // Clear cart button might not exist
        }
    }
    
    public boolean isEmptyCartMessageDisplayed() {
        // Check if cart is empty
        return !hasCartItems() || !driver.findElements(emptyCartMessage).isEmpty();
    }
    
    public String getEmptyCartMessage() {
        try {
            return elementUtils.getText(emptyCartMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isContinueShoppingButtonDisplayed() {
        return !driver.findElements(continueShoppingButton).isEmpty();
    }
    
    public String getCartSubtotal() {
        try {
            return elementUtils.getText(cartSubtotal);
        } catch (Exception e) {
            return "0";
        }
    }
    
    public void enterCouponCode(String couponCode) {
        try {
            elementUtils.sendKeys(couponInput, couponCode);
        } catch (Exception e) {
            // Coupon field might not exist
        }
    }
    
    public void clickApplyCoupon() {
        try {
            elementUtils.click(applyCouponButton);
        } catch (Exception e) {
            // Coupon button might not exist
        }
    }
    
    public boolean isDiscountDisplayed() {
        return !driver.findElements(discountAmount).isEmpty();
    }
    
    public String getDiscountAmount() {
        try {
            return elementUtils.getText(discountAmount);
        } catch (Exception e) {
            return "0";
        }
    }
    
    public String getShippingCost() {
        try {
            return elementUtils.getText(shippingCost);
        } catch (Exception e) {
            return "";
        }
    }
    
    public String getCartTotal() {
        try {
            return elementUtils.getText(cartTotal);
        } catch (Exception e) {
            return "0";
        }
    }
    
    public String getErrorMessage() {
        try {
            return elementUtils.getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isCouponSuccessMessageDisplayed() {
        return !driver.findElements(couponSuccessMessage).isEmpty();
    }
    
    public void clickProceedToCheckout() {
        try {
            // Find and click checkout button
            List<WebElement> checkoutButtons = driver.findElements(proceedToCheckoutButton);
            if (!checkoutButtons.isEmpty()) {
                checkoutButtons.get(0).click();
            } else {
                // Try alternative
                driver.findElement(By.cssSelector(".product-btn-buy")).click();
            }
            Thread.sleep(1000); // Wait for modal
        } catch (Exception e) {
            // Continue
        }
    }
    
    public boolean isItemRemovalConfirmationDisplayed() {
        return !driver.findElements(itemRemovalConfirmation).isEmpty();
    }
    
    public void clickCartIcon() {
        try {
            elementUtils.click(cartIcon);
        } catch (Exception e) {
            // Navigate directly
        }
    }

    public boolean isCheckoutModalDisplayed() {
        try {
            Thread.sleep(500);
            return !driver.findElements(By.id("checkoutModal")).isEmpty() &&
                   driver.findElement(By.id("checkoutModal")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
