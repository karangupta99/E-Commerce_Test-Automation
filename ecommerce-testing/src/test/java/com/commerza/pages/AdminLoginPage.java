package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By emailField = By.id("admin-email");
    private By passwordField = By.id("admin-password");
    private By loginButton = By.cssSelector(".login-btn");
    private By togglePassword = By.id("togglePassword");
    private By forgotPasswordLink = By.cssSelector("a[href='admin-forgot-password.html']");
    private By errorMessage = By.id("loginError");
    private By resetEmailField = By.id("admin-reset-email");
    private By sendResetLinkButton = By.cssSelector(".reset-btn");
    private By confirmationMessage = By.id("customAlert");
    
    public AdminLoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToAdminLoginPage(String baseUrl) {
        driver.get(baseUrl + "admin-login.html");
    }
    
    public void enterEmail(String email) {
        elementUtils.sendKeys(emailField, email);
    }
    
    public void enterPassword(String password) {
        elementUtils.sendKeys(passwordField, password);
    }
    
    public void clickLoginButton() {
        elementUtils.click(loginButton);
    }
    
    public void clickForgotPassword() {
        elementUtils.click(forgotPasswordLink);
    }
    
    public void enterResetEmail(String email) {
        elementUtils.sendKeys(resetEmailField, email);
    }
    
    public void clickSendResetLink() {
        elementUtils.click(sendResetLinkButton);
    }
    
    public String getErrorMessage() {
        return elementUtils.getText(errorMessage);
    }
    
    public boolean isErrorMessageDisplayed() {
        return elementUtils.isDisplayed(errorMessage);
    }
    
    public boolean isConfirmationMessageDisplayed() {
        return elementUtils.isDisplayed(confirmationMessage);
    }
    
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}
