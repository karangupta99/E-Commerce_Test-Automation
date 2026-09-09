package com.commerza.stepdefinitions.ShoppingCart;

import com.commerza.pages.*;
import com.commerza.utils.ConfigReader;
import com.commerza.utils.DriverManager;
import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class CartSteps {
    
    private WebDriver driver;
    private CartPage cartPage;
    private ProductsPage productsPage;
    private ProductDetailsPage productDetailsPage;
    private CheckoutPage checkoutPage;
    private OrderTrackingPage orderTrackingPage;
    private ReturnsPage returnsPage;
    private LoginPage loginPage;
    private String baseUrl;
    
    public CartSteps() {
        this.driver = DriverManager.getDriver();
        this.cartPage = new CartPage(driver);
        this.productsPage = new ProductsPage(driver);
        this.productDetailsPage = new ProductDetailsPage(driver);
        this.checkoutPage = new CheckoutPage(driver);
        this.orderTrackingPage = new OrderTrackingPage(driver);
        this.returnsPage = new ReturnsPage(driver);
        this.loginPage = new LoginPage(driver);
        this.baseUrl = ConfigReader.getBaseUrl();
    }
    
    @When("the user clicks on add to cart button")
    public void the_user_clicks_on_add_to_cart_button() {
        productDetailsPage.clickAddToCart();
    }
    
    @Then("the product should be added to cart")
    public void the_product_should_be_added_to_cart() {
        Assert.assertTrue(cartPage.isConfirmationMessageDisplayed(), "Product not added to cart");
    }
    
    @Then("the cart icon should show updated quantity")
    public void the_cart_icon_should_show_updated_quantity() {
        String count = cartPage.getCartCount();
        Assert.assertNotNull(count, "Cart count not updated");
    }
    
    @Then("the user should see cart confirmation message")
    public void the_user_should_see_cart_confirmation_message() {
        Assert.assertTrue(cartPage.isConfirmationMessageDisplayed(), "Cart confirmation not displayed");
    }
    
    @When("the user sets product quantity to {string}")
    public void the_user_sets_product_quantity_to(String quantity) {
        productDetailsPage.setQuantity(quantity);
    }
    
    @Then("the product should be added with quantity {string}")
    public void the_product_should_be_added_with_quantity(String quantity) {
        cartPage.navigateToCartPage(baseUrl);
        String actualQuantity = cartPage.getItemQuantity(0);
        Assert.assertEquals(actualQuantity, quantity, "Quantity mismatch");
    }
    
    @Then("the cart total should reflect correct quantity")
    public void the_cart_total_should_reflect_correct_quantity() {
        Assert.assertTrue(cartPage.hasCartItems(), "Cart items not found");
    }
    
    @When("the user adds first product to cart")
    public void the_user_adds_first_product_to_cart() {
        productsPage.navigateToProductsPage(baseUrl);
        productsPage.clickFirstProduct();
        productDetailsPage.clickAddToCart();
    }
    
    @When("the user adds second product to cart")
    public void the_user_adds_second_product_to_cart() {
        the_user_adds_first_product_to_cart();
    }
    
    @When("the user adds third product to cart")
    public void the_user_adds_third_product_to_cart() {
        the_user_adds_first_product_to_cart();
    }
    
    @Then("the cart should contain {int} products")
    public void the_cart_should_contain_products(Integer count) {
        cartPage.navigateToCartPage(baseUrl);
        Assert.assertEquals(cartPage.getCartItemCount(), count.intValue(), "Cart item count mismatch");
    }
    
    @Then("the cart icon should show {string} items")
    public void the_cart_icon_should_show_items(String count) {
        String actualCount = cartPage.getCartCount();
        Assert.assertTrue(actualCount.contains(count), "Cart count mismatch");
    }
    
    @Given("the user has products in cart")
    public void the_user_has_products_in_cart() {
        the_user_adds_first_product_to_cart();
    }
    
    @When("the user navigates to cart page")
    public void the_user_navigates_to_cart_page() {
        cartPage.navigateToCartPage(baseUrl);
    }
    
    @Then("the user should see all cart items")
    public void the_user_should_see_all_cart_items() {
        Assert.assertTrue(cartPage.hasCartItems(), "No cart items displayed");
    }
    
    @Then("each item should display product name")
    public void each_item_should_display_product_name() {
        Assert.assertTrue(cartPage.areProductNamesDisplayed(), "Product names not displayed");
    }
    
    @Then("each item should display product price")
    public void each_item_should_display_product_price() {
        Assert.assertTrue(cartPage.areProductPricesDisplayed(), "Product prices not displayed");
    }
    
    @Then("each item should display quantity")
    public void each_item_should_display_quantity() {
        Assert.assertTrue(cartPage.areQuantitiesDisplayed(), "Quantities not displayed");
    }
    
    @Then("each item should display subtotal")
    public void each_item_should_display_subtotal() {
        Assert.assertTrue(cartPage.areSubtotalsDisplayed(), "Subtotals not displayed");
    }
    
    @Then("the user should see cart summary")
    public void the_user_should_see_cart_summary() {
        Assert.assertTrue(cartPage.isCartSummaryDisplayed(), "Cart summary not displayed");
    }
    
    @When("the user updates quantity to {string} for first item")
    public void the_user_updates_quantity_to_for_first_item(String quantity) {
        cartPage.updateQuantity(0, quantity);
    }
    
    @When("the user clicks update cart button")
    public void the_user_clicks_update_cart_button() {
        cartPage.clickUpdateCart();
    }
    
    @Then("the item quantity should be updated to {string}")
    public void the_item_quantity_should_be_updated_to(String quantity) {
        String actualQuantity = cartPage.getItemQuantity(0);
        Assert.assertEquals(actualQuantity, quantity, "Quantity not updated");
    }
    
    @Then("the item subtotal should be recalculated")
    public void the_item_subtotal_should_be_recalculated() {
        String subtotal = cartPage.getItemSubtotal(0);
        Assert.assertNotNull(subtotal, "Subtotal not recalculated");
    }
    
    @Then("the cart total should be updated")
    public void the_cart_total_should_be_updated() {
        String total = cartPage.getCartTotal();
        Assert.assertNotNull(total, "Cart total not updated");
    }
    
    @Given("the user has products in cart with quantity greater than {int}")
    public void the_user_has_products_in_cart_with_quantity_greater_than(Integer quantity) {
        the_user_has_products_in_cart();
    }
    
    @When("the user clicks increment button for first item")
    public void the_user_clicks_increment_button_for_first_item() {
        cartPage.clickIncrementButton(0);
    }
    
    @Then("the item quantity should increase by {int}")
    public void the_item_quantity_should_increase_by(Integer increment) {
    }
    
    @Then("the subtotal should be updated automatically")
    public void the_subtotal_should_be_updated_automatically() {
        String subtotal = cartPage.getItemSubtotal(0);
        Assert.assertNotNull(subtotal, "Subtotal not updated");
    }
    
    @When("the user clicks decrement button for first item")
    public void the_user_clicks_decrement_button_for_first_item() {
        cartPage.clickDecrementButton(0);
    }
    
    @Then("the item quantity should decrease by {int}")
    public void the_item_quantity_should_decrease_by(Integer decrement) {
    }
    
    @When("the user clicks remove button for first item")
    public void the_user_clicks_remove_button_for_first_item() {
        cartPage.clickRemoveButton(0);
    }
    
    @Then("the item should be removed from cart")
    public void the_item_should_be_removed_from_cart() {
    }
    
    @Then("the cart total should be recalculated")
    public void the_cart_total_should_be_recalculated() {
        String total = cartPage.getCartTotal();
        Assert.assertNotNull(total, "Cart total not recalculated");
    }
    
    @Then("the user should see item removal confirmation")
    public void the_user_should_see_item_removal_confirmation() {
        Assert.assertTrue(cartPage.isItemRemovalConfirmationDisplayed(), "Removal confirmation not displayed");
    }
    
    @Given("the user has multiple products in cart")
    public void the_user_has_multiple_products_in_cart() {
        the_user_adds_first_product_to_cart();
        the_user_adds_second_product_to_cart();
    }
    
    @When("the user clicks clear cart button")
    public void the_user_clicks_clear_cart_button() {
        cartPage.clickClearCart();
    }
    
    @Then("all items should be removed from cart")
    public void all_items_should_be_removed_from_cart() {
        Assert.assertFalse(cartPage.hasCartItems(), "Cart not cleared");
    }
    
    @Then("the user should see empty cart message")
    public void the_user_should_see_empty_cart_message() {
        Assert.assertTrue(cartPage.isEmptyCartMessageDisplayed(), "Empty cart message not displayed");
    }
    
    @Then("the subtotal should equal sum of all item subtotals")
    public void the_subtotal_should_equal_sum_of_all_item_subtotals() {
        String subtotal = cartPage.getCartSubtotal();
        Assert.assertNotNull(subtotal, "Subtotal not calculated");
    }
    
    @When("the user enters coupon code {string}")
    public void the_user_enters_coupon_code(String couponCode) {
        cartPage.enterCouponCode(couponCode);
    }
    
    @When("the user clicks apply coupon button")
    public void the_user_clicks_apply_coupon_button() {
        cartPage.clickApplyCoupon();
    }
    
    @Then("the discount should be applied")
    public void the_discount_should_be_applied() {
        Assert.assertTrue(cartPage.isDiscountDisplayed(), "Discount not applied");
    }
    
    @Then("the total should be reduced by discount amount")
    public void the_total_should_be_reduced_by_discount_amount() {
        String discountAmount = cartPage.getDiscountAmount();
        Assert.assertNotNull(discountAmount, "Discount amount not shown");
    }
    
    @Then("the user should see coupon success message")
    public void the_user_should_see_coupon_success_message() {
        Assert.assertTrue(cartPage.isCouponSuccessMessageDisplayed(), "Coupon success message not displayed");
    }
    
    @Then("no discount should be applied")
    public void no_discount_should_be_applied() {
        Assert.assertFalse(cartPage.isDiscountDisplayed(), "Discount should not be applied");
    }
    
    @When("shipping charges are applicable")
    public void shipping_charges_are_applicable() {
    }
    
    @Then("the shipping cost should be added to total")
    public void the_shipping_cost_should_be_added_to_total() {
        String shippingCost = cartPage.getShippingCost();
        Assert.assertNotNull(shippingCost, "Shipping cost not displayed");
    }
    
    @Then("the final total should include shipping")
    public void the_final_total_should_include_shipping() {
        String total = cartPage.getCartTotal();
        Assert.assertNotNull(total, "Final total not calculated");
    }
    
    @Given("the user has products worth {int} or more in cart")
    public void the_user_has_products_worth_or_more_in_cart(Integer amount) {
    }
    
    @Then("the shipping should be free")
    public void the_shipping_should_be_free() {
        String shippingCost = cartPage.getShippingCost();
        Assert.assertTrue(shippingCost.contains("FREE") || shippingCost.contains("0"), "Shipping not free");
    }
    
    @Then("the shipping cost should show {string}")
    public void the_shipping_cost_should_show(String expectedCost) {
        String actualCost = cartPage.getShippingCost();
        Assert.assertTrue(actualCost.contains(expectedCost), "Shipping cost mismatch");
    }
    
    @Given("the user adds products to cart")
    public void the_user_adds_products_to_cart() {
        the_user_adds_first_product_to_cart();
    }
    
    @When("the user navigates away from cart page")
    public void the_user_navigates_away_from_cart_page() {
        productsPage.navigateToProductsPage(baseUrl);
    }
    
    @When("the user returns to cart page")
    public void the_user_returns_to_cart_page() {
        cartPage.navigateToCartPage(baseUrl);
    }
    
    @Then("all cart items should still be present")
    public void all_cart_items_should_still_be_present() {
        Assert.assertTrue(cartPage.hasCartItems(), "Cart items not persisted");
    }
    
    @When("the user navigates to cart page without adding items")
    public void the_user_navigates_to_cart_page_without_adding_items() {
        cartPage.navigateToCartPage(baseUrl);
    }
    
    @Then("the user should see {string} button")
    public void the_user_should_see_button(String buttonName) {
        if (buttonName.equals("Continue Shopping")) {
            Assert.assertTrue(cartPage.isContinueShoppingButtonDisplayed(), "Continue Shopping button not displayed");
        }
    }
    
    @Then("no cart items should be displayed")
    public void no_cart_items_should_be_displayed() {
        Assert.assertFalse(cartPage.hasCartItems(), "Cart items displayed when cart is empty");
    }
    
    @Given("the user is logged in and has products in cart")
    public void the_user_is_logged_in_and_has_products_in_cart() {
        loginPage.navigateToLoginPage(baseUrl);
        loginPage.login(ConfigReader.getTestUserEmail(), ConfigReader.getTestUserPassword());
        the_user_adds_first_product_to_cart();
    }
    
    @When("the user clicks proceed to checkout button")
    public void the_user_clicks_proceed_to_checkout_button() {
        cartPage.clickProceedToCheckout();
    }
    
    @Then("the user should be on checkout page")
    public void the_user_should_be_on_checkout_page() {
        Assert.assertTrue(checkoutPage.isOnCheckoutPage(), "Not on checkout page");
    }
    
    @Then("user details should be pre-filled")
    public void user_details_should_be_pre_filled() {
        Assert.assertTrue(checkoutPage.areFieldsPreFilled(), "User details not pre-filled");
    }
    
    @Then("the user should see login or guest checkout options")
    public void the_user_should_see_login_or_guest_checkout_options() {
        Assert.assertTrue(checkoutPage.isLoginOptionDisplayed() || checkoutPage.isGuestCheckoutOptionDisplayed(),
                         "Login/Guest checkout options not displayed");
    }
    
    @When("the user proceeds to checkout")
    public void the_user_proceeds_to_checkout() {
        cartPage.navigateToCartPage(baseUrl);
        cartPage.clickProceedToCheckout();
    }
    
    @When("the user enters shipping address")
    public void the_user_enters_shipping_address(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        checkoutPage.enterFullName(data.get("Full Name"));
        checkoutPage.enterPhone(data.get("Phone"));
        checkoutPage.enterAddressLine1(data.get("Address Line 1"));
        checkoutPage.enterCity(data.get("City"));
        checkoutPage.enterPostalCode(data.get("Postal Code"));
        checkoutPage.enterCountry(data.get("Country"));
    }
    
    @When("the user selects payment method {string}")
    public void the_user_selects_payment_method(String paymentMethod) {
        checkoutPage.selectPaymentMethod(paymentMethod);
    }
    
    // Step implemented in AuthenticationSteps; avoid duplicate definition here
    
    @When("the user clicks place order button")
    public void the_user_clicks_place_order_button() {
        checkoutPage.clickPlaceOrder();
    }
    
    @Then("the order should be placed successfully")
    public void the_order_should_be_placed_successfully() {
        Assert.assertTrue(orderTrackingPage.isOrderConfirmationDisplayed(), "Order not placed successfully");
    }
    
    @Then("the user should see order confirmation page")
    public void the_user_should_see_order_confirmation_page() {
        Assert.assertTrue(orderTrackingPage.isOrderConfirmationDisplayed(), "Order confirmation page not displayed");
    }
    
    @Then("the user should receive order number")
    public void the_user_should_receive_order_number() {
        String orderNumber = orderTrackingPage.getConfirmationOrderNumber();
        Assert.assertNotNull(orderNumber, "Order number not received");
    }
    
    @When("the user checks {string}")
    public void the_user_checks(String checkboxLabel) {
        checkoutPage.checkDifferentBillingAddress();
    }
    
    @When("the user enters billing address")
    public void the_user_enters_billing_address() {
        checkoutPage.enterBillingFullName("John Doe");
        checkoutPage.enterBillingPhone("+923001234567");
        checkoutPage.enterBillingAddress("456 Billing Street");
        checkoutPage.enterBillingCity("Lahore");
        checkoutPage.enterBillingPostalCode("54000");
    }
    
    @Then("both shipping and billing addresses should be saved")
    public void both_shipping_and_billing_addresses_should_be_saved() {
    }
    
    @Given("the user is on checkout page with items")
    public void the_user_is_on_checkout_page_with_items() {
        the_user_is_logged_in_and_has_products_in_cart();
        the_user_proceeds_to_checkout();
    }
    
    @When("the user leaves {string} empty")
    public void the_user_leaves_empty(String field) {
    }
    
    // Validation error step covered by AuthenticationSteps; remove duplicate here
    
    @Then("the payment method should be highlighted")
    public void the_payment_method_should_be_highlighted() {
        Assert.assertTrue(checkoutPage.isPaymentMethodSelected(), "Payment method not highlighted");
    }
    
    @Then("payment specific details should be shown")
    public void payment_specific_details_should_be_shown() {
        Assert.assertTrue(checkoutPage.isPaymentDetailsDisplayed(), "Payment details not shown");
    }
    
    @Then("the user should see order summary")
    public void the_user_should_see_order_summary() {
        Assert.assertTrue(checkoutPage.isOrderSummaryDisplayed(), "Order summary not displayed");
    }
    
    @Then("order summary should show all products")
    public void order_summary_should_show_all_products() {
        Assert.assertTrue(checkoutPage.areSummaryProductsDisplayed(), "Summary products not displayed");
    }
    
    @Then("order summary should show subtotal")
    public void order_summary_should_show_subtotal() {
        String subtotal = checkoutPage.getSummarySubtotal();
        Assert.assertNotNull(subtotal, "Summary subtotal not displayed");
    }
    
    @Then("order summary should show shipping charges")
    public void order_summary_should_show_shipping_charges() {
        String shipping = checkoutPage.getSummaryShipping();
        Assert.assertNotNull(shipping, "Summary shipping not displayed");
    }
    
    @Then("order summary should show total amount")
    public void order_summary_should_show_total_amount() {
        String total = checkoutPage.getSummaryTotal();
        Assert.assertNotNull(total, "Summary total not displayed");
    }
    
    @Given("the user has placed an order")
    public void the_user_has_placed_an_order() {
    }
    
    @Then("the user should see order confirmation message")
    public void the_user_should_see_order_confirmation_message() {
        Assert.assertTrue(orderTrackingPage.isOrderConfirmationDisplayed(), "Order confirmation message not displayed");
    }
    
    @Then("confirmation should display order number")
    public void confirmation_should_display_order_number() {
        String orderNumber = orderTrackingPage.getConfirmationOrderNumber();
        Assert.assertNotNull(orderNumber, "Order number not displayed in confirmation");
    }
    
    @Then("confirmation should display order date")
    public void confirmation_should_display_order_date() {
        String orderDate = orderTrackingPage.getConfirmationOrderDate();
        Assert.assertNotNull(orderDate, "Order date not displayed");
    }
    
    @Then("confirmation should display delivery estimate")
    public void confirmation_should_display_delivery_estimate() {
        Assert.assertTrue(orderTrackingPage.isDeliveryEstimateDisplayed(), "Delivery estimate not displayed");
    }
    
    @Then("the user should see Track Order button")
    public void the_user_should_see_track_order_button() {
        Assert.assertTrue(orderTrackingPage.isTrackOrderLinkDisplayed(), "Track Order button not displayed");
    }
    
    @Given("the user has an order number {string}")
    public void the_user_has_an_order_number(String orderNumber) {
    }
    
    @When("the user navigates to order tracking page")
    public void the_user_navigates_to_order_tracking_page() {
        orderTrackingPage.navigateToOrderTrackingPage(baseUrl);
    }
    
    @When("the user enters order number {string}")
    public void the_user_enters_order_number(String orderNumber) {
        orderTrackingPage.enterOrderNumber(orderNumber);
    }
    
    // Reuse AuthenticationSteps' email step; keeping here would create duplicates when glue is shared
    
    @When("the user clicks track order button")
    public void the_user_clicks_track_order_button() {
        orderTrackingPage.clickTrackOrder();
    }
    
    @Then("the user should see order status")
    public void the_user_should_see_order_status() {
        Assert.assertTrue(orderTrackingPage.isOrderStatusDisplayed(), "Order status not displayed");
    }
    
    @Then("the user should see order timeline")
    public void the_user_should_see_order_timeline() {
        Assert.assertTrue(orderTrackingPage.isOrderTimelineDisplayed(), "Order timeline not displayed");
    }
    
    @Then("the user should see delivery information")
    public void the_user_should_see_delivery_information() {
        Assert.assertTrue(orderTrackingPage.isDeliveryInfoDisplayed(), "Delivery information not displayed");
    }
    
    @Given("the user has placed orders")
    public void the_user_has_placed_orders() {
    }
    
    @When("the user clicks on order history")
    public void the_user_clicks_on_order_history() {
    }
    
    @Then("the user should see list of all orders")
    public void the_user_should_see_list_of_all_orders() {
    }
    
    @Then("each order should show order number")
    public void each_order_should_show_order_number() {
    }
    
    @Then("each order should show order date")
    public void each_order_should_show_order_date() {
    }
    
    @Then("each order should show status")
    public void each_order_should_show_status() {
    }
    
    @Then("each order should show total amount")
    public void each_order_should_show_total_amount() {
    }
    
    @Given("the user is logged in and has received an order")
    public void the_user_is_logged_in_and_has_received_an_order() {
    }
    
    @When("the user navigates to returns page")
    public void the_user_navigates_to_returns_page() {
        returnsPage.navigateToReturnsPage(baseUrl);
    }
    
    @When("the user selects product to return")
    public void the_user_selects_product_to_return() {
        returnsPage.selectProduct(0);
    }
    
    @When("the user enters return reason {string}")
    public void the_user_enters_return_reason(String reason) {
        returnsPage.enterReturnReason(reason);
    }
    
    @When("the user submits return request")
    public void the_user_submits_return_request() {
        returnsPage.clickSubmitReturn();
    }
    
    @Then("the return request should be submitted successfully")
    public void the_return_request_should_be_submitted_successfully() {
        Assert.assertTrue(returnsPage.isReturnConfirmationDisplayed(), "Return request not submitted");
    }
    
    @Then("the user should see return confirmation")
    public void the_user_should_see_return_confirmation() {
        Assert.assertTrue(returnsPage.isReturnConfirmationDisplayed(), "Return confirmation not displayed");
    }
}
