package com.diegobarrioh.akdemia.pages;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BasePage {

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

    public abstract BasePage isAt();

}
