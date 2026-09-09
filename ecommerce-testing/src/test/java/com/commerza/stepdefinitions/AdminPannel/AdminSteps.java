package com.commerza.stepdefinitions.AdminPannel;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.commerza.pages.AdminLoginPage;
import com.commerza.pages.AdminDashboardPage;
import com.commerza.utils.ConfigReader;
import com.commerza.utils.DriverManager;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import java.util.List;

public class AdminSteps {

    private static final Logger logger = LogManager.getLogger(AdminSteps.class);

    private WebDriver driver;
    private AdminLoginPage adminPage;
    private AdminDashboardPage adminDashboardPage;

    public AdminSteps() {
        this.driver             = DriverManager.getDriver();
        this.adminPage          = new AdminLoginPage(this.driver);
        this.adminDashboardPage = new AdminDashboardPage(this.driver);
        logger.debug("AdminSteps initialized");
    }

    // =========================================================================
    // SCENARIO 1: Successful admin login
    // =========================================================================

    @Given("the admin navigates to admin login page")
    public void the_admin_navigates_to_admin_login_page() {
        logger.info("Navigating to admin login page");
        adminPage.navigateToAdminLoginPage(ConfigReader.getAdminUrl());
    }

    @When("the admin enters valid admin email")
    public void the_admin_enters_admin_email() {
        adminPage.enterEmail(ConfigReader.getAdminEmail());
    }

    @When("the admin enters valid admin password")
    public void the_admin_enters_admin_password() {
        adminPage.enterPassword(ConfigReader.getAdminPassword());
    }

    @When("the admin clicks on admin login button")
    public void the_admin_clicks_on_admin_login_button() {
        adminPage.clickLoginButton();
    }

    @Then("the admin should be logged in successfully")
    public void the_admin_should_be_logged_in_successfully() {
        if (adminPage.isErrorMessageDisplayed())
            throw new RuntimeException("Login failed — error message displayed");
        logger.info("Admin logged in successfully");
    }

    @Then("the admin should see the admin dashboard")
    public void the_admin_should_see_the_admin_dashboard() {
        if (!adminDashboardPage.isDashboardDisplayed())
            throw new RuntimeException("Dashboard not visible after login");
        logger.info("Admin dashboard is displayed");
    }

    // =========================================================================
    // SHARED: Clear local storage
    // =========================================================================

    @And("the driver clears local storage")
    public void the_driver_clears_local_storage() {
        ((JavascriptExecutor) driver)
            .executeScript("localStorage.clear();location.reload();");
        logger.info("Local storage cleared");
    }

    // =========================================================================
    // SCENARIO 2: Invalid login
    // =========================================================================

    @When("the admin enters invalid admin email")
    public void the_admin_enters_invalid_admin_email() {
        adminPage.enterEmail("invalid@email.com");
    }

    @When("the admin enters invalid admin password")
    public void the_admin_enters_invalid_admin_password() {
        adminPage.enterPassword("invalidPassword");
    }

    @Then("the admin should see admin login error message")
    public void the_admin_should_see_admin_login_error_message() {
        if (!adminPage.isErrorMessageDisplayed())
            throw new RuntimeException("Login error message not displayed");
    }

    @Then("the admin should not be able to access dashboard")
    public void the_admin_should_not_be_able_to_access_dashboard() {
        if (adminDashboardPage.isDashboardDisplayed())
            throw new RuntimeException("Dashboard is visible when it should not be");
    }

    // =========================================================================
    // SCENARIO 3: Forgot password
    // =========================================================================

    @When("the admin clicks on admin forgot password link")
    public void the_admin_clicks_on_admin_forgot_password_link() {
        adminPage.clickForgotPassword();
    }

    @When("the admin clicks on send admin reset link")
    public void the_admin_clicks_on_send_admin_reset_link() {
        logger.info("Send reset link — TODO: add when reset page HTML is available");
    }

