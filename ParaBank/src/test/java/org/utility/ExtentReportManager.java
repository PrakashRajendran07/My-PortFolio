package org.utility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    public static String reportPath;

    public static ExtentReports getReporter() {
        if (extent == null) {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String repName = "Test-Report-" + timeStamp + ".html";
            reportPath = System.getProperty("user.dir") + "/reports/" + repName;

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Functional Testing");
            sparkReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Environment", "Local");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("OS", "MacOS");
            extent.setSystemInfo("UserName", System.getProperty("user.name"));
        }
        return extent;
    }

    public static void openReport() {
        try {
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
