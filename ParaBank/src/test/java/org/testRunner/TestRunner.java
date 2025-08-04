package org.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/resources/FeatureFiles/fundTransferPage.feature"},
    glue = {"org.stepdefinitions"},
    dryRun = false,
    monochrome = true,
 //   tags = "(@negative2)",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}