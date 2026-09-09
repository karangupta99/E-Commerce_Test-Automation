package com.commerza.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ShoppingCart",
        glue = {
                "com.commerza.stepdefinitions.ShoppingCart",
                "com.commerza.stepdefinitions.ProductBrowsing",
                "com.commerza.hooks"
        },
        tags = "@ShoppingCart",
        plugin = {
                "pretty",
                "html:test-output/reports/ShoppingCart/cucumber-report.html",
                "json:test-output/reports/ShoppingCart/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class ShoppingCart extends AbstractTestNGCucumberTests {
}
