package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ComparePage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By comparisonTable = By.cssSelector(".table, .compare-table, #compare-container table, #compare-container");
    private By compareItems = By.cssSelector(".card, .compare-item, .product-card");
    private By compareContainer = By.id("compare-container");
    private By productSpecs = By.cssSelector(".table tbody tr, .compare-specs tr, .specs-row");
    private By addToCartButtons = By.cssSelector(".product-btn-cart, .btn-cart, .add-to-cart, .product-btn-buy");
    private By removeButtons = By.cssSelector(".btn-remove-compare, .remove-compare, button[class*='remove']");
    private By emptyCompareMessage = By.cssSelector(".text-center, .empty-compare, [class*='empty']");
    private By compareCount = By.id("compare-count");
    private By maxLimitMessage = By.id("customAlert");
    
    public ComparePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToComparePage(String baseUrl) {
        driver.get(baseUrl + "compare.html");
        // Wait for page to load
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(compareContainer));
        } catch (Exception e) {
            // Continue anyway
        }
    }
    
    public boolean isComparisonTableDisplayed() {
        // Check for compare container or table or any compare items
        try {
            return !driver.findElements(compareContainer).isEmpty() ||
                   !driver.findElements(By.cssSelector(".table")).isEmpty() ||
                   !driver.findElements(compareItems).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    public int getCompareItemCount() {
        return driver.findElements(compareItems).size();
    }
    
    public boolean areProductSpecsDisplayed() {
        // Check for specs rows or any content in compare container
        List<WebElement> specs = driver.findElements(productSpecs);
        if (!specs.isEmpty()) return true;
        // Fallback: check for product cards with details
        List<WebElement> cards = driver.findElements(By.cssSelector("#compare-container .card, #compare-container .product-card"));
        return !cards.isEmpty();
    }
    
    public List<WebElement> getAddToCartButtons() {
        return driver.findElements(addToCartButtons);
    }
    
    public List<WebElement> getRemoveButtons() {
        return driver.findElements(removeButtons);
    }
    
    public void removeProduct(int index) {
        List<WebElement> removeButtonsList = getRemoveButtons();
        if (index < removeButtonsList.size()) {
            removeButtonsList.get(index).click();
        }
    }
    
    public boolean isEmptyCompareMessageDisplayed() {
        // Check for empty state
        List<WebElement> items = driver.findElements(By.cssSelector("#compare-container .card"));
        return items.isEmpty() || !driver.findElements(emptyCompareMessage).isEmpty();
    }
    
    public String getCompareCount() {
        try {
            return elementUtils.getText(compareCount);
        } catch (Exception e) {
            return "0";
        }
    }
    
    public boolean isMaxLimitMessageDisplayed() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignore
        }
        List<WebElement> alerts = driver.findElements(maxLimitMessage);
        return !alerts.isEmpty() && alerts.get(0).isDisplayed();
    }
    
    public String getMaxLimitMessage() {
        try {
            return elementUtils.getText(maxLimitMessage);
        } catch (Exception e) {
            return "";
        }
    }
}
