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

    public static String captureScreenshot(WebDriver driver, String testName) {

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                .format(new Date());

        String screenshotPath = System.getProperty("user.dir")
                + "/screenshots/"
                + testName + "_" + timestamp + ".png";

        File sourceFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File destinationFile = new File(screenshotPath);

        try {

            FileUtils.copyFile(sourceFile, destinationFile);

            System.out.println("Screenshot captured: " + screenshotPath);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return screenshotPath;
    }
}
