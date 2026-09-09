package com.automation.listeners;

import com.automation.utils.ExtentManager;
import com.automation.utils.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Field;

/**
 * Central reporting + failure-screenshot listener.
 *
 * Registered on BaseTest via @Listeners, so every test class extending
 * BaseTest gets this automatically — no need to add screenshot code inside
 * individual @Test methods.
 */
public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        extent = ExtentManager.getInstance();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test passed");
        quitDriver(getDriverFromTest(result));
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().log(Status.FAIL, "Test failed: " + result.getThrowable());

        WebDriver driver = getDriverFromTest(result);

        if (driver != null) {
            try {
                String screenshotRelativePath = ScreenshotUtils.captureScreenshotForReport(
                        driver, result.getMethod().getMethodName()
                );

                test.get().fail(
                        "Screenshot at failure:",
                        MediaEntityBuilder.createScreenCaptureFromPath(screenshotRelativePath).build()
                );
            } catch (Exception e) {
                test.get().log(Status.WARNING, "Could not attach screenshot: " + e.getMessage());
            }
        } else {
            test.get().log(Status.WARNING, "WebDriver instance not found — screenshot skipped.");
        }

        quitDriver(driver);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        quitDriver(getDriverFromTest(result));
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    /**
     * Quits the browser. Called from here (not BaseTest's @AfterMethod)
     * because TestNG invokes onTestFailure/onTestSuccess AFTER @AfterMethod
     * has already run — quitting earlier would kill the session before a
     * failure screenshot could be captured.
     */
    private void quitDriver(WebDriver driver) {

        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Browser closed successfully");
            } catch (Exception e) {
                // Session may already be gone — safe to ignore here.
            }
        }
    }

    /**
     * Pulls the protected "driver" field off BaseTest via reflection, since
     * the listener has no direct reference to the running test instance.
     */
    private WebDriver getDriverFromTest(ITestResult result) {

        try {
            Object testInstance = result.getInstance();
            Field driverField = testInstance.getClass()
                    .getSuperclass()
                    .getDeclaredField("driver");
            driverField.setAccessible(true);
            return (WebDriver) driverField.get(testInstance);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}