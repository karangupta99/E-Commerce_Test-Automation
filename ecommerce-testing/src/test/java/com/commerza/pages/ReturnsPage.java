package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ReturnsPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By orderNumberField = By.id("returnOrderId");
    private By emailField = By.id("returnEmail");
    private By productSelect = By.id("returnProduct");
    private By returnReasonField = By.id("returnReason");
    private By submitReturnButton = By.cssSelector("#returnForm button[type='submit']");
    private By returnConfirmation = By.id("returnForm");
    private By confirmationMessage = By.id("customAlert");
    private By returnRequestNumber = By.cssSelector(".card-title");
    
    public ReturnsPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public void navigateToReturnsPage(String baseUrl) {
        driver.get(baseUrl + "returns.html");
    }
    
    public void enterOrderNumber(String orderNumber) {
        elementUtils.sendKeys(orderNumberField, orderNumber);
    }
    
    public void selectProduct(int index) {
        List<WebElement> options = driver.findElement(productSelect).findElements(By.tagName("option"));
        if (index < options.size()) {
            options.get(index).click();
        }
    }
    
    public void enterReturnReason(String reason) {
        elementUtils.sendKeys(returnReasonField, reason);
    }
    
    public void clickSubmitReturn() {
        elementUtils.click(submitReturnButton);
    }
    
    public boolean isReturnConfirmationDisplayed() {
        return elementUtils.isDisplayed(returnConfirmation);
    }
    
    public String getConfirmationMessage() {
        return elementUtils.getText(confirmationMessage);
    }
    
    public String getReturnRequestNumber() {
        return elementUtils.getText(returnRequestNumber);
    }
}
