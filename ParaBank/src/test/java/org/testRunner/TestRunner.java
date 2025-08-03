package org.testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = {"src/test/resources/FeatureFiles/loanRequest.feature"},
    glue = {"org.stepdefinitions"},
    dryRun = false,
    monochrome = true,
  //  tags = "(@verifyAccountDetails)",
    plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}