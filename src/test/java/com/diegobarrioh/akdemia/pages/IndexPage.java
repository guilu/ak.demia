package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class IndexPage extends BasePage {
    @FindBy(id = "blog_link")
    private WebElement lnkLogin;

    public LoginPage ClickLogin() {
        lnkLogin.click();
        System.out.println("Click Login");
        return new LoginPage();
    }

    @Override
    public void verifyPageLoaded() {
        waitTillElementIsReady(lnkLogin, Duration.ofSeconds(5));
    }

    @Override
    protected String getPath() {
        return "/login";
    }
}