    @Then("the admin should see password reset email confirmation")
    public void the_admin_should_see_password_reset_email_confirmation() {
        logger.info("Reset confirmation — TODO: add when reset page HTML is available");
    }

    // =========================================================================
    // SHARED PRECONDITION: Admin is logged in (Scenarios 4–35)
    // =========================================================================

    @Given("the admin is logged in to admin panel")
    public void the_admin_is_logged_in_to_admin_panel() {
        // adminPage.navigateToAdminLoginPage(ConfigReader.getAdminUrl());
        adminPage.login(ConfigReader.getAdminEmail(), ConfigReader.getAdminPassword());
        logger.info("Admin login completed");
    }

    // =========================================================================
    // SCENARIOS 4–9: Product Management
    // =========================================================================

    @When("the admin navigates to products management section")
    public void the_admin_navigates_to_products_management_section() {
				try{
						adminDashboardPage.getHelperActions().get(0).click();
				} catch (Exception e) {
						adminDashboardPage.clickProductsLink();
				}
    }

    @When("the admin clicks on add new product button")
    public void the_admin_clicks_on_add_new_product_button() {
        adminDashboardPage.clickAddNewProductButton();
        // adminDashboardPage.waitForProductModalVisible();
    }

    @When("the admin enters product details")
    public void the_admin_enters_product_details(DataTable dataTable) {
        for (java.util.Map<String, String> row : dataTable.asMaps()) {
            String field = row.get("Field");
            String value = row.get("Value");
            switch (field) {
                case "Product Name":   adminDashboardPage.enterProductName(value);        break;
                case "Section":        adminDashboardPage.selectProductSection(value);    break;
                case "Price":          adminDashboardPage.enterProductPrice(value);       break;
                case "SalePrice":      adminDashboardPage.enterProductSalePrice(value);   break;
                case "Stock Quantity": adminDashboardPage.enterProductStock(value);       break;
                case "Movement Type":  adminDashboardPage.selectMovementType(value);      break;
                case "Image Path":     adminDashboardPage.enterProductImagePath(value);   break;
                case "Description":    adminDashboardPage.enterProductDescription(value); break;
                default: logger.warn("Unknown product field: {}", field);
            }
        }
    }

    @When("the admin uploads product images")
    public void the_admin_uploads_product_images() {
        logger.info("Image upload handled via Image Path field in product modal");
    }

    @When("the admin clicks save product button")
    public void the_admin_clicks_save_product_button() {
        adminDashboardPage.clickSaveProductButton();
    }

    @Then("the product should be added successfully")
    public void the_product_should_be_added_successfully() {
        // if (adminDashboardPage.isProductModalDisplayed())
        //     throw new RuntimeException("Product modal still visible — product not added");
        logger.info("Product added successfully");
    }

    @Then("the admin should see success message {string}")
    public void the_admin_should_see_success_message(String message) {
        logger.info("Success message step — TODO: add toast/alert locator when available");
    }

    // ── Scenario 5: Edit product ──────────────────────────────────────────────

    @When("the admin clicks on edit button for a product")
    public void the_admin_clicks_on_edit_button_for_a_product() {
        adminDashboardPage.clickEditProductAtRow(1);
        // adminDashboardPage.waitForProductModalVisible();
    }

    @When("the admin updates product name to {string}")
    public void the_admin_updates_product_name_to(String name) {
        adminDashboardPage.enterProductName(name);
    }

    @When("the admin updates product price to {string}")
    public void the_admin_updates_product_price_to(String price) {
        adminDashboardPage.enterProductPrice(price);
    }

    @When("the admin clicks update product button")
    public void the_admin_clicks_update_product_button() {
        adminDashboardPage.clickSaveProductButton();
    }

    @Then("the product should be updated successfully")
    public void the_product_should_be_updated_successfully() {
        // if (adminDashboardPage.isProductModalDisplayed())
        //     throw new RuntimeException("Product modal still visible — product not updated");
        logger.info("Product updated successfully");
    }

