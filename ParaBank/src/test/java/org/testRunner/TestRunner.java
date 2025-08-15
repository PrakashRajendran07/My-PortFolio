package org.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/resources/FeatureFiles/register.feature"},
    glue = {"org.stepdefinitions", "org.utility"},
    dryRun = false,
    monochrome = true,
   // tags = "(@Negative)",
    plugin = {"pretty", "html:target/cucumber-reports.html",
    		"json:target/cucumber.json",
    		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}