@Team3 @Cart
Feature: Shopping Cart and Checkout
  As a customer
  I want to manage my shopping cart and complete checkout
  So that I can purchase products

  Background:
    Given the user is on the Commerza homepage

  @AddToCart @Smoke @Regression
  Scenario: Add product to cart from product details page
    When the user navigates to the products page
    And the user clicks on a product
    And the user clicks on add to cart button
    Then the product should be added to cart
    And the cart icon should show updated quantity
    And the user should see cart confirmation message

  @AddToCart @Regression
  Scenario: Add product to cart with specific quantity
    When the user navigates to the products page
    And the user clicks on a product
    And the user sets product quantity to "3"
    And the user clicks on add to cart button
    Then the product should be added with quantity "3"
    And the cart total should reflect correct quantity

  @AddToCart @Regression
  Scenario: Add multiple products to cart
    When the user adds first product to cart
    And the user adds second product to cart
    And the user adds third product to cart
    Then the cart should contain 3 products
    And the cart icon should show "3" items

  @CartManagement @Smoke @Regression
  Scenario: View shopping cart
    Given the user has products in cart
    When the user navigates to cart page
    Then the user should see all cart items
    And each item should display product name
    And each item should display product price
    And each item should display quantity
    And each item should display subtotal
    And the user should see cart summary

  @CartManagement @Regression
  Scenario: Update product quantity in cart
    Given the user has products in cart
    When the user navigates to cart page
    And the user updates quantity to "5" for first item
    And the user clicks update cart button
    Then the item quantity should be updated to "5"
    And the item subtotal should be recalculated
    And the cart total should be updated

  @CartManagement @Regression
  Scenario: Increase quantity using increment button
    Given the user has products in cart
    When the user navigates to cart page
    And the user clicks increment button for first item
    Then the item quantity should increase by 1
    And the subtotal should be updated automatically

  @CartManagement @Regression
  Scenario: Decrease quantity using decrement button
    Given the user has products in cart with quantity greater than 1
    When the user navigates to cart page
    And the user clicks decrement button for first item
    Then the item quantity should decrease by 1
    And the subtotal should be updated automatically

  @CartManagement @Regression
  Scenario: Remove product from cart
    Given the user has products in cart
    When the user navigates to cart page
    And the user clicks remove button for first item
    Then the item should be removed from cart
    And the cart total should be recalculated
    And the user should see item removal confirmation

  @CartManagement @Regression
  Scenario: Clear entire cart
    Given the user has multiple products in cart
    When the user navigates to cart page
    And the user clicks clear cart button
    Then all items should be removed from cart
    And the user should see empty cart message

  @CartCalculations @Regression
  Scenario: Verify cart subtotal calculation
    Given the user has products in cart
    When the user navigates to cart page
    Then the subtotal should equal sum of all item subtotals

  @CartCalculations @Regression
  Scenario: Apply discount coupon
    Given the user has products in cart
    When the user navigates to cart page
    And the user enters coupon code "SAVE10"
    And the user clicks apply coupon button
    Then the discount should be applied
    And the total should be reduced by discount amount
    And the user should see coupon success message

  @CartCalculations @NegativeTesting
  Scenario: Apply invalid coupon code
    Given the user has products in cart
    When the user navigates to cart page
    And the user enters coupon code "INVALID123"
    And the user clicks apply coupon button
    Then the user should see error message "Invalid coupon code"
    And no discount should be applied

  @CartCalculations @Regression
  Scenario: Apply shipping charges
    Given the user has products in cart
    When the user navigates to cart page
    And shipping charges are applicable
    Then the shipping cost should be added to total
    And the final total should include shipping

  @CartCalculations @Regression
  Scenario: Free shipping threshold
    Given the user has products worth 5000 or more in cart
    When the user navigates to cart page
    Then the shipping should be free
    And the shipping cost should show "FREE"

  @CartPersistence @Regression
  Scenario: Cart persists across sessions
    Given the user adds products to cart
    When the user navigates away from cart page
    And the user returns to cart page
    Then all cart items should still be present

  @EmptyCart @Regression
  Scenario: View empty cart  
    When the user navigates to cart page without adding items
    Then the user should see empty cart message
    And the user should see "Continue Shopping" button
    And no cart items should be displayed

  @Checkout @Smoke @Regression
  Scenario: Proceed to checkout as logged in user
    Given the user is logged in with email "testuser@commerza.com" and password "Test@123"
    And the user has products in cart
    When the user navigates to cart page
    And the user clicks proceed to checkout button
    Then the user should be on checkout page
    And user details should be pre-filled

  @Checkout @Regression
  Scenario: Proceed to checkout as guest
    Given the user has products in cart
    When the user navigates to cart page
    And the user clicks proceed to checkout button
    Then the user should see login or guest checkout options

  @Checkout @Smoke @Regression
  Scenario: Complete checkout with all details
    Given the user is logged in and has products in cart
    When the user proceeds to checkout
    And the user enters shipping address
    | Field          | Value              |
    | Full Name      | John Doe           |
    | Phone          | +923001234567      |
    | Address Line 1 | 123 Main Street    |
    | City           | Karachi            |
    | Postal Code    | 75500              |
    | Country        | Pakistan           |
    And the user selects payment method "Cash on Delivery"
    And the user agrees to terms and conditions
    And the user clicks place order button
    Then the order should be placed successfully
    And the user should see order confirmation page
    And the user should receive order number

  @Checkout @Regression
  Scenario: Checkout with different billing address
    Given the user is logged in and has products in cart
    When the user proceeds to checkout
    And the user checks "Use different billing address"
    And the user enters billing address
    Then both shipping and billing addresses should be saved

  @Checkout @NegativeTesting
  Scenario Outline: Checkout with missing required fields
    Given the user is logged in and has products in cart
    When the user proceeds to checkout
    And the user leaves "<field>" empty
    And the user clicks place order button
    Then the user should see validation error for "<field>"

    Examples:
      | field          |
      | Full Name      |
      | Phone          |
      | Address        |
      | City           |
      | Postal Code    |

  @Checkout @Regression
  Scenario: Select payment method
    Given the user is on checkout page with items
    When the user selects payment method "Cash on Delivery"
    Then the payment method should be highlighted
    And payment specific details should be shown

  @Checkout @Regression
  Scenario: Review order before placing
    Given the user is on checkout page with items
    Then the user should see order summary
    And order summary should show all products
    And order summary should show subtotal
    And order summary should show shipping charges
    And order summary should show total amount

  @OrderTracking @Regression
  Scenario: View order confirmation after checkout
    Given the user has placed an order
    Then the user should see order confirmation message
    And confirmation should display order number
    And confirmation should display order date
    And confirmation should display delivery estimate
    And the user should see "Track Order" button

  @OrderTracking @Regression
  Scenario: Track order using order number
    Given the user has an order number "ORD123456"
    When the user navigates to order tracking page
    And the user enters order number "ORD123456"
    And the user enters email "testuser@commerza.com"
    And the user clicks track order button
    Then the user should see order status
    And the user should see order timeline
    And the user should see delivery information

  @OrderTracking @NegativeTesting
  Scenario: Track order with invalid order number
    When the user navigates to order tracking page
    And the user enters order number "INVALID123"
    And the user enters email "testuser@commerza.com"
    And the user clicks track order button
    Then the user should see error message "Order not found"

  @OrderHistory @Regression
  Scenario: View order history in account
    Given the user is logged in with email "testuser@commerza.com" and password "Test@123"
    And the user has placed orders
    When the user navigates to account page
    And the user clicks on order history
    Then the user should see list of all orders
    And each order should show order number
    And each order should show order date
    And each order should show status
    And each order should show total amount

  @ReturnRequest @Regression
  Scenario: Request product return
    Given the user is logged in and has received an order
    When the user navigates to returns page
    And the user enters order number
    And the user selects product to return
    And the user enters return reason "Product defective"
    And the user submits return request
    Then the return request should be submitted successfully
    And the user should see return confirmation
