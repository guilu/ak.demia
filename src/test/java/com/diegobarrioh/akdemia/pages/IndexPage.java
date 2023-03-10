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

    @FindBy(id = "logout_msg")
    private WebElement msgLogout;

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
        assertThat(lnkLogout.isDisplayed()).isTrue();
        return this;
    }

    public IndexPage iAmNotLogged() {
        assertThat(lnkLogin.isDisplayed()).isTrue();
        return this;
    }

    public IndexPage iJustLoggedOut() {
        assertThat(msgLogout.isDisplayed()).isTrue();
        return this;
    }

    public IndexPage gotToLogoutPage() {
        lnkLogout.click();
        return this;
    }

    @Override
    public IndexPage isAt() {
       assertThat(this.driver.getTitle()).isEqualTo("Akdemia dbhstudios");
       return this;
    }

}
