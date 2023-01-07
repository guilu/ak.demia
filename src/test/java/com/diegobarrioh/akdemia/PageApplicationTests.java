package com.diegobarrioh.akdemia;

import com.diegobarrioh.akdemia.pages.IndexPage;
import com.diegobarrioh.akdemia.pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
public class PageApplicationTests {

    @Autowired
    private IndexPage indexPage;
    @Autowired
    private LoginPage loginPage;

    @Value("${spring.application.url}")
    private String appUrl;

    @Test
    @DirtiesContext
    void navigateToIndex() {
        System.out.println("navigated to"+appUrl);
        indexPage.goToIndexPage();
     }

    @Test
    @DirtiesContext
    void goToLoginPage() {
        indexPage.goToIndexPage()
                .goToLoginPage();
    }

    @Test
    @DirtiesContext
    void performLogin() {
        indexPage.goToIndexPage().goToLoginPage();
        loginPage.login("user","password");
    }

    @Test
    @DirtiesContext
    void errorPerformingLogin() {
        indexPage.goToIndexPage().goToLoginPage();
        loginPage
                .login("usuario","password")
                .verifyPasswordErrorMessage("Invalid username and password.");
    }

}

