package com.commerza.pages;

import com.commerza.utils.ElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminProductManagementPage {
	private static final Logger logger = LogManager.getLogger(AdminProductManagementPage.class);
	private WebDriver driver;
	private ElementUtils elementUtils;

	private By addProductButton = By.id("addNewProductBtn");

	private By productNameField = By.id("productName");
	private By productSection = By.cssSelector("#productSectionMenu li a");
	private By productPriceField = By.id("productPrice");
	private By productSalePriceField = By.id("productSalePrice");
	private By stockQuantityField = By.id("productStock");
	private By movementType = By.xpath("//button[@id='productMovementBtn']/../ul/li");
	private By productImageUpload = By.id("productImage");
	private By productDescriptionField = By.id("productDescription");
	private By saveProductButton = By.id("saveProductBtn");

	private By productCount = By.id("productCount");
	private By productEditButtons = By.cssSelector("#productsTable tbody tr button[title='Edit']");
	private By productDeleteButtons = By.cssSelector("#productsTable tbody tr button[title='Delete']");

	private By helperActionButtons = By.cssSelector("div.helper-actions button");


	public AdminProductManagementPage(WebDriver driver) {
		this.driver = driver;
		this.elementUtils = new ElementUtils(driver);
	}

	public List<WebElement> getProductDeleteButtons() {
		return driver.findElements(productDeleteButtons);
	}

	public List<WebElement> getProductEditButtons() {
		return driver.findElements(productEditButtons);
	}

	public void navigateToProductMangementPage() {
		logger.info("finding product management page button");
		new Actions(driver).moveToElement(driver.findElements(helperActionButtons).get(0)).click().perform();
		logger.info("navigation Button clicked");
	}

	public int getProductCount() {
		try {
			String text = elementUtils.getText(productCount);
			if (text == null) {
				logger.warn("No Products found");
				return 0;
			}
			return Integer.parseInt(text.replaceAll("[^0-9]", ""));
		} catch (NumberFormatException e) {
			logger.warn("No Products found");
			return 0;
		}
	}

	public void setProductSection(int section) {
		elementUtils.click(By.id("productSectionBtn"));
		WebElement elem = driver.findElements(productSection).get(section);
		new Actions(driver).moveToElement(elem).click().perform();
	}

	public void clickAddProduct() {
		elementUtils.click(addProductButton);
	}

	public void enterMovementType(String val) {
		int indx;
		switch(val) {
			case "Quartz":
				indx = 0;
				break;
			default:
				indx = 1;
		}
		elementUtils.click(By.id("productMovementBtn"));
		new Actions(driver).moveToElement(driver.findElements(movementType).get(indx)).click().perform();
	}

	public void enterProductName(String name) {
		elementUtils.sendKeys(productNameField, name);
	}

	public void enterPrice(String price) {
		elementUtils.sendKeys(productPriceField, price);
	}

	public void enterSalePrice(String price) {
		elementUtils.sendKeys(productSalePriceField, price);
	}

	public void selectCategory(String category) {
		driver.findElement(By.xpath("//select[@id='product-category']/option[contains(text(),'" + category + "')]")).click();
	}

	public void enterDescription(String description) {
		elementUtils.sendKeys(productDescriptionField, description);
	}

	public void enterStockQuantity(String quantity) {
		elementUtils.sendKeys(stockQuantityField, quantity);
	}

	public void uploadImage(String imagePath) {
		driver.findElement(productImageUpload).sendKeys(imagePath);
	}

	public void clickSaveProduct() {
		elementUtils.click(saveProductButton);
	}

	public void clickUpdateProduct() {
		logger.debug("Implement function");
	}

	public boolean isSuccessMessageDisplayed() {
		logger.debug("Implement function");
		return false;
	}

	public String getSuccessMessage() {
		logger.debug("Implement function");
		return " ";
	}

	public void clickEditProduct(int index) {
		logger.debug("Implement function");
	}

	public void clickDeleteProduct(int index) {
		logger.debug("Implement function");
	}

	public void confirmDeletion() {
		logger.debug("Implement function");
	}

	public boolean isValidationErrorDisplayed() {
		logger.debug("Implement function");
		return false;
	}

	public void markAsFeatured() {
		logger.debug("Implement function");
	}

	public void clickSaveChanges() {
		logger.debug("Implement function");
	}
}
