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

    @Value("${spring.application.url}")
    private String appUrl;

    @LocalServerPort
    private String randomPort;

    @Test
    @DirtiesContext
    void navigateToIndex() {
        indexPage.navigate(appUrl + ":" + randomPort);
        System.out.println("navigated to " + appUrl + ":" + randomPort);
    }

    @Test
    @DirtiesContext
    void performLogin() {
        indexPage.navigate(appUrl + ":" + randomPort);
        System.out.println("navigated to " + appUrl + ":" + randomPort);
        indexPage.ClickLogin();
        System.out.println("clicked on login");

        loginPage.login("user", "password");
    }


}