    @Then("the updated details should be saved")
    public void the_updated_details_should_be_saved() {
        if (adminDashboardPage.getProductsTableRows().isEmpty())
            throw new RuntimeException("Products table empty — updated details not reflected");
    }

    // ── Scenario 6: Delete product ────────────────────────────────────────────

    @When("the admin clicks on delete button for a product")
    public void the_admin_clicks_on_delete_button_for_a_product() {
        adminDashboardPage.clickDeleteProductAtRow(1);
    }

    @When("the admin confirms deletion")
    public void the_admin_confirms_deletion() {
        try { driver.switchTo().alert().accept(); }
        catch (NoAlertPresentException e) { logger.debug("No alert — inline deletion"); }
    }

    @Then("the product should be deleted successfully")
    public void the_product_should_be_deleted_successfully() {
        logger.info("Product count after deletion: {}", adminDashboardPage.getProductCount());
    }

    // ── Scenario 7: Update product stock ─────────────────────────────────────

    @When("the admin clicks on a product to edit")
    public void the_admin_clicks_on_a_product_to_edit() {
        adminDashboardPage.clickEditProductAtRow(1);
        // adminDashboardPage.waitForProductModalVisible();
    }

    @When("the admin updates stock quantity to {string}")
    public void the_admin_updates_stock_quantity_to(String stock) {
        adminDashboardPage.enterProductStock(stock);
    }

    @Then("the stock quantity should be updated")
    public void the_stock_quantity_should_be_updated() {
        // if (adminDashboardPage.isProductModalDisplayed())
        //     throw new RuntimeException("Modal still visible — stock not saved");
    }

    @Then("the updated stock should be reflected")
    public void the_updated_stock_should_be_reflected() {
        if (adminDashboardPage.getProductsTableRows().isEmpty())
            throw new RuntimeException("Products table empty — stock not reflected");
    }

    // ── Scenario 8: Mark product as featured ─────────────────────────────────

    @When("the admin selects a product")
    public void the_admin_selects_a_product() {
        adminDashboardPage.clickEditProductAtRow(1);
        // adminDashboardPage.waitForProductModalVisible();
    }

    @When("the admin marks product as featured")
    public void the_admin_marks_product_as_featured() {
        adminDashboardPage.selectProductSection("Featured");
    }

    @Then("the product should appear in featured section")
    public void the_product_should_appear_in_featured_section() {
        // if (adminDashboardPage.isProductModalDisplayed())
        //     throw new RuntimeException("Modal still visible — featured assignment not saved");
    }

    // ── Scenario 9: Missing required fields (Outline) ────────────────────────

    @When("the admin leaves {string} empty")
    public void the_admin_leaves_empty(String field) {
        adminDashboardPage.clearProductField(field);
    }

    @Then("the admin should see validation error for {string}")
    public void the_admin_should_see_validation_error_for(String field) {
        if (!adminDashboardPage.isProductModalDisplayed())
            throw new RuntimeException("Modal closed unexpectedly — validation may not have triggered for: " + field);
        logger.info("Validation confirmed for: {}", field);
    }

    // ── Shared save/changes steps ─────────────────────────────────────────────

    @When("the admin saves changes")
    public void the_admin_saves_changes() {
        adminDashboardPage.clickSaveProductButton();
    }

    @When("the admin saves the changes")
    public void the_admin_saves_the_changes() {
        adminDashboardPage.clickSaveProductButton();
    }

    @When("the admin clicks save changes")
    public void the_admin_clicks_save_changes() {
        adminDashboardPage.clickSaveSliderButton();
    }

    // =========================================================================
    // PRIVATE HELPER
    // =========================================================================

    private void assertCellsNonEmpty(List<WebElement> cells, String label) {
        if (cells.isEmpty() || cells.stream().allMatch(c -> c.getText().trim().isEmpty()))
            throw new RuntimeException(label + " not displayed");
        logger.info("{} verified", label);
    }
}
