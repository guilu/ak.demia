package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IndexPage extends BasePage {

    @Autowired
    private LoginPage loginPage;

    @FindBy(id = "login")
    private WebElement lnkLogin;

    public IndexPage goToIndexPage(String baseUrl) {
        System.out.println("BASE_URL:"+baseUrl);
        driver.get(baseUrl);
        return this;
    }

    public IndexPage goToLoginPage() {
        lnkLogin.click();
        return this;
    }

}
