package org.stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.utility.ExtentReportManager;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseClass {

    private static ThreadLocal<ExtentTest> scenarioTest = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {
        ExtentTest test = ExtentReportManager.getReporter().createTest(scenario.getName());
        scenarioTest.set(test);
    }

    @After
    public void afterScenario(Scenario scenario) {
        ExtentTest test = scenarioTest.get();

        if (scenario.isFailed()) {
            test.log(Status.FAIL, "Scenario failed: " + scenario.getName());
            test.log(Status.FAIL, scenario.getStatus().toString());

            // Capture screenshot
            try {
                String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
                String screenShotFile = "Screenshot-" + timeStamp + ".png";
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile = new File(System.getProperty("user.dir") + "/screenshots/" + screenShotFile);
                FileUtils.copyFile(srcFile, destFile);
                test.addScreenCaptureFromPath(destFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                test.log(Status.WARNING, "Could not capture screenshot.");
            }
        } else {
            test.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }

        // Flush after every scenario
        ExtentReportManager.getReporter().flush();
    }
}
