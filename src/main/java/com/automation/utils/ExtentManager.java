package com.automation.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {

        if (extent == null) {

            String reportPath = System.getProperty("user.dir")
                    + "/test-output/ExtentReport.html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("QA Automation Report");
            sparkReporter.config().setReportName("KingIT - Automation Test Results");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "KingIT");
            extent.setSystemInfo("Environment", "Staging");
            extent.setSystemInfo("Executed By", "QA Automation");
        }

        return extent;
    }
}
