package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(css = "input[type=submit]")
    private WebElement submit;

    @FindBy(id = "error")
    private WebElement error;

    public LoginPage login(String username, String password) {
        System.out.println("Sending keys " + username + " and " + password);
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.submit.click();
        System.out.println("clicked");
        return this;
    }


    public LoginPage verifyPasswordErrorMessage(String expectedText) {
        assertThat(error.getText()).isEqualTo(expectedText);
        return this;
    }

    @Override
    public void isAt() {
        assertThat(this.driver.getTitle()).isEqualTo("Log In");
    }
}
