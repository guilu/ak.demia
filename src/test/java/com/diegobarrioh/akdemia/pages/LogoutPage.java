package com.diegobarrioh.akdemia.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

@Component
public class LogoutPage extends BasePage{

    @FindBy(css = "input[type=submit]")
    private WebElement submit;

    @Override
    public LogoutPage isAt() {
        assertThat(this.driver.getTitle()).isEqualTo("logout");
        return this;
    }

    public LogoutPage confirmLogout(){
        submit.click();
        return this;
    }
}
