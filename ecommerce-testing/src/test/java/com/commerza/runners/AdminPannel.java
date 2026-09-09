package com.commerza.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/AdminPannel",
        glue = {
                "com.commerza.stepdefinitions.AdminPannel",
                "com.commerza.hooks"
        },
        tags = "@AdminTest",
        plugin = {
                "pretty",
                "html:test-output/reports/ProductBrowsing/cucumber-report.html",
                "json:test-output/reports/ProductBrowsing/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class AdminPannel extends AbstractTestNGCucumberTests {
}
