package com.diegobarrioh.akdemia.pages;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;


public abstract class BasePage {

    @Autowired
    public WebDriver driver;

    @Value("${spring.application.url}")
    protected String baseUrl;

    @PostConstruct
    public void initDriver() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void navigate(String url) {
        this.driver.navigate().to(url);
    }

    public void loadPage() {
        getDriver().get(getUrl());
        verifyPageLoaded();
    }

    public String getUrl() {
        return baseUrl.concat(getPath());
    }

    // example "/login"
    protected abstract String getPath();

    public abstract void verifyPageLoaded();

    public WebDriver getDriver() {
        return driver;
    }

    protected void  waitTillElementIsReady(WebElement webElement, Duration seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        System.out.println("esperando a ver "+webElement.getTagName());
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void sendKeysWhenReady(WebElement webElement, String keys) {
        waitTillElementIsReady(webElement, Duration.ofSeconds(5));
        webElement.sendKeys(keys);
    }

    protected void clickWhenReady(WebElement webElement) {
        waitTillElementIsReady(webElement, Duration.ofSeconds(5));
        webElement.click();
    }

}
