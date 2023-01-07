package com.diegobarrioh.akdemia.pages;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;


public class BasePage {

    @Autowired
    protected WebDriver driver;

    @PostConstruct
    public void initDriver() {
        PageFactory.initElements(driver, this);
    }

    public void navigate(String url) {
        this.driver.navigate().to(url);
    }

    public WebDriver getDriver() {
        return driver;
    }

}
