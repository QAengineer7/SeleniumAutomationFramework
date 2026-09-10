// package com.automation.pages;

// import com.automation.base.BasePage;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;

// public class DashboardPage extends BasePage {

//     public DashboardPage(WebDriver driver) {
//         super(driver);
//     }

//     // Admin Overview heading
//     private By adminOverview = By.xpath(
//             "//*[normalize-space()='Admin Overview']"
//     );


//     // Verify Dashboard is displayed
//     public boolean isDashboardDisplayed() {

//         return isDisplayed(adminOverview);
//     }


//     // Get current URL
//     public String getDashboardUrl() {

//         return getCurrentUrl();
//     }
// }




package com.automation.pages;

import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private By productionSidebarHeading = By.id("navbarDropdown");

    public boolean isDashboardDisplayed() {
        return isDisplayed(productionSidebarHeading);
    }

    public String getDashboardUrl() {
        return getCurrentUrl();
    }
}