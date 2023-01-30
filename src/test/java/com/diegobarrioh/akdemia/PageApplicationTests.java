package com.diegobarrioh.akdemia;

import com.diegobarrioh.akdemia.pages.IndexPage;
import com.diegobarrioh.akdemia.pages.LoginPage;
import com.diegobarrioh.akdemia.pages.LogoutPage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class PageApplicationTests {

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
    void navigateToIndex() {
        indexPage.goToIndexPage(url());
        assertThat(indexPage.isAt()).isNotNull();
     }

    @Test
    void goToLoginPage() {
        indexPage.goToIndexPage(url())
                .goToLoginPage();
        assertThat(loginPage.isAt()).isNotNull();
    }

    @Test
    @WithMockUser
    void performLogin() {
        indexPage.goToIndexPage(url()).goToLoginPage();
        loginPage.login("user@gmail.com","1234");
        assertThat(indexPage.iAmLogged()).isNotNull();
    }

    @Test
    void errorPerformingLogin() {
        indexPage.goToIndexPage(url()).goToLoginPage();
        loginPage
                .login("user@gmail.com","1234")
                .verifyPasswordErrorMessage("Username and/or password erróneo.");
    }

    @Test
    void performLogout() {
        indexPage.goToIndexPage(url()).goToLoginPage();
        loginPage.login("user@gmail.com","1234");
        indexPage.gotToLogoutPage();
        //logoutPage.confirmLogout();
        indexPage.iJustLoggedOut();
    }


    private String url(){
        return this.baseUrl+":"+this.port;
    }

}

