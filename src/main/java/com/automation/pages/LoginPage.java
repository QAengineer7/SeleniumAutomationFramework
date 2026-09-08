// package com.automation.pages;

// import com.automation.base.BasePage;

// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;

// public class LoginPage extends BasePage {

//     public LoginPage(WebDriver driver) {
//         super(driver);
//     }

//     // Locators
//     private By emailField = By.id("email");

//     private By passwordField = By.id("password");

//     private By loginButton = By.cssSelector("button[type='submit']");


//     // Enter Email
//     public void enterEmail(String email) {

//         type(emailField, email);
//     }


//     // Enter Password
//     public void enterPassword(String password) {

//         type(passwordField, password);
//     }


//     // Click Login Button
//     public void clickLoginButton() {

//         click(loginButton);
//     }


//     // Complete Login Action
//     public void login(String email, String password) {

//         enterEmail(email);
//         enterPassword(password);
//         clickLoginButton();
//     }
// }






package com.automation.pages;

import com.automation.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators (based on actual DOM from kits-staging.kingit.com.au/index.php)
    private By emailField = By.cssSelector("input.login-email[name='email']");

    private By passwordField = By.cssSelector("input.login-password[name='password']");

    private By loginButton = By.cssSelector("#LoginForm button.btn-theme[type='submit']");


    // Enter Email
    public void enterEmail(String email) {

        type(emailField, email);
    }


    // Enter Password
    public void enterPassword(String password) {

        type(passwordField, password);
    }


    // Click Login Button
    public void clickLoginButton() {

        click(loginButton);
    }


    // Complete Login Action
    public void login(String email, String password) {

        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }
}