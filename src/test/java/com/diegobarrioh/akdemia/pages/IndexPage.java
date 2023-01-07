package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IndexPage extends BasePage {

    @Value("${spring.application.url}")
    private String baseUrl;

    @Autowired
    private LoginPage loginPage;

    @FindBy(id = "login")
    private WebElement lnkLogin;

    public IndexPage goToIndexPage() {
        driver.get(baseUrl);
        return this;
    }

    public IndexPage goToLoginPage() {
        lnkLogin.click();
        return this;
    }

}
