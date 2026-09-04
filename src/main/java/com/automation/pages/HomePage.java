package com.automation.pages;

import com.automation.base.BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By loginLink = By.cssSelector(
            "a[href='https://justo-staging.orientaloutsourcing.in/login']"
    );

    public void clickLogin() {

        click(loginLink);
    }
}