@ProductBrowsing @Products
Feature: Product Browsing and Search
  As a customer
  I want to browse and search for products
  So that I can find products I want to purchase

  Background:
    Given the user is on the Commerza homepage

  @ProductListing @Smoke @Regression
  Scenario: View all products on products page
    When the user navigates to the products page
    Then the user should see a list of products
    And each product should display product image
    And each product should display product name
    And each product should display product price

  @ProductListing @Regression
  Scenario: Product listing pagination
    When the user navigates to the products page
    And there are more than 12 products
    Then the user should see pagination controls
    When the user clicks on next page
    Then the user should see the next set of products

  @ProductSearch @Smoke @Regression
  Scenario: Search for products with valid keyword
    When the user enters "Automatic" in the search box
    And the user clicks on search button
    Then the user should see search results for "Automatic"
    And all displayed products should match the search keyword

  @ProductSearch @Regression
  Scenario: Search with no results
    When the user enters "NonexistentProduct" in the search box
    And the user clicks on search button
    Then the user should see "No products found" message

  @ProductSearch @Regression
  Scenario Outline: Search for products with different keywords
    When the user enters "<searchKeyword>" in the search box
    And the user clicks on search button
    Then the user should see search results for "<searchKeyword>"

    Examples:
      | searchKeyword |
      | Smart         |
      | Sports        |
      | Minimal       |
      | Automatic     |

  @CategoryFilter @Smoke @Regression
  Scenario: Filter products by category
    When the user navigates to the products page
    And the user selects "Smart Watches" category
    Then the user should see only products from "Smart Watches" category
    And the category filter should be highlighted

  @CategoryFilter @Regression
  Scenario: View products in specific category page
    When the user clicks on "Sports Watches" category link
    Then the user should be on category page for "Sports"
    And the user should see products from "Sports" category only

  @CategoryFilter @Regression
  Scenario: Navigate between different category pages
    When the user navigates to "shop-category-a.html"
    Then the user should see category A products
    When the user clicks on category B link
    Then the user should see category B products

  @ProductDetails @Smoke @Regression
  Scenario: View product details
    When the user navigates to the products page
    And the user clicks on a product
    Then the user should see the product details page
    And the product details should include product name
    And the product details should include product price
    And the product details should include product description
    And the product details should include product images
    And the user should see "Add to Cart" button
    And the user should see "Add to Wishlist" button

  @ProductDetails @Regression
  Scenario: View product image gallery
    When the user is on a product details page
    And the product has multiple images
    Then the user should see image thumbnails
    When the user clicks on a thumbnail
    Then the main product image should change

  @PriceFilter @Regression
  Scenario: Filter products by price range
    When the user navigates to the products page
    And the user sets minimum price to "1000"
    And the user sets maximum price to "5000"
    And the user applies price filter
    Then all displayed products should be within price range

  @Sorting @Regression
  Scenario Outline: Sort products by different criteria
    When the user navigates to the products page
    And the user selects sort by "<sortOption>"
    Then the products should be sorted by "<sortOption>"

    Examples:
      | sortOption        |
      | Price: Low to High|
      | Price: High to Low|
      | Name: A to Z      |
      | Name: Z to A      |
      | Newest First      |

  @Wishlist @Smoke @Regression
  Scenario: Add product to wishlist from product listing
    Given the user is logged in with email "testuser@commerza.com" and password "Test@123"
    When the user navigates to the products page
    And the user clicks on wishlist icon for a product
    Then the product should be added to wishlist
    And the user should see wishlist confirmation message

  @Wishlist @Regression
  Scenario: Add product to wishlist from product details
    Given the user is logged in with email "testuser@commerza.com" and password "Test@123"
    When the user is on a product details page
    And the user clicks on "Add to Wishlist" button
    Then the product should be added to wishlist
    And the wishlist icon should be filled

  @Wishlist @Regression
  Scenario: View wishlist page
    Given the user is logged in with email "testuser@commerza.com" and password "Test@123"
    And the user has products in wishlist
    When the user navigates to wishlist page
    Then the user should see all wishlisted products
    And each product should have "Remove" option
    And each product should have "Add to Cart" option

  @Wishlist @Regression
  Scenario: Remove product from wishlist
    Given the user is logged in with email "testuser@commerza.com" and password "Test@123"
    And the user has products in wishlist
    When the user navigates to wishlist page
    And the user clicks on remove button for a product
    Then the product should be removed from wishlist
    And the user should see removal confirmation

  @Wishlist @NegativeTesting
  Scenario: Add to wishlist without login
    When the user navigates to the products page
    And the user clicks on wishlist icon for a product
    Then the user should be prompted to login

  @Compare @Regression
  Scenario: Add products to compare
    When the user navigates to the products page
    And the user clicks on compare icon for first product
    And the user clicks on compare icon for second product
    Then the user should see compare notification with count "2"

  @Compare @Regression
  Scenario: View product comparison page
    When the user has products in compare list
    And the user navigates to compare page
    Then the user should see comparison table
    And the table should show product specifications
    And each product should have "Add to Cart" button
    And each product should have "Remove" button

  @Compare @Regression
  Scenario: Remove product from comparison
    When the user has products in compare list
    And the user navigates to compare page
    And the user removes a product from comparison
    Then the product should be removed from comparison table
    And the compare count should be updated

  @Compare @NegativeTesting
  Scenario: Compare with maximum limit
    When the user adds 4 products to compare
    And the user tries to add 5th product to compare
    Then the user should see message "Maximum 4 products can be compared"

  @ProductAvailability @Regression
  Scenario: View in-stock product
    When the user views a product that is in stock
    Then the product should show "In Stock" status
    And the "Add to Cart" button should be enabled

  @ProductAvailability @Regression
  Scenario: View out-of-stock product
    When the user views a product that is out of stock
    Then the product should show "Out of Stock" status
    And the "Add to Cart" button should be disabled
    And the user should see "Notify Me" option
