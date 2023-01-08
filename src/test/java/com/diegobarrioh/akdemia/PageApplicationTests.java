package com.diegobarrioh.akdemia;

import com.diegobarrioh.akdemia.pages.IndexPage;
import com.diegobarrioh.akdemia.pages.LoginPage;
import com.diegobarrioh.akdemia.pages.LogoutPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageApplicationTests {

    @Autowired
    private IndexPage indexPage;
    @Autowired
    private LoginPage loginPage;

    @Autowired
    private LogoutPage logoutPage;

    @LocalServerPort
    private int port;

    @Value("${spring.application.url}")
    private String baseUrl;

    @Test
    @DirtiesContext
    void navigateToIndex() {
        indexPage.goToIndexPage(url());
     }

    @Test
    @DirtiesContext
    void goToLoginPage() {
        indexPage.goToIndexPage(url())
                .goToLoginPage();
    }

    @Test
    @DirtiesContext
    void performLogin() {
        indexPage.goToIndexPage(url()).goToLoginPage();
        loginPage.login("user","password");
        indexPage.iAmLogged();
    }

    @Test
    @DirtiesContext
    void errorPerformingLogin() {
        indexPage.goToIndexPage(url()).goToLoginPage();
        loginPage
                .login("usuario","password")
                .verifyPasswordErrorMessage("Invalid username and password.");
    }

    @Test
    @DirtiesContext
    void performLogout() {
        indexPage.goToIndexPage(url()).goToLoginPage();
        loginPage.login("user","password");
        indexPage.gotToLogoutPage();
        logoutPage.confirmLogout();
        indexPage.iJustLoggedOut();
    }

    private String url(){
        return this.baseUrl+":"+this.port;
    }

}

