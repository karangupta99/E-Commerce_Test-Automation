package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private final By emailField = By.id("user-login-email");
    private By passwordField = By.id("user-login-password");
    private By loginButton = By.cssSelector("button[type='submit'], .login-btn");
    private By forgotPasswordLink = By.linkText("Forgot Password?");
    private By errorMessage = By.cssSelector("#customAlert");
    private By emailValidationError = By.cssSelector("#user-login-email:invalid");
    private By passwordValidationError = By.cssSelector("#user-login-password:invalid");
    private By signupLink = By.linkText("Sign Up");
    
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToLoginPage(String baseUrl) {
        driver.get(baseUrl + "login.html");
        // Wait for login form to load
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(emailField));
        } catch (Exception e) {
            // Continue anyway
        }
    }
    
    public void enterEmail(String email) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(emailField));
            elementUtils.sendKeys(emailField, email);
        } catch (Exception e) {
            // Try direct find
            driver.findElement(emailField).sendKeys(email);
        }
    }
    
    public void enterPassword(String password) {
        try {
            elementUtils.sendKeys(passwordField, password);
        } catch (Exception e) {
            driver.findElement(passwordField).sendKeys(password);
        }
    }
    
    public void clickLoginButton() {
        try {
            elementUtils.click(loginButton);
        } catch (Exception e) {
            // Try JS click
            try {
                elementUtils.jsClick(loginButton);
            } catch (Exception ex) {
                // Submit form directly
                driver.findElement(emailField).submit();
            }
        }
    }
    
    public void clickForgotPasswordLink() {
        try {
            elementUtils.click(forgotPasswordLink);
        } catch (Exception e) {
            // Ignore
        }
    }
    
    public void clickSignupLink() {
        try {
            elementUtils.click(signupLink);
        } catch (Exception e) {
            // Navigate directly
            String currentUrl = driver.getCurrentUrl();
            driver.get(currentUrl.replace("login.html", "signup.html"));
        }
    }
    
    public String getErrorMessage() {
        try {
            return elementUtils.getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
    
    public boolean isEmailValidationErrorDisplayed() {
        return !driver.findElements(emailValidationError).isEmpty();
    }
    
    public boolean isPasswordValidationErrorDisplayed() {
        return !driver.findElements(passwordValidationError).isEmpty();
    }
    
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        // Wait for login to process
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
