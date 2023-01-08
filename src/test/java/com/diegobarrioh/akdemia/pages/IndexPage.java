package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class IndexPage extends BasePage {

    @Autowired
    private LoginPage loginPage;

    @FindBy(id = "login")
    private WebElement lnkLogin;

    @FindBy(id = "logout")
    private WebElement lnkLogout;

    public IndexPage goToIndexPage(String baseUrl) {
        System.out.println("BASE_URL:"+baseUrl);
        driver.get(baseUrl);
        return this;
    }

    public IndexPage goToLoginPage() {
        lnkLogin.click();
        return this;
    }

    public IndexPage iAmLogged() {
        assertThat(lnkLogout.getText()).isEqualTo("Log Out");
        return this;
    }

    @Override
    public void isAt() {
       assertThat(this.driver.getTitle()).isEqualTo("Akdemia dbhstduios");
    }

}
