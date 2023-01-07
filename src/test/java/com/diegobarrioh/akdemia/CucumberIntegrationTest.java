package com.diegobarrioh.akdemia;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        plugin = {"pretty","html:target/cucumber-html-report.html"},
        glue = {"com.diegobarrioh.akdemia.steps"}
)
public class CucumberIntegrationTest {
}
