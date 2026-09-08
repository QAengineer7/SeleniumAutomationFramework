// package com.automation.pages;

// import com.automation.base.BasePage;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;

// public class HomePage extends BasePage {

//     public HomePage(WebDriver driver) {
//         super(driver);
//     }

//     private By loginLink = By.cssSelector(
//             "a[href='https://justo-staging.orientaloutsourcing.in/login']"
//     );

//     public void clickLogin() {

//         click(loginLink);
//     }
// }



package com.automation.pages;

import com.automation.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * NOTE: Not used in the KingIT flow.
 * KingIT's index.php loads the Login form directly, so there is no
 * separate "click Login link from Home page" step like the Justo project had.
 * Kept here in case a future flow needs it.
 */
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By loginLink = By.cssSelector(
            "a[href='https://kits-staging.kingit.com.au/index.php']"
    );

    public void clickLogin() {

        click(loginLink);
    }
}

