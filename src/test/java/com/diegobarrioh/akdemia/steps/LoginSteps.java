package com.diegobarrioh.akdemia.steps;

import com.diegobarrioh.akdemia.pages.IndexPage;
import com.diegobarrioh.akdemia.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.h2.index.Index;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class LoginSteps {

    @Autowired
    private IndexPage indexPage;

    @Autowired
    private LoginPage loginPage;

    @Given("I am on the login page")
    public LoginSteps iAmOnTheLoginPage() {
        indexPage
                .goToIndexPage()
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
