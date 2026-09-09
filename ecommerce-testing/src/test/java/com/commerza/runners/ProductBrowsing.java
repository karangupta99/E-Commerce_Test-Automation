package com.commerza.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ProductBrowsing",
        glue = {
                "com.commerza.stepdefinitions.ProductBrowsing",
                "com.commerza.stepdefinitions.authentication",
                "com.commerza.hooks"
        },
        tags = "@ProductBrowsing",
        plugin = {
                "pretty",
                "html:test-output/reports/ProductBrowsing/cucumber-report.html",
                "json:test-output/reports/ProductBrowsing/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true,
        dryRun = false
)
public class ProductBrowsing extends AbstractTestNGCucumberTests {}

