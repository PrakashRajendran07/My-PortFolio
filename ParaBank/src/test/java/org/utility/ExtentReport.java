package org.utility;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport implements ITestListener{
	public ExtentSparkReporter sparkReporter; 
	public ExtentReports extent;
	public ExtentTest test;
//	public static ExtentReports extent;
	String repName;
	String screenShotFile;
	@Override
	public void onStart(ITestContext context) {
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report-" + timeStamp + ".html";
		
		//sparkReporter= new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/"+repName);
		
		
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Environment", "Local");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("UserName", System.getProperty("use.name"));
		extent.setSystemInfo("OS", "MacOs");
		
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
		
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		 test = extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());

		    test.log(Status.FAIL, result.getName() + " got failed");
		    test.log(Status.INFO, result.getThrowable().toString());

		    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		    screenShotFile = "ScreenShot-" + timeStamp + ".png";

		    try {
		        WebDriver driver = null;

		        Object testInstance = result.getInstance();
		        if (testInstance instanceof BaseClass) {
		            driver = ((BaseClass) testInstance).driver;
		        }

		        if (driver == null) {
		            driver = BaseClass.driver;
		        }

		        if (driver != null) {
		            TakesScreenshot ts = (TakesScreenshot) driver;
		            File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		            File dest = new File(System.getProperty("user.dir") + "/screenshots/" + screenShotFile);
		            FileUtils.copyFile(sourceFile, dest);

		            test.addScreenCaptureFromPath(dest.getAbsolutePath());
		        } else {
		            test.log(Status.WARNING, "WebDriver is null. Screenshot not captured.");
		            System.out.println("WebDriver is null. Skipping screenshot.");
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        test.log(Status.WARNING, "Exception while capturing screenshot: " + e.getMessage());
		    }}
	@Override
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Case SKIPPED is: " + result.getName());
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
		File ExtentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(ExtentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	



}
