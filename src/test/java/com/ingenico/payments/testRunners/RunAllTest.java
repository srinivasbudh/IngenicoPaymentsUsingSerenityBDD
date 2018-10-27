package com.ingenico.payments.testRunners;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Integration test using cucumber runner
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/", glue = "com.ingenico.payments")

public class RunAllTest {
}
