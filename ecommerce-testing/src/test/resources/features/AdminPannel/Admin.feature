@AdminTest
Feature: Admin Panel and Order Management
  As an admin
  I want to manage products, orders, and website content
  So that I can operate the e-commerce platform effectively

  Background:
    Given the admin navigates to admin login page

  @AdminLogin @Smoke @Regression
  Scenario: Successful admin login with valid credentials
    When the admin enters valid admin email
    And the admin enters valid admin password
    And the admin clicks on admin login button
    Then the admin should be logged in successfully
    And the admin should see the admin dashboard
    And the driver clears local storage

  @AdminLogin @NegativeTesting
  Scenario: Admin login with invalid credentials
    When the admin enters invalid admin email
    And the admin enters invalid admin password
    And the admin clicks on admin login button
    Then the admin should see admin login error message
    And the admin should not be able to access dashboard
    And the driver clears local storage

  @AdminLogin @Regression
  Scenario: Admin forgot password
    When the admin clicks on admin forgot password link
    And the admin clicks on send admin reset link
    Then the admin should see password reset email confirmation
    And the driver clears local storage

  @ProductManagement @Regression
  Scenario: Add new product
    Given the admin is logged in to admin panel
    When the admin navigates to products management section
    And the admin clicks on add new product button
    And the admin enters product details
      | Field            | Value                          |
      | Product Name     | Premium Automatic Watch        |
      | Section          | THE AUTOMATIC VAULT            |
      | Price            | 4500                           |
      | SalePrice        | 2500                           |
      | Stock Quantity   | 50                             |
      | Movement Type    | Quartz                         |
      | Image Path       | Image placeholder              |
      | Description      | Elegant automatic watch        |
    And the admin uploads product images
    And the admin clicks save product button
    Then the product should be added successfully
    And the admin should see success message "Product added"
    And the driver clears local storage

  @ProductManagement @Regression
  Scenario: Edit existing product
    Given the admin is logged in to admin panel
    When the admin navigates to products management section
    And the admin clicks on edit button for a product
    And the admin updates product name to "Updated Watch Name"
    And the admin updates product price to "5000"
    And the admin clicks update product button
    Then the product should be updated successfully
    And the updated details should be saved
    And the driver clears local storage

  @ProductManagement @Regression 
  Scenario: Delete product
    Given the admin is logged in to admin panel
    When the admin navigates to products management section
    And the admin clicks on delete button for a product
    And the admin confirms deletion
    Then the product should be deleted successfully
    And the driver clears local storage

  @ProductManagement @Regression
  Scenario: Update product stock
    Given the admin is logged in to admin panel
    When the admin navigates to products management section
    And the admin clicks on a product to edit
    And the admin updates stock quantity to "100"
    And the admin clicks update product button
    Then the stock quantity should be updated
    And the updated stock should be reflected
    And the driver clears local storage

  @ProductManagement @Regression
  Scenario: Set product as featured
    Given the admin is logged in to admin panel
    When the admin navigates to products management section
    And the admin selects a product
    And the admin marks product as featured
    And the admin saves changes
    Then the product should appear in featured section
    And the driver clears local storage

  @ProductManagement @NegativeTesting @Smoke
  Scenario Outline: Add product with missing required fields
    Given the admin is logged in to admin panel
    When the admin navigates to products management section
    And the admin clicks on add new product button
    And the admin leaves "<field>" empty
    And the admin clicks save product button
    Then the admin should see validation error for "<field>"
    And the driver clears local storage

    Examples:
      | field           |
      | Product Name    |
      | Price           |
      | Stock Quantity  |
