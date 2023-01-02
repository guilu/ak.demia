package com.diegobarrioh.akdemia.sit.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The home page.
 *
 * @author Michael Simons
 */
public class HomePage {

    private final WebDriver webDriver;

    @FindBy(id = "logout")
    private WebElement logout;

    public static LoginPage to(WebDriver driver, int port) {
        driver.get("http://localhost:" + port + "/");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HomePage assertAt(String expected) {
        assertThat(this.webDriver.getTitle()).isEqualTo(expected);
        return this;
    }

    public LoginPage logoutAction() {
        this.logout.click();
        //hace el click en el form de logout y vuelve a login... con mensaje de logout success
        return PageFactory.initElements(this.webDriver, LogoutConfirmPage.class).logout();
    }

}