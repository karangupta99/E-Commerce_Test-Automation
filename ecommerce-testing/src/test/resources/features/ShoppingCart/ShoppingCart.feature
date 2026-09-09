@ShoppingCart @Cart
Feature: Shopping Cart and Purchase Flow
  As a customer
  I want to add products to cart and complete purchases
  So that I can buy products from the store

  Background:
    Given the user is on the Commerza homepage

  @AddToCart @Smoke @Regression
  Scenario: Add product to cart from product listing
    When the user navigates to the products page
    And the user clicks on add to cart button for a product
    Then the product should be added to cart
    And the cart count should increase

  @AddToCart @Regression
  Scenario: Add multiple products to cart
    When the user navigates to the products page
    And the user adds 3 products to cart
    Then the cart count should show 3 items

  @ViewCart @Smoke @Regression
  Scenario: View shopping cart
    Given the user has products in cart
    When the user navigates to cart page
    Then the user should see cart items
    And each cart item should display product name
    And each cart item should display product price
    And the user should see order summary

  @UpdateCart @Regression
  Scenario: Update product quantity in cart
    Given the user has products in cart
    When the user navigates to cart page
    And the user increases the quantity of first item
    Then the item quantity should be updated
    And the cart total should be updated

  @RemoveFromCart @Regression
  Scenario: Remove product from cart
    Given the user has products in cart
    When the user navigates to cart page
    And the user removes a product from cart
    Then the product should be removed from cart

  @Checkout @Smoke @Regression
  Scenario: Proceed to checkout from cart
    Given the user is logged in with valid credentials
    And the user has products in cart
    When the user navigates to cart page
    And the user clicks proceed to checkout
    Then the checkout modal should be displayed
    And the user should see checkout form

  @CompletePurchase @Smoke @Regression
  Scenario: Complete purchase with Cash on Delivery
    Given the user is logged in with valid credentials
    And the user has products in cart
    When the user navigates to cart page
    And the user clicks proceed to checkout
    And the user enters checkout details
      | fullName | Test User          |
      | phone    | +919876543210      |
      | address  | 123 Test Street    |
      | email    | test@example.com   |
    And the user clicks complete order
    Then the order should be placed successfully
    And the user should see order confirmation

  @AddToWishlist @Smoke @Regression
  Scenario: Add product to wishlist from product listing
    Given the user is logged in with valid credentials
    When the user navigates to the products page
    And the user clicks on wishlist button for a product
    Then the product should be saved to wishlist
    And the wishlist count should increase

  @MoveWishlistToCart @Regression
  Scenario: Move product from wishlist to cart
    Given the user is logged in with valid credentials
    And the user has products in wishlist
    When the user navigates to wishlist page
    And the user clicks add to cart for a wishlist item
    Then the product should be added to cart from wishlist

  @OrderTracking @Smoke @Regression
  Scenario: Track order with valid order ID
    When the user navigates to order tracking page
    And the user enters order ID "ORD-12345"
    And the user clicks track order button
    Then the user should see order tracking form
    And the tracking timeline should be displayed

  @OrderTracking @Regression
  Scenario: Track order with invalid order ID
    When the user navigates to order tracking page
    And the user enters order ID "INVALID-ORDER"
    And the user clicks track order button
    Then the user should see order tracking result

  @EmptyCart @Regression
  Scenario: View empty cart message
    When the user navigates to cart page
    Then the user should see empty cart or cart page

  @BuyNow @Regression
  Scenario: Buy product directly using Buy Now
    Given the user is logged in with valid credentials
    When the user navigates to the products page
    And the user clicks on buy now button for a product
    Then the user should be directed to cart or checkout

  @CartPersistence @Regression
  Scenario: Cart persists after page refresh
    Given the user has products in cart
    When the user refreshes the page
    And the user navigates to cart page
    Then the cart should still contain the products

