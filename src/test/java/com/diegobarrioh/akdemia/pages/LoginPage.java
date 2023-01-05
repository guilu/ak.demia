package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "input[type=submit]")
    private WebElement submit;


    public void login(String username, String password) {
            this.username.sendKeys(username);
            this.password.sendKeys(password);
            this.submit.click();
    }

    @Override
    public void verifyPageLoaded() {
        System.out.println("esperando a ver "+this.submit);
        waitTillElementIsReady(this.username, Duration.ofSeconds(5));
    }

    @Override
    protected String getPath() {
        return "/login";
    }
}