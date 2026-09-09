package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WishlistPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By wishlistItems = By.cssSelector(".card, .wishlist-item, .product-card");
    private By wishlistContainer = By.id("wishlist-container");
    private By removeButtons = By.cssSelector(".btn-remove-wishlist, .remove-wishlist, button[class*='remove']");
    private By addToCartButtons = By.cssSelector(".product-btn-cart, .btn-cart, .add-to-cart");
    private By emptyWishlistMessage = By.cssSelector(".text-center, .empty-wishlist, [class*='empty']");
    private By wishlistCount = By.id("wishlist-count");
    private By productNames = By.cssSelector(".product-name, .card-title");
    private By productPrices = By.cssSelector(".product-price, .price");
    private By removalConfirmation = By.id("customAlert");
    
    public WishlistPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToWishlistPage(String baseUrl) {
        driver.get(baseUrl + "wishlist.html");
        // Wait for page to load
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.or(
                    ExpectedConditions.presenceOfElementLocated(wishlistContainer),
                    ExpectedConditions.urlContains("login")
                ));
        } catch (Exception e) {
            // Continue anyway
        }
    }
    
    public boolean hasWishlistItems() {
        // Wait for dynamic content
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ignore
        }
        List<WebElement> items = driver.findElements(By.cssSelector("#wishlist-container .card, #wishlist-container .product-card, .wishlist-item"));
        return !items.isEmpty();
    }
    
    public int getWishlistItemCount() {
        return driver.findElements(wishlistItems).size();
    }
    
    public List<WebElement> getWishlistItems() {
        return driver.findElements(wishlistItems);
    }
    
    public void clickRemoveButton(int index) {
        List<WebElement> removeButtonsList = driver.findElements(removeButtons);
        if (index < removeButtonsList.size()) {
            removeButtonsList.get(index).click();
        }
    }
    
    public void clickAddToCartButton(int index) {
        List<WebElement> addToCartButtonsList = driver.findElements(addToCartButtons);
        if (index < addToCartButtonsList.size()) {
            addToCartButtonsList.get(index).click();
        }
    }
    
    public boolean isEmptyWishlistMessageDisplayed() {
        // Check for empty message or no items
        List<WebElement> items = driver.findElements(By.cssSelector("#wishlist-container .card"));
        if (items.isEmpty()) {
            return true;
        }
        return !driver.findElements(emptyWishlistMessage).isEmpty();
    }
    
    public String getEmptyWishlistMessage() {
        try {
            return elementUtils.getText(emptyWishlistMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isRemovalConfirmationDisplayed() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }
        return !driver.findElements(removalConfirmation).isEmpty() &&
               driver.findElement(removalConfirmation).isDisplayed();
    }
    
    public String getRemovalConfirmationMessage() {
        try {
            return elementUtils.getText(removalConfirmation);
        } catch (Exception e) {
            return "";
        }
    }
}
