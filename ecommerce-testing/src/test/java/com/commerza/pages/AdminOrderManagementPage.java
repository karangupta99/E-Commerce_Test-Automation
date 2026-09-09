package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AdminOrderManagementPage {
    
    private WebDriver driver;
    private ElementUtils elementUtils;
    
    private By ordersList = By.id("ordersSection");
    private By ordersTable = By.cssSelector("#ordersTableBody");
    private By orderItems = By.cssSelector(".order-row");
    private By orderNumbers = By.cssSelector(".order-number");
    private By customerNames = By.cssSelector(".customer-name");
    private By orderDates = By.cssSelector(".order-date");
    private By orderStatuses = By.cssSelector(".order-status");
    private By orderAmounts = By.cssSelector(".order-amount");
    private By searchField = By.id("orderSearch");
    private By searchButton = By.cssSelector(".btn-search");
    private By statusFilter = By.id("statusFilter");
    private By dateFromField = By.id("dateFrom");
    private By dateToField = By.id("dateTo");
    private By applyFilterButton = By.cssSelector(".btn-apply-filter");
    private By orderDetailsSection = By.cssSelector(".order-details-panel");
    private By customerInfoSection = By.cssSelector(".customer-info-section");
    private By shippingAddressSection = By.cssSelector(".shipping-info");
    private By orderItemsSection = By.cssSelector(".order-items-list");
    private By paymentMethodSection = By.cssSelector(".payment-info");
    private By statusSelect = By.id("updateOrderStatus");
    private By saveStatusButton = By.cssSelector(".btn-save-status");
    private By cancelOrderButton = By.cssSelector(".btn-cancel-order");
    private By cancellationReasonField = By.id("cancellationReason");
    private By confirmCancellationButton = By.cssSelector(".btn-confirm-cancel");
    private By exportButton = By.id("exportBtn");
    private By csvFormatOption = By.cssSelector("[data-format='csv']");
    
    public AdminOrderManagementPage(WebDriver driver) {
        this.driver = driver;
        this.elementUtils = new ElementUtils(driver);
    }
    
    public boolean isOrdersListDisplayed() {
        return elementUtils.isDisplayed(ordersList);
    }
    
    public int getOrderCount() {
        return driver.findElements(orderItems).size();
    }
    
    public boolean areOrderNumbersDisplayed() {
        return !driver.findElements(orderNumbers).isEmpty();
    }
    
    public boolean areCustomerNamesDisplayed() {
        return !driver.findElements(customerNames).isEmpty();
    }
    
    public boolean areOrderDatesDisplayed() {
        return !driver.findElements(orderDates).isEmpty();
    }
    
    public boolean areOrderStatusesDisplayed() {
        return !driver.findElements(orderStatuses).isEmpty();
    }
    
    public boolean areOrderAmountsDisplayed() {
        return !driver.findElements(orderAmounts).isEmpty();
    }
    
    public void clickOrder(int index) {
        List<WebElement> orders = driver.findElements(orderItems);
        if (index < orders.size()) {
            orders.get(index).click();
        }
    }
    
    public boolean isOrderDetailsDisplayed() {
        return elementUtils.isDisplayed(orderDetailsSection);
    }
    
    public boolean isCustomerInfoDisplayed() {
        return elementUtils.isDisplayed(customerInfoSection);
    }
    
    public boolean isShippingAddressDisplayed() {
        return elementUtils.isDisplayed(shippingAddressSection);
    }
    
    public boolean isOrderItemsSectionDisplayed() {
        return elementUtils.isDisplayed(orderItemsSection);
    }
    
    public boolean isPaymentMethodDisplayed() {
        return elementUtils.isDisplayed(paymentMethodSection);
    }
    
    public void changeOrderStatus(String status) {
        driver.findElement(By.xpath("//select[@id='order-status']/option[contains(text(),'" + status + "')]")).click();
    }
    
    public void clickSaveStatus() {
        elementUtils.click(saveStatusButton);
    }
    
    public void enterSearchQuery(String query) {
        elementUtils.sendKeys(searchField, query);
    }
    
    public void clickSearch() {
        elementUtils.click(searchButton);
    }
    
    public void selectStatusFilter(String status) {
        driver.findElement(By.xpath("//select[@id='status-filter']/option[contains(text(),'" + status + "')]")).click();
    }
    
    public void setDateRange(String fromDate, String toDate) {
        elementUtils.sendKeys(dateFromField, fromDate);
        elementUtils.sendKeys(dateToField, toDate);
    }
    
    public void clickApplyFilter() {
        elementUtils.click(applyFilterButton);
    }
    
    public void clickCancelOrder() {
        elementUtils.click(cancelOrderButton);
    }
    
    public void enterCancellationReason(String reason) {
        elementUtils.sendKeys(cancellationReasonField, reason);
    }
    
    public void confirmCancellation() {
        elementUtils.click(confirmCancellationButton);
    }
    
    public void clickExport() {
        elementUtils.click(exportButton);
    }
    
    public void selectCSVFormat() {
        elementUtils.click(csvFormatOption);
    }
}
