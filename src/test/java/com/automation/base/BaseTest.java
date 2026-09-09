// package com.automation.base;

// import com.automation.utils.ConfigReader;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.testng.annotations.AfterMethod;
// import org.testng.annotations.BeforeMethod;

// public class BaseTest {

//     protected WebDriver driver;

//     @BeforeMethod
//     public void setUp() {

//         driver = new ChromeDriver();

//         driver.manage().window().maximize();

//         driver.get(ConfigReader.getProperty("url"));

//         System.out.println("Justo application opened successfully");
//     }

//     @AfterMethod
//     public void tearDown() {

//         if (driver != null) {
//             driver.quit();
//         }

//         System.out.println("Browser closed successfully");
//     }
// }



package com.automation.base;

import com.automation.listeners.TestListener;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--disable-blink-features=AutomationControlled");

        driver = new ChromeDriver(options);

        // Explicit fixed size as a fallback — on CI machines without a real
        // interactive desktop session, maximize()/--start-maximized can
        // silently fail and leave the browser at a small default size,
        // which can collapse the responsive sidebar and hide elements
        // like the "PRODUCTION" menu.
        driver.manage().window().setSize(new Dimension(1920, 1080));

        driver.get(ConfigReader.getProperty("url"));

        System.out.println("KingIT application opened successfully");
    }

    // NOTE: driver.quit() is intentionally NOT done here anymore.
    // TestNG fires ITestListener.onTestSuccess/onTestFailure AFTER
    // @AfterMethod runs — so quitting the browser in an @AfterMethod would
    // kill the session before TestListener gets a chance to capture a
    // failure screenshot (this caused "invalid session id" errors).
    // TestListener now owns quitting the driver, right after it finishes
    // logging the result / capturing the screenshot for that test.
}