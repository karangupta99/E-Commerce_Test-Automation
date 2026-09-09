# E-commerce Test Automation Framework

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.18.1-green)
![Cucumber](https://img.shields.io/badge/Cucumber-7.15.0-brightgreen)
![TestNG](https://img.shields.io/badge/TestNG-7.9.0-red)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue)

## 📖 Overview

Comprehensive test automation framework for e-commerce platform using Selenium WebDriver, Cucumber BDD, and TestNG. The project follows Page Object Model design pattern and is divided among 4 teams for efficient parallel testing.

**Author:** bugHunter  
**Location:** Indrapuri, Bhopal  
**Email:** bughunter@ecommerce.com

## 🏗️ Project Structure

```
ecommerce-testing/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── commerza/
│       │           ├── hooks/
│       │           │   └── Hooks.java
│       │           ├── pages/
│       │           │   ├── HomePage.java
│       │           │   ├── LoginPage.java
│       │           │   ├── SignupPage.java
│       │           │   ├── AccountPage.java
│       │           │   ├── ForgotPasswordPage.java
│       │           │   ├── ProductsPage.java
│       │           │   ├── ProductDetailsPage.java
│       │           │   ├── WishlistPage.java
│       │           │   ├── ComparePage.java
│       │           │   ├── CartPage.java
│       │           │   ├── CheckoutPage.java
│       │           │   ├── OrderTrackingPage.java
│       │           │   ├── ReturnsPage.java
│       │           │   ├── AdminLoginPage.java
│       │           │   ├── AdminDashboardPage.java
│       │           │   ├── AdminProductManagementPage.java
│       │           │   └── AdminOrderManagementPage.java
│       │           ├── runners/
│       │           │   ├── Team1Runner.java
│       │           │   ├── Team2Runner.java
│       │           │   ├── Team3Runner.java
│       │           │   └── Team4Runner.java
│       │           ├── stepdefinitions/
│       │           │   ├── team1/
│       │           │   │   └── AuthenticationSteps.java
│       │           │   ├── team2/
│       │           │   │   └── ProductBrowsingSteps.java
│       │           │   ├── team3/
│       │           │   │   └── CartSteps.java
│       │           │   └── team4/
│       │           │       └── AdminSteps.java
│       │           └── utils/
│       │               ├── ConfigReader.java
│       │               ├── DriverManager.java
│       │               └── ElementUtils.java
│       └── resources/
│           ├── features/
│           │   ├── team1/
│           │   │   └── Authentication.feature
│           │   ├── team2/
│           │   │   └── ProductBrowsing.feature
│           │   ├── team3/
│           │   │   └── ShoppingCart.feature
│           │   └── team4/
│           │       └── AdminPanel.feature
│           ├── config.properties
│           ├── extent.properties
│           └── extent-config.xml
├── test-output/
│   ├── reports/
│   └── screenshots/
├── pom.xml
├── testng.xml
├── README.md
└── WORK_DIVISION_AND_SETUP_GUIDE.md
```

## 🚀 Quick Start

### Prerequisites
- Java JDK 21
- Maven 3.6+
- Chrome/Firefox Browser
- Git

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ecommerce
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Configure environment**
   - Update `src/test/resources/config.properties` with the environment URLs and credentials you need
   - Default UI target: `https://project-testing-mu.vercel.app/`
   - Default admin target: `https://project-testing-mu.vercel.app/admin/frontend/`

4. **Run a test**
   ```bash
   mvn clean test -Dtest=Team1Runner
   ```

## 🌐 Hosted Frontend Target

- **Customer UI:** `https://project-testing-mu.vercel.app/`
- **Admin UI:** `https://project-testing-mu.vercel.app/admin/frontend/`
- The storefront persists cart, wishlist, and comparison state in `localStorage`/`sessionStorage`, so the automation hooks now reset both stores around every scenario to keep flows deterministic.

## 👥 Functional Streams

| Module | Scenarios | Responsibility | Runner |
|--------|-----------|----------------|--------|
| Authentication & Account Management | 18 | Login, Signup, Profile Management | `Team1Runner` |
| Product Browsing & Search | 27 | Browse, Search, Wishlist, Compare | `Team2Runner` |
| Shopping Cart & Checkout | 32 | Cart, Checkout, Orders, Returns | `Team3Runner` |
| Admin Panel & Order Management | 43 | Product Management, Orders, CMS | `Team4Runner` |
| **Total** | **120** | **Complete E-commerce Testing** | -- |

## 🧪 Running Tests

### Run All Tests
```bash
mvn clean test
```

### Run Module-Specific Tests
```bash
# Authentication & Account Management (Team1Runner)
mvn clean test -Dtest=Team1Runner

# Product Browsing & Search (Team2Runner)
mvn clean test -Dtest=Team2Runner

# Shopping Cart & Checkout (Team3Runner)
mvn clean test -Dtest=Team3Runner

# Admin Panel & Order Management (Team4Runner)
mvn clean test -Dtest=Team4Runner
```

### Run by Tags
```bash
# Smoke tests only
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Regression suite
mvn clean test -Dcucumber.filter.tags="@Regression"

# Negative tests
mvn clean test -Dcucumber.filter.tags="@NegativeTesting"

# Specific feature
mvn clean test -Dcucumber.filter.tags="@Login"
```

### Parallel Execution
```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

## 📊 Test Reports

### Report Locations
- **Main Extent Report:** `test-output/reports/ExtentReport.html`
- **Module Reports:**
   - Authentication & Account Management: `test-output/reports/team1/cucumber-report.html`
   - Product Browsing & Search: `test-output/reports/team2/cucumber-report.html`
   - Shopping Cart & Checkout: `test-output/reports/team3/cucumber-report.html`
   - Admin Panel & Order Management: `test-output/reports/team4/cucumber-report.html`
- **Screenshots:** `test-output/screenshots/`

### View Reports
```bash
# Windows
start test-output/reports/ExtentReport.html

# Linux/Mac
open test-output/reports/ExtentReport.html
```

## 🎯 Test Coverage

### Functional Areas Covered

#### Customer Facing
- ✅ User Registration & Login
- ✅ Profile Management
- ✅ Product Search & Browse
- ✅ Product Filtering & Sorting
- ✅ Wishlist Management
- ✅ Product Comparison
- ✅ Shopping Cart
- ✅ Checkout Process
- ✅ Order Tracking
- ✅ Returns & Refunds

#### Admin Panel
- ✅ Admin Authentication
- ✅ Product Management (CRUD)
- ✅ Order Management
- ✅ Customer Management
- ✅ Category Management
- ✅ Content Management
- ✅ Dashboard & Reports

## 🛠️ Framework Features

### Design Patterns
- **Page Object Model (POM)** - Maintainable page representations
- **Singleton Pattern** - Driver management
- **Factory Pattern** - Browser initialization

### Key Features
- ✅ BDD with Cucumber
- ✅ TestNG for test execution
- ✅ Extent Reports with screenshots
- ✅ Parallel execution support
- ✅ Cross-browser testing
- ✅ Headless mode
- ✅ Configurable waits
- ✅ Screenshot on failure
- ✅ Data-driven testing
- ✅ Tag-based execution

### State Management & Browser Storage
- UI flows rely on `localStorage` and `sessionStorage` for cart, wishlist, and admin state; the `Hooks` layer now clears both stores before and after every scenario so modules run in isolation.
- Storage resets happen alongside cookie cleanup, which keeps regression runs deterministic even when scenarios depend on persisted client context.

## 📋 Test Tags

'''
| Tag | Purpose |
|-----|---------|
| `@Team1` - `@Team4` | Team-specific tests |
| `@Smoke` | Critical smoke tests |
| `@Regression` | Full regression suite |
| `@NegativeTesting` | Negative test scenarios |
| `@Login`, `@Signup`, etc. | Feature-specific |
'''

## 🔧 Configuration

### config.properties
```properties
# URLs
base.url=https://project-testing-mu.vercel.app/
admin.url=https://project-testing-mu.vercel.app/admin/frontend/

# Browser
browser=chrome
headless=false

# Timeouts
implicit.wait=10
explicit.wait=20

# Credentials
test.user.email=testuser@ecommerce.com
test.user.password=Test@123
admin.email=admin@ecommerce.com
admin.password=Admin@123
```

## 🧩 Dependencies

```xml
<!-- Main Dependencies -->
- Selenium WebDriver 4.18.1
- Cucumber Java 7.15.0
- Cucumber TestNG 7.15.0
- TestNG 7.9.0
- WebDriverManager 5.7.0
- ExtentReports Cucumber7 Adapter 1.14.0
- Apache POI 5.2.5
- Log4j 2.23.0
```

## 📈 Statistics

- **Total Test Scenarios:** 120
- **Smoke Tests:** 20
- **Regression Tests:** 90
- **Negative Tests:** 20
- **Page Objects:** 18
- **Step Definition Files:** 4
- **Feature Files:** 4

## 🐛 Troubleshooting

**Browser not launching?**
```bash
mvn clean install -U
```

**Element not found?**
- Increase wait time in `config.properties`
- Check locators in page objects

**Tests failing in headless?**
- Set `headless=false` in config

**Port in use?**
```bash
taskkill /F /IM chrome.exe /T
taskkill /F /IM chromedriver.exe /T
```

## 📚 Documentation

- **[Work Division & Setup Guide](WORK_DIVISION_AND_SETUP_GUIDE.md)** - Complete team division guide
- **Feature Files** - BDD scenarios in Gherkin
- **Page Objects** - Element locators and methods
- **Step Definitions** - Test implementation

## 🤝 Contributing

### Code Standards
1. Follow Page Object Model
2. Use meaningful names
3. Add proper assertions
4. Keep methods focused
5. Handle exceptions
6. Update documentation

### Git Workflow
```bash
# Create feature branch
git checkout -b feature/auth-login-tests

# Commit changes
git add .
git commit -m "feat: Add login test scenarios"

# Push and create PR
git push origin feature/auth-login-tests
```

## 📞 Support

For issues or questions:
1. Check documentation
2. Review similar implementations
3. Contact QA Lead
4. Refer to official docs

## 📄 License

This project is part of E-commerce testing initiative.

## 👨‍💻 Author

**Primary Author:** bugHunter  
**Email:** bughunter@ecommerce.com  
**Location:** Indrapuri, Bhopal  

**Project Team:**
- Authentication & Account Management
- Product Browsing & Search
- Shopping Cart & Checkout
- Admin Panel Management

---

## 🎯 Get Started Now!

```bash
# 1. Install dependencies
mvn clean install

# 2. Run a quick test
mvn clean test -Dtest=Team1Runner -Dcucumber.filter.tags="@Smoke"

# 3. View report
start test-output/reports/ExtentReport.html
```

---

**Made with ❤️ using Selenium, Cucumber & TestNG**
