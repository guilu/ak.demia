package com.diegobarrioh.akdemia;

import com.diegobarrioh.akdemia.pages.IndexPage;
import com.diegobarrioh.akdemia.pages.LoginPage;
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

    @LocalServerPort
    private int port;

    @Value("${spring.application.url}")
    private String baseUrl;

    @Test
    @DirtiesContext
    void navigateToIndex() {
        System.out.println("navigated to"+baseUrl+":"+port);
        indexPage.goToIndexPage(baseUrl+":"+port);
     }

    @Test
    @DirtiesContext
    void goToLoginPage() {
        indexPage.goToIndexPage(baseUrl+":"+port)
                .goToLoginPage();
    }

    @Test
    @DirtiesContext
    void performLogin() {
        indexPage.goToIndexPage(baseUrl+":"+port).goToLoginPage();
        loginPage.login("user","password");
        indexPage.iAmLogged();
    }

    @Test
    @DirtiesContext
    void errorPerformingLogin() {
        indexPage.goToIndexPage(baseUrl+":"+port).goToLoginPage();
        loginPage
                .login("usuario","password")
                .verifyPasswordErrorMessage("Invalid username and password.");
    }

}

