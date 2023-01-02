package com.diegobarrioh.akdemia.sit.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The log out confirmation page.
 *
 * @author Rob Winch
 */
public class LogoutConfirmPage {

    private final WebDriver webDriver;

    private final LogoutConfirmPage.LogoutForm logoutForm;

    public LogoutConfirmPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.logoutForm = PageFactory.initElements(this.webDriver, LogoutForm.class);
    }

    public LoginPage logout() {
        return this.logoutForm.logout();
    }

    public static class LogoutForm {

        private WebDriver webDriver;

        @FindBy(css = "button[type=submit]")
        private WebElement submit;

        public LogoutForm(WebDriver webDriver) {
            this.webDriver = webDriver;
        }

        public LoginPage logout() {
            this.submit.click();
            return PageFactory.initElements(this.webDriver, LoginPage.class);
        }

    }

}