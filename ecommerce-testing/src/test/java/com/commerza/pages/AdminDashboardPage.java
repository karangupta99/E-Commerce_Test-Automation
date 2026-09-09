package com.commerza.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AdminDashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ── ORIGINAL LOCATORS ────────────────────────────────────────────────────
    private By dashboard        = By.id("dashboardSection");
    private By totalOrders      = By.id("totalOrdersValue");
    private By totalRevenue     = By.id("totalRevenueValue");
    private By totalCustomers   = By.id("totalCustomersValue");
    private By pendingOrders    = By.id("pendingOrdersValue");
    private By recentOrdersList = By.cssSelector(".table");
    private By salesChart       = By.id("salesChart");
    private By productsLink     = By.id("products-tab");
    private By ordersLink       = By.id("orders-tab");
    private By customersLink    = By.id("customers-tab");
    private By contentLink      = By.id("website-tab");
    private By categoriesLink   = By.id("dashboard-tab");
    private By reportsLink      = By.id("analytics-tab");
    private By settingsLink     = By.id("homepage-tab");
    private By logoutButton     = By.id("logoutBtn");

    // ── SECTION PANELS ───────────────────────────────────────────────────────
		private By helperActions    = By.cssSelector(".helper-actions button");
    private By productsSection  = By.id("productsSection");
    private By ordersSection    = By.id("ordersSection");
    private By customersSection = By.id("customersSection");
    private By analyticsSection = By.id("analyticsSection");
    private By websiteSection   = By.id("websiteSection");
    private By homepageSection  = By.id("homepageSection");

    // ── DASHBOARD STATS ───────────────────────────────────────────────────────
    private By totalRevenueInfo   = By.id("totalRevenueInfo");
    private By totalOrdersInfo    = By.id("totalOrdersInfo");
    private By totalProductsValue = By.id("totalProductsValue");
    private By totalCustomersInfo = By.id("totalCustomersInfo");
    private By lowStockCount      = By.id("lowStockCount");
    private By recentOrdersRows   = By.cssSelector(".table tbody tr");
    private By pageTitle          = By.id("pageTitle");

    // ── TOPBAR ────────────────────────────────────────────────────────────────
    private By exportBtn          = By.id("exportBtn");
    private By exportAsJsonOption = By.cssSelector("a[onclick*='exportProductsData']");
    private By exportAsCsvOption  = By.cssSelector("a[onclick*='exportProductsAsCSV']");
    private By notificationBell   = By.cssSelector("button[aria-label='Notifications']");
    private By notificationCount  = By.id("notificationCount");

    // ── SECTION MANAGEMENT ────────────────────────────────────────────────────
    private By sectionFilterBtn        = By.id("sectionFilterBtn");
    private By sectionNameInput        = By.id("sectionName");
    private By sectionIdInput          = By.id("sectionId");
    private By sectionPageInput        = By.id("sectionPage");
    private By sectionCategoryInput    = By.id("sectionCategory");
    private By sectionSubcategoryInput = By.id("sectionSubcategory");
    private By saveSectionBtn          = By.id("saveSectionBtn");
    private By resetSectionBtn         = By.id("resetSectionBtn");
    private By sectionsTableRows       = By.cssSelector("#sectionsTable tbody tr");

    // ── PRODUCTS TABLE ────────────────────────────────────────────────────────
    private By productCount      = By.id("productCount");
    private By addNewProductBtn  = By.id("addNewProductBtn");
    private By productsTableRows = By.cssSelector("#productsTable tbody tr");

    // ── PRODUCT MODAL ─────────────────────────────────────────────────────────
    private By productModal            = By.id("productModal");
    private By productNameInput        = By.id("productName");
    private By productSectionBtn       = By.id("productSectionBtn");
    private By productSectionHidden    = By.id("productSection");
    private By productPriceInput       = By.id("productPrice");
    private By productSalePriceInput   = By.id("productSalePrice");
    private By productStockInput       = By.id("productStock");
    private By productMovementBtn      = By.id("productMovementBtn");
    private By productImageInput       = By.id("productImage");
    private By productDescriptionInput = By.id("productDescription");
    private By saveProductBtn          = By.id("saveProductBtn");
    private By cancelProductModalBtn   = By.cssSelector("#productModal .btn-outline-secondary[data-bs-dismiss='modal']");

    // ── ORDERS TABLE ──────────────────────────────────────────────────────────
    private By ordersTable        = By.cssSelector("#ordersSection .table");
    private By ordersTableRows    = By.cssSelector("#ordersSection .table tbody tr");
    private By orderIdCells       = By.cssSelector("#ordersSection tbody td:nth-child(1)");
    private By orderCustomerCells = By.cssSelector("#ordersSection tbody td:nth-child(2)");
    private By orderDateCells     = By.cssSelector("#ordersSection tbody td:nth-child(3)");
    private By orderAmountCells   = By.cssSelector("#ordersSection tbody td:nth-child(4)");
    private By orderPaymentCells  = By.cssSelector("#ordersSection tbody td:nth-child(5)");
    private By orderStatusCells   = By.cssSelector("#ordersSection tbody td:nth-child(6)");
    private By orderDeleteButtons = By.cssSelector("#ordersSection tbody .btn-outline-danger");

    // ── CUSTOMERS TABLE ───────────────────────────────────────────────────────
    private By customersTable      = By.cssSelector("#customersSection .table");
    private By customersTableRows  = By.cssSelector("#customersSection .table tbody tr");
    private By customerNameCells   = By.cssSelector("#customersSection tbody td:nth-child(2)");
    private By customerEmailCells  = By.cssSelector("#customersSection tbody td:nth-child(3)");
    private By customerPhoneCells  = By.cssSelector("#customersSection tbody td:nth-child(4)");
    private By customerOrdersCells = By.cssSelector("#customersSection tbody td:nth-child(5)");
    private By customerSpentCells  = By.cssSelector("#customersSection tbody td:nth-child(6)");

    // ── WEBSITE: ADMIN EMAIL ──────────────────────────────────────────────────
    private By securityEmailPasswordInput = By.id("securityEmailPassword");
    private By securityEmailResetKeyInput = By.id("securityEmailResetKey");
    private By securityEmailNewInput      = By.id("securityEmailNew");
    private By securityEmailConfirmInput  = By.id("securityEmailConfirm");
    private By saveAdminEmailBtn          = By.id("saveAdminEmailBtn");

    // ── WEBSITE: ADMIN PASSWORD ───────────────────────────────────────────────
    private By securityPasswordEmailInput   = By.id("securityPasswordEmail");
    private By securityPasswordResetKey     = By.id("securityPasswordResetKey");
    private By securityPasswordNewInput     = By.id("securityPasswordNew");
    private By securityPasswordConfirmInput = By.id("securityPasswordConfirm");
    private By saveAdminPasswordBtn         = By.id("saveAdminPasswordBtn");

    // ── WEBSITE: RESET KEY ────────────────────────────────────────────────────
    private By securityKeyEmailInput    = By.id("securityKeyEmail");
    private By securityKeyPasswordInput = By.id("securityKeyPassword");
    private By securityKeyNewInput      = By.id("securityKeyNew");
    private By securityKeyConfirmInput  = By.id("securityKeyConfirm");
    private By saveAdminResetKeyBtn     = By.id("saveAdminResetKeyBtn");

    // ── WEBSITE: BRANDING ─────────────────────────────────────────────────────
    private By siteNameInput    = By.id("siteName");
    private By siteLogoInput    = By.id("siteLogo");
    private By siteFaviconInput = By.id("siteFavicon");
    private By saveBrandBtn     = By.id("saveBrandBtn");

    // ── WEBSITE: CONTACT ──────────────────────────────────────────────────────
    private By siteAddressInput = By.id("siteAddress");
    private By siteEmailInput   = By.id("siteEmail");
    private By sitePhoneInput   = By.id("sitePhone");
    private By saveContactBtn   = By.id("saveContactBtn");

    // ── WEBSITE: SOCIAL LINKS ─────────────────────────────────────────────────
    private By socialLabelInput     = By.id("socialLabel");
    private By socialUrlInput       = By.id("socialUrl");
    private By socialIconInput      = By.id("socialIcon");
    private By saveSocialBtn        = By.id("saveSocialBtn");
    private By resetSocialBtn       = By.id("resetSocialBtn");
    private By socialLinksTableRows = By.cssSelector("#socialLinksTable tbody tr");

    // ── HOMEPAGE: TICKER ──────────────────────────────────────────────────────
    private By tickerEnabledToggle = By.id("tickerEnabled");
    private By tickerMessagesInput = By.id("tickerMessages");
    private By saveTickerBtn       = By.id("saveTickerBtn");
    private By resetTickerBtn      = By.id("resetTickerBtn");

    // ── HOMEPAGE: SLIDER ──────────────────────────────────────────────────────
    private By sliderImageInput      = By.id("sliderImage");
    private By sliderAltInput        = By.id("sliderAlt");
    private By sliderLabelInput      = By.id("sliderLabel");
    private By sliderHeadingInput    = By.id("sliderHeading");
    private By sliderTextInput       = By.id("sliderText");
    private By sliderButtonTextInput = By.id("sliderButtonText");
    private By sliderButtonLinkInput = By.id("sliderButtonLink");
    private By saveSliderBtn         = By.id("saveSliderBtn");
    private By resetSliderBtn        = By.id("resetSliderBtn");
    private By sliderTableRows       = By.cssSelector("#sliderTable tbody tr");

    // =========================================================================
    // CONSTRUCTOR
    // =========================================================================

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // =========================================================================
    // PRIVATE RAW-WEBDRIVER HELPERS
    // =========================================================================

    private WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void click(By locator) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        new Actions(driver).moveToElement(el).click().perform();
    }

    private void type(By locator, String text) {
        WebElement el = find(locator);
        el.clear();
        el.sendKeys(text);
    }

    private String getText(By locator) {
        return find(locator).getText();
    }

    private boolean isDisplayed(By locator) {
        try { return driver.findElement(locator).isDisplayed(); }
        catch (NoSuchElementException e) { return false; }
    }

    private List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    // =========================================================================
    // ORIGINAL METHODS
    // =========================================================================

    public boolean isDashboardDisplayed()        { return isDisplayed(dashboard); }
    public boolean isTotalOrdersDisplayed()      { return isDisplayed(totalOrders); }
    public String  getTotalOrders()              { return getText(totalOrders); }
    public boolean isTotalRevenueDisplayed()     { return isDisplayed(totalRevenue); }
    public String  getTotalRevenue()             { return getText(totalRevenue); }
    public boolean isTotalCustomersDisplayed()   { return isDisplayed(totalCustomers); }
    public boolean isPendingOrdersDisplayed()    { return isDisplayed(pendingOrders); }
    public boolean isRecentOrdersListDisplayed() { return isDisplayed(recentOrdersList); }
    public boolean isSalesChartDisplayed()       { return isDisplayed(salesChart); }
    public void    clickProductsLink()           { click(productsLink); }
    public void    clickOrdersLink()             { click(ordersLink); }
    public void    clickCustomersLink()          { click(customersLink); }
    public void    clickContentLink()            { click(contentLink); }
    public void    clickCategoriesLink()         { click(categoriesLink); }
    public void    clickReportsLink()            { click(reportsLink); }
    public void    clickSettingsLink()           { click(settingsLink); }
    public void    clickLogout()                 { click(logoutButton); }
    public String  getCurrentUrl()               { return driver.getCurrentUrl(); }
		public List<WebElement> getHelperActions()   { return findAll(helperActions); }

    // =========================================================================
    // SECTION VISIBILITY
    // =========================================================================

    public boolean isProductsSectionDisplayed()  { return isDisplayed(productsSection); }
    public boolean isOrdersSectionDisplayed()    { return isDisplayed(ordersSection); }
    public boolean isCustomersSectionDisplayed() { return isDisplayed(customersSection); }
    public boolean isAnalyticsSectionDisplayed() { return isDisplayed(analyticsSection); }
    public boolean isWebsiteSectionDisplayed()   { return isDisplayed(websiteSection); }
    public boolean isHomepageSectionDisplayed()  { return isDisplayed(homepageSection); }

    // =========================================================================
    // DASHBOARD STATS
    // =========================================================================

    public boolean isTotalProductsDisplayed()  { return isDisplayed(totalProductsValue); }
    public String  getTotalProducts()          { return getText(totalProductsValue); }
    public String  getTotalCustomers()         { return getText(totalCustomers); }
    public String  getLowStockCount()          { return getText(lowStockCount); }
    public List<WebElement> getRecentOrdersRows() { return findAll(recentOrdersRows); }

    // =========================================================================
    // TOPBAR: EXPORT
    // =========================================================================

    public void clickExportButton() { click(exportBtn); }
    public void clickExportAsCsv()  { click(exportBtn); click(exportAsCsvOption); }
    public void clickExportAsJson() { click(exportBtn); click(exportAsJsonOption); }

    // =========================================================================
    // NOTIFICATIONS
    // =========================================================================

    public void clickNotificationBell() { click(notificationBell); }
    public boolean isNotificationCountVisible() {
        return !driver.findElement(notificationCount).getAttribute("class").contains("d-none");
    }
    public String getNotificationCount() { return getText(notificationCount); }

    // =========================================================================
    // SECTION MANAGEMENT
    // =========================================================================

    public void selectSectionFilter(String name) {
        click(sectionFilterBtn);
        for (WebElement i : findAll(By.cssSelector("#sectionFilterMenu .dropdown-item")))
            if (i.getText().equalsIgnoreCase(name)) { i.click(); return; }
    }

    public void enterSectionName(String v)       { type(sectionNameInput, v); }
    public void enterSectionId(String v)         { type(sectionIdInput, v); }
    public void enterSectionPage(String v)       { type(sectionPageInput, v); }
    public void enterSectionCategory(String v)   { type(sectionCategoryInput, v); }
    public void enterSectionSubcategory(String v){ type(sectionSubcategoryInput, v); }
    public void clickSaveSectionButton()         { click(saveSectionBtn); }
    public void clickResetSectionButton()        { click(resetSectionBtn); }
    public List<WebElement> getSectionsTableRows(){ return findAll(sectionsTableRows); }

    // =========================================================================
    // PRODUCTS TABLE
    // =========================================================================

    public String  getProductCount()              { return getText(productCount); }
    public void    clickAddNewProductButton()      { click(addNewProductBtn); }
    public List<WebElement> getProductsTableRows(){ return findAll(productsTableRows); }

    public void clickEditProductAtRow(int row) {
        WebElement elem = findAll(productsTableRows).get(row - 1);
        WebElement celem = elem.findElement(By.cssSelector(".btn-outline-warning, [onclick*='editProduct']"));
				new Actions(driver).moveToElement(celem).click().perform();
    }

    public void clickDeleteProductAtRow(int row) {
        WebElement elem = findAll(productsTableRows).get(row - 1);
        WebElement celem = elem.findElement(By.cssSelector(".btn-outline-danger, [onclick*='deleteProduct']"));
				new Actions(driver).moveToElement(celem).click().perform();
    }

    // =========================================================================
    // PRODUCT MODAL
    // =========================================================================

    public boolean isProductModalDisplayed() { return isDisplayed(productModal); }
    public void waitForProductModalVisible() {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.cssSelector(".modal.show")   // adjust to your modal locator
				));
    }

    public void enterProductName(String v)        { type(productNameInput, v); }
    public void enterProductPrice(String v)       { type(productPriceInput, v); }
    public void enterProductSalePrice(String v)   { type(productSalePriceInput, v); }
    public void enterProductStock(String v)       { type(productStockInput, v); }
    public void enterProductImagePath(String v)   { type(productImageInput, v); }
    public void enterProductDescription(String v) { type(productDescriptionInput, v); }
    public void clickSaveProductButton()          { click(saveProductBtn); }
    public void clickCancelProductModal()         { click(cancelProductModalBtn); }

    public void selectProductSection(String name) {
        click(productSectionBtn);
        for (WebElement i : findAll(By.cssSelector("#productSectionMenu .dropdown-item")))
            if (i.getText().equalsIgnoreCase(name)) { i.click(); return; }
    }

    public void selectMovementType(String label) {
        click(productMovementBtn);
        for (WebElement i : findAll(By.cssSelector("#productMovementBtn ~ ul .dropdown-item")))
            if (i.getText().equalsIgnoreCase(label)) { i.click(); return; }
    }

    public void clearProductField(String fieldName) {
        switch (fieldName.toLowerCase().trim()) {
            case "product name":   type(productNameInput,    ""); break;
            case "price":          type(productPriceInput,   ""); break;
            case "section":        type(productSectionHidden,""); break;
            case "stock quantity": type(productStockInput,   ""); break;
            default: throw new IllegalArgumentException("Unknown product field: " + fieldName);
        }
    }

    // =========================================================================
    // ORDERS TABLE
    // =========================================================================

    public boolean isOrdersTableDisplayed()           { return isDisplayed(ordersTable); }
    public List<WebElement> getOrdersTableRows()      { return findAll(ordersTableRows); }
    public List<WebElement> getOrderIdCells()         { return findAll(orderIdCells); }
    public List<WebElement> getOrderCustomerCells()   { return findAll(orderCustomerCells); }
    public List<WebElement> getOrderDateCells()       { return findAll(orderDateCells); }
    public List<WebElement> getOrderAmountCells()     { return findAll(orderAmountCells); }
    public List<WebElement> getOrderStatusCells()     { return findAll(orderStatusCells); }
    public List<WebElement> getOrderPaymentCells()    { return findAll(orderPaymentCells); }

    public void clickOrderById(String orderId) {
        for (WebElement row : findAll(ordersTableRows))
            if (row.findElement(By.cssSelector("td:first-child")).getText().contains(orderId)) {
                row.click(); return;
            }
        throw new RuntimeException("Order not found: " + orderId);
    }

    public void clickDeleteOrderAtRow(int row) {
        findAll(orderDeleteButtons).get(row - 1).click();
    }

    // =========================================================================
    // CUSTOMERS TABLE
    // =========================================================================

    public boolean isCustomersTableDisplayed()        { return isDisplayed(customersTable); }
    public List<WebElement> getCustomersTableRows()   { return findAll(customersTableRows); }
    public List<WebElement> getCustomerNameCells()    { return findAll(customerNameCells); }
    public List<WebElement> getCustomerEmailCells()   { return findAll(customerEmailCells); }
    public List<WebElement> getCustomerPhoneCells()   { return findAll(customerPhoneCells); }
    public List<WebElement> getCustomerOrdersCells()  { return findAll(customerOrdersCells); }
    public List<WebElement> getCustomerSpentCells()   { return findAll(customerSpentCells); }

    // =========================================================================
    // WEBSITE: ADMIN EMAIL
    // =========================================================================

    public void enterCurrentPasswordForEmail(String v) { type(securityEmailPasswordInput, v); }
    public void enterResetKeyForEmail(String v)        { type(securityEmailResetKeyInput, v); }
    public void enterNewAdminEmail(String v)           { type(securityEmailNewInput, v); }
    public void enterConfirmAdminEmail(String v)       { type(securityEmailConfirmInput, v); }
    public void clickUpdateEmailButton()               { click(saveAdminEmailBtn); }

    // =========================================================================
    // WEBSITE: ADMIN PASSWORD
    // =========================================================================

    public void enterCurrentEmailForPassword(String v) { type(securityPasswordEmailInput, v); }
    public void enterResetKeyForPassword(String v)     { type(securityPasswordResetKey, v); }
    public void enterNewAdminPassword(String v)        { type(securityPasswordNewInput, v); }
    public void enterConfirmAdminPassword(String v)    { type(securityPasswordConfirmInput, v); }
    public void clickUpdatePasswordButton()            { click(saveAdminPasswordBtn); }

    // =========================================================================
    // WEBSITE: RESET KEY
    // =========================================================================

    public void enterEmailForResetKey(String v)           { type(securityKeyEmailInput, v); }
    public void enterCurrentPasswordForResetKey(String v) { type(securityKeyPasswordInput, v); }
    public void enterNewResetKey(String v)                { type(securityKeyNewInput, v); }
    public void enterConfirmResetKey(String v)            { type(securityKeyConfirmInput, v); }
    public void clickUpdateResetKeyButton()               { click(saveAdminResetKeyBtn); }

    // =========================================================================
    // WEBSITE: BRANDING
    // =========================================================================

    public void enterSiteName(String v)        { type(siteNameInput, v); }
    public void enterSiteLogoPath(String v)    { type(siteLogoInput, v); }
    public void enterSiteFaviconPath(String v) { type(siteFaviconInput, v); }
    public void clickSaveBrandingButton()      { click(saveBrandBtn); }

    // =========================================================================
    // WEBSITE: CONTACT
    // =========================================================================

    public void enterCompanyAddress(String v) { type(siteAddressInput, v); }
    public void enterCompanyEmail(String v)   { type(siteEmailInput, v); }
    public void enterCompanyPhone(String v)   { type(sitePhoneInput, v); }
    public void clickSaveContactButton()      { click(saveContactBtn); }

    // =========================================================================
    // WEBSITE: SOCIAL LINKS
    // =========================================================================

    public void enterSocialLabel(String v)            { type(socialLabelInput, v); }
    public void enterSocialUrl(String v)              { type(socialUrlInput, v); }
    public void enterSocialIcon(String v)             { type(socialIconInput, v); }
    public void clickSaveSocialButton()               { click(saveSocialBtn); }
    public void clickResetSocialButton()              { click(resetSocialBtn); }
    public List<WebElement> getSocialLinksTableRows() { return findAll(socialLinksTableRows); }

    // =========================================================================
    // HOMEPAGE: TICKER
    // =========================================================================

    public boolean isTickerEnabled()             { return driver.findElement(tickerEnabledToggle).isSelected(); }
    public void    toggleTicker()                { click(tickerEnabledToggle); }
    public void    enterTickerMessages(String v) { type(tickerMessagesInput, v); }
    public void    clickSaveTickerButton()       { click(saveTickerBtn); }
    public void    clickResetTickerButton()      { click(resetTickerBtn); }

    // =========================================================================
    // HOMEPAGE: SLIDER
    // =========================================================================

    public void enterSliderImagePath(String v)   { type(sliderImageInput, v); }
    public void enterSliderAltText(String v)     { type(sliderAltInput, v); }
    public void enterSliderLabel(String v)       { type(sliderLabelInput, v); }
    public void enterSliderHeading(String v)     { type(sliderHeadingInput, v); }
    public void enterSliderDescription(String v) { type(sliderTextInput, v); }
    public void enterSliderButtonText(String v)  { type(sliderButtonTextInput, v); }
    public void enterSliderButtonLink(String v)  { type(sliderButtonLinkInput, v); }
    public void clickSaveSliderButton()          { click(saveSliderBtn); }
    public void clickResetSliderButton()         { click(resetSliderBtn); }
    public List<WebElement> getSliderTableRows() { return findAll(sliderTableRows); }

    // =========================================================================
    // UTILITY
    // =========================================================================

    public boolean isOnDashboardPage() {
        return getCurrentUrl().contains("admin") && isDashboardDisplayed();
    }
}
