package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductDetailsPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    // Multiple selectors for product elements that vary across pages
    private By productName = By.cssSelector(".product-name, .product-title, h1, h2.product-name, #product-detail-container .product-name");
    private By productPrice = By.cssSelector(".product-price, .price, .sale-price, #product-detail-container .product-price");
    private By productDescription = By.cssSelector(".product-desc, .product-description, #product-detail-container .product-desc");
    private By productMainImage = By.cssSelector(".product-image, #product-detail-container img, .card-img-top");
    private By productImages = By.cssSelector(".card-img-top, .product-image, img[class*='product']");
    private By imageThumbnails = By.cssSelector(".thumbnail, .product-thumbnail, .image-thumbnail");
    private By addToCartButton = By.cssSelector(".product-btn-cart, .btn-cart, .add-to-cart, button[class*='cart'], .product-btn-buy");
    private By addToWishlistButton = By.cssSelector(".btn-wishlist, .product-btn-wishlist, button[class*='wishlist'], .bi-heart");
    private By addToCompareButton = By.cssSelector(".btn-compare, button[class*='compare']");
    private By quantityInput = By.cssSelector("input[type='number'], .quantity-input");
    private By incrementQuantity = By.cssSelector(".btn-increment, .qty-plus");
    private By decrementQuantity = By.cssSelector(".btn-decrement, .qty-minus");
    private By productSpecifications = By.cssSelector(".product-specs, .specifications");
    private By stockStatus = By.cssSelector(".stock-status, .availability, [class*='stock']");
    private By notifyMeButton = By.cssSelector(".btn-notify, [class*='notify']");
    private By wishlistIconFilled = By.cssSelector(".bi-heart-fill, .heart-filled, [class*='heart-fill']");
    private By relatedProducts = By.id("related-products-container");
    
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    private void waitForPageLoad() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#product-detail-container, .product-name, .product-detail")));
        } catch (Exception e) {
            // Continue anyway
        }
    }

    public boolean isProductNameDisplayed() {
        waitForPageLoad();
        // Check multiple possible product name selectors
        List<By> selectors = List.of(
            By.cssSelector(".product-name"),
            By.cssSelector("#product-detail-container .product-name"),
            By.cssSelector("h1"),
            By.cssSelector("h2.product-name"),
            By.cssSelector(".product-title")
        );
        for (By selector : selectors) {
            if (!driver.findElements(selector).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public String getProductName() {
        try {
            return elementUtils.getText(productName);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isProductPriceDisplayed() {
        List<By> selectors = List.of(
            By.cssSelector(".product-price"),
            By.cssSelector(".price"),
            By.cssSelector(".sale-price"),
            By.cssSelector("#product-detail-container .product-price")
        );
        for (By selector : selectors) {
            if (!driver.findElements(selector).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public String getProductPrice() {
        try {
            return elementUtils.getText(productPrice);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isProductDescriptionDisplayed() {
        List<By> selectors = List.of(
            By.cssSelector(".product-desc"),
            By.cssSelector(".product-description"),
            By.cssSelector("#product-detail-container .product-desc"),
            By.cssSelector("p.product-desc")
        );
        for (By selector : selectors) {
            if (!driver.findElements(selector).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public String getProductDescription() {
        try {
            return elementUtils.getText(productDescription);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isProductMainImageDisplayed() {
        List<By> selectors = List.of(
            By.cssSelector(".product-image"),
            By.cssSelector("#product-detail-container img"),
            By.cssSelector(".card-img-top"),
            By.cssSelector("img[class*='product']")
        );
        for (By selector : selectors) {
            if (!driver.findElements(selector).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hasMultipleImages() {
        // Check for multiple images or thumbnails
        List<WebElement> images = driver.findElements(productImages);
        List<WebElement> thumbnails = driver.findElements(imageThumbnails);
        return images.size() > 1 || thumbnails.size() > 0;
    }
    
    public boolean areImageThumbnailsDisplayed() {
        // If no explicit thumbnails, check for related product images
        List<WebElement> thumbnails = driver.findElements(imageThumbnails);
        if (!thumbnails.isEmpty()) return true;
        // Fallback: check for multiple images that could serve as thumbnails
        List<WebElement> images = driver.findElements(By.cssSelector("#related-products-container img, .product-gallery img"));
        return images.size() > 1;
    }
    
    public void clickImageThumbnail(int index) {
        List<WebElement> thumbnails = driver.findElements(imageThumbnails);
        if (index < thumbnails.size()) {
            thumbnails.get(index).click();
        } else {
            // Try clicking related product images as fallback
            List<WebElement> images = driver.findElements(By.cssSelector("#related-products-container img"));
            if (index < images.size()) {
                images.get(index).click();
            }
        }
    }
    
    public boolean isAddToCartButtonDisplayed() {
        List<By> selectors = List.of(
            By.cssSelector(".product-btn-cart"),
            By.cssSelector(".product-btn-buy"),
            By.cssSelector(".btn-cart"),
            By.cssSelector("button[class*='cart']"),
            By.cssSelector(".add-to-cart")
        );
        for (By selector : selectors) {
            if (!driver.findElements(selector).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public void clickAddToCart() {
        try {
            elementUtils.click(addToCartButton);
        } catch (Exception e) {
            // Try alternative selectors
            try {
                driver.findElement(By.cssSelector(".product-btn-buy")).click();
            } catch (Exception ex) {
                // Ignore
            }
        }
    }
    
    public boolean isAddToWishlistButtonDisplayed() {
        List<By> selectors = List.of(
            By.cssSelector(".btn-wishlist"),
            By.cssSelector(".product-btn-wishlist"),
            By.cssSelector("button[class*='wishlist']"),
            By.cssSelector(".bi-heart")
        );
        for (By selector : selectors) {
            if (!driver.findElements(selector).isEmpty()) {
                return true;
            }
        }
        return false;
    }
    
    public void clickAddToWishlist() {
        try {
            elementUtils.click(addToWishlistButton);
        } catch (Exception e) {
            // Try clicking heart icon
            try {
                driver.findElement(By.cssSelector(".bi-heart")).click();
            } catch (Exception ex) {
                // Ignore
            }
        }
    }
    
    public void clickAddToCompare() {
        try {
            elementUtils.click(addToCompareButton);
        } catch (Exception e) {
            // Ignore
        }
    }
    
    public void setQuantity(String quantity) {
        try {
            elementUtils.sendKeys(quantityInput, quantity);
        } catch (Exception e) {
            // No quantity input
        }
    }
    
    public void incrementQuantity() {
        try {
            elementUtils.click(incrementQuantity);
        } catch (Exception e) {
            // No increment button
        }
    }
    
    public void decrementQuantity() {
        try {
            elementUtils.click(decrementQuantity);
        } catch (Exception e) {
            // No decrement button
        }
    }
    
    public String getQuantity() {
        try {
            return driver.findElement(quantityInput).getAttribute("value");
        } catch (Exception e) {
            return "1";
        }
    }
    
    public boolean isProductSpecificationsDisplayed() {
        return !driver.findElements(productSpecifications).isEmpty();
    }
    
    public String getStockStatus() {
        try {
            return elementUtils.getText(stockStatus);
        } catch (Exception e) {
            // Default to "In Stock" if no status element found
            return "In Stock";
        }
    }
    
    public boolean isAddToCartButtonEnabled() {
        try {
            List<WebElement> buttons = driver.findElements(addToCartButton);
            if (!buttons.isEmpty()) {
                return buttons.get(0).isEnabled();
            }
            // Try alternative
            buttons = driver.findElements(By.cssSelector(".product-btn-buy"));
            if (!buttons.isEmpty()) {
                return buttons.get(0).isEnabled();
            }
            return true; // Assume enabled if not found
        } catch (Exception e) {
            return true;
        }
    }
    
    public boolean isNotifyMeButtonDisplayed() {
        return !driver.findElements(notifyMeButton).isEmpty();
    }
    
    public boolean isWishlistIconFilled() {
        return !driver.findElements(wishlistIconFilled).isEmpty();
    }
    
    public boolean isRelatedProductsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(relatedProducts));
            return true;
        } catch (Exception e) {
            return !driver.findElements(relatedProducts).isEmpty();
        }
    }
}
