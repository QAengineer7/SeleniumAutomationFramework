// package com.automation.tests;

// import com.automation.base.BaseTest;
// import com.automation.pages.DashboardPage;
// import com.automation.pages.HomePage;
// import com.automation.pages.LoginPage;
// import com.automation.utils.ConfigReader;

// import org.testng.Assert;
// import org.testng.annotations.Test;

// public class LoginTest extends BaseTest {

//     @Test
//     public void validLoginTest() {

//         // Step 1: Open Homepage and click Login
//         HomePage homePage = new HomePage(driver);

//         homePage.clickLogin();


//         // Step 2: Login
//         LoginPage loginPage = new LoginPage(driver);

//         loginPage.login(
//                 ConfigReader.getProperty("username"),
//                 ConfigReader.getProperty("password")
//         );


//         // Step 3: Dashboard Verification
//         DashboardPage dashboardPage = new DashboardPage(driver);

//         Assert.assertTrue(
//                 dashboardPage.isDashboardDisplayed(),
//                 "Login failed! Admin Dashboard was not displayed."
//         );


//         // Step 4: URL Verification
//       Assert.assertTrue(
//         dashboardPage.getDashboardUrl().contains("/admin/dashboard"),
//         "Login failed! Dashboard URL was not opened."
// );


//         System.out.println("Login successful - Admin Dashboard opened.");
//     }
// }











package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import com.automation.utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        // Step 1: Login
        // (KingIT's index.php loads the Login form directly — no separate
        // Home page "click Login" step needed here, unlike the Justo project)
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );


        // TEMP DEBUG — remove once root cause is confirmed
        System.out.println("Current URL after login attempt: " + driver.getCurrentUrl());
        System.out.println("Page contains 'PRODUCTION': " + driver.getPageSource().contains("PRODUCTION"));
        System.out.println("Page contains 'Invalid': " + driver.getPageSource().contains("Invalid"));

        // Step 2: Dashboard Verification
        DashboardPage dashboardPage = new DashboardPage(driver);

        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Login failed! Admin Dashboard was not displayed."
        );


        // Step 3: URL Verification
        Assert.assertTrue(
                dashboardPage.getDashboardUrl().contains("/management/dashboard.php"),
                "Login failed! Dashboard URL was not opened."
        );


        System.out.println("Login successful - KingIT Admin Dashboard opened.");
    }
}