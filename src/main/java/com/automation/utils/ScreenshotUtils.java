// package com.automation.utils;

// import java.io.File;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// import org.apache.commons.io.FileUtils;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.WebDriver;

// public class ScreenshotUtils {

//     // Screenshots live inside test-output/ so they sit next to the Extent
//     // report and can be zipped/archived together as one portable folder.
//     private static final String SCREENSHOT_DIR =
//             System.getProperty("user.dir") + "/test-output/screenshots/";

//     /**
//      * Captures a screenshot and returns the ABSOLUTE path.
//      * Useful for manual/debug calls and console logging.
//      */
//     public static String captureScreenshot(WebDriver driver, String testName) {

//         String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

//         String fileName = testName + "_" + timestamp + ".png";

//         File destinationFile = new File(SCREENSHOT_DIR + fileName);

//         try {

//             File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//             FileUtils.copyFile(sourceFile, destinationFile);

//             System.out.println("Screenshot captured: " + destinationFile.getAbsolutePath());

//         } catch (IOException e) {

//             e.printStackTrace();
//         }

//         return destinationFile.getAbsolutePath();
//     }

//     /**
//      * Captures a screenshot and returns a path RELATIVE to test-output/
//      * (e.g. "screenshots/loginTest_....png"). Use this when embedding into
//      * the ExtentReports HTML report so links still work if the whole
//      * test-output folder is copied/downloaded elsewhere (e.g. from Jenkins).
//      */
//     public static String captureScreenshotForReport(WebDriver driver, String testName) {

//         String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

//         String fileName = testName + "_" + timestamp + ".png";

//         File destinationFile = new File(SCREENSHOT_DIR + fileName);

//         try {

//             File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//             FileUtils.copyFile(sourceFile, destinationFile);

//         } catch (IOException e) {

//             e.printStackTrace();
//         }

//         return "screenshots/" + fileName;
//     }
// }



package com.automation.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR =
            System.getProperty("user.dir") + File.separator
            + "test-output" + File.separator
            + "screenshots" + File.separator;

    /**
     * Captures a screenshot and returns the absolute path.
     */
    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp =
                new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        String fileName = testName + "_" + timestamp + ".png";

        File destinationFile = new File(SCREENSHOT_DIR + fileName);

        try {

            // Create screenshot directory if it does not exist
            File parentDirectory = destinationFile.getParentFile();

            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println(
                    "Screenshot captured: "
                    + destinationFile.getAbsolutePath()
            );

        } catch (IOException e) {

            System.err.println(
                    "Failed to capture screenshot: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return destinationFile.getAbsolutePath();
    }

    /**
     * Captures a screenshot and returns a path relative to test-output.
     * Example:
     * screenshots/loginTest_2026-09-09_16-06-14.png
     */
    public static String captureScreenshotForReport(
            WebDriver driver,
            String testName) {

        String timestamp =
                new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        String fileName = testName + "_" + timestamp + ".png";

        File destinationFile =
                new File(SCREENSHOT_DIR + fileName);

        try {

            // IMPORTANT for Jenkins:
            // Create directory automatically.
            File parentDirectory = destinationFile.getParentFile();

            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            File sourceFile =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println(
                    "Failure screenshot captured: "
                    + destinationFile.getAbsolutePath()
            );

        } catch (IOException e) {

            System.err.println(
                    "Failed to capture failure screenshot: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return "screenshots/" + fileName;
    }
}