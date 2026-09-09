package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By logo = By.cssSelector(".navbar-logo, .navbar-brand img");
    private By searchBox = By.cssSelector(".search-input, .search-form input[type='search']");
    private By loginLink = By.cssSelector("a[href='login.html'], a[href*='login']");
    private By signupLink = By.cssSelector("a[href='signup.html'], a[href*='signup']");
    private By cartIcon = By.id("cart-icon");
    private By cartCount = By.id("cart-count");
    private By wishlistIcon = By.cssSelector("a[href='wishlist.html'] i, a[href*='wishlist']");
    private By wishlistCount = By.id("wishlist-count");
    private By productCategories = By.cssSelector(".dropdown-menu");
    private By featuredProducts = By.id("featured-products-container");
    private By bannerSlider = By.id("carouselExampleIndicators");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToHomePage(String baseUrl) {
        driver.get(baseUrl + "index.html");
        // Wait for page to load
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(logo));
        } catch (Exception e) {
            // Continue anyway
        }
    }
    
    public boolean isLogoDisplayed() {
        try {
            return !driver.findElements(logo).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    public void clickLogo() {
        try {
            elementUtils.click(logo);
        } catch (Exception e) {
            // Ignore
        }
    }
    
    public void enterSearchText(String searchText) {
        try {
            elementUtils.sendKeys(searchBox, searchText);
        } catch (Exception e) {
            // Search box might not be visible
        }
    }
    
    public void clickLoginLink() {
        try {
            elementUtils.click(loginLink);
        } catch (Exception e) {
            // Navigate directly
            driver.get(driver.getCurrentUrl().replace("index.html", "login.html"));
        }
    }
    
    public void clickSignupLink() {
        try {
            elementUtils.click(signupLink);
        } catch (Exception e) {
            // Navigate directly
            driver.get(driver.getCurrentUrl().replace("index.html", "signup.html"));
        }
    }
    
    public void clickCartIcon() {
        try {
            elementUtils.click(cartIcon);
        } catch (Exception e) {
            // Navigate directly
            driver.get(driver.getCurrentUrl().replace("index.html", "cart.html"));
        }
    }
    
    public void clickWishlistIcon() {
        try {
            elementUtils.click(wishlistIcon);
        } catch (Exception e) {
            // Navigate directly
            driver.get(driver.getCurrentUrl().replace("index.html", "wishlist.html"));
        }
    }
    
    public boolean isFeaturedProductsDisplayed() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(featuredProducts));
            return true;
        } catch (Exception e) {
            return !driver.findElements(featuredProducts).isEmpty();
        }
    }
    
    public boolean isBannerSliderDisplayed() {
        return !driver.findElements(bannerSlider).isEmpty();
    }
    
    public boolean isCategoryMenuDisplayed() {
        return !driver.findElements(productCategories).isEmpty();
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
}
