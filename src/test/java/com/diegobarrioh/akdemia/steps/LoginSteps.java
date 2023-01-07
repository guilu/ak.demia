package com.diegobarrioh.akdemia.steps;

import com.diegobarrioh.akdemia.pages.IndexPage;
import com.diegobarrioh.akdemia.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginSteps {

    @Autowired
    private IndexPage indexPage;

    @Autowired
    private LoginPage loginPage;

    @LocalServerPort
    private int port;

    @Value("${spring.application.url}")
    private String baseUrl;

    @Given("I am on the login page")
    public LoginSteps iAmOnTheLoginPage() {
        indexPage
                .goToIndexPage(baseUrl+":"+port)
                .goToLoginPage();
        return this;
    }

    @When("I try to login with {string} and {string}")
    public void iTryToLoginWithAnd(String user, String password) {
        loginPage.login(user,password);
    }

    @Then("I verify invalid login message")
    public void iVerifyInvalidLoginMessage() {
        loginPage.verifyPasswordErrorMessage("Invalid username and password.");
    }
}
