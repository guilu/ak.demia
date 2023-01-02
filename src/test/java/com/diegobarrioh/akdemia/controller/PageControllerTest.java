package com.diegobarrioh.akdemia.controller;

import com.diegobarrioh.akdemia.sit.pages.HomePage;
import com.diegobarrioh.akdemia.sit.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageControllerTest {

    private WebDriver driver;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        this.driver = new HtmlUnitDriver();
    }

    @AfterEach
    void tearDown() {
        this.driver.quit();
    }

    @Test
    void login() {
        final LoginPage loginPage = HomePage.to(this.driver, this.port);
        loginPage.assertAt("Please sign in");
    }

    @Test
    void home() {
        final LoginPage loginPage = HomePage.to(this.driver, this.port);
        HomePage homePage = loginPage.loginForm().username("user").password("password").submit();
        homePage.assertAt("Akdemia dbhstudios");
    }
    @Test
    void logout() {

        final LoginPage loginPage = HomePage.to(this.driver, this.port);
        HomePage homePage = loginPage.loginForm().username("user").password("password").submit();
        LoginPage logoutSuccess = homePage.logoutAction();
        logoutSuccess.assertAt("Please sign in");
    }
}