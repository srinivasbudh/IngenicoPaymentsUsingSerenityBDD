package com.ingenico.payments.stepDefinitions.payments;

import com.ingenico.payments.pageMethods.APIMethods;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Srinivas Budha on 10/28/2018.
 */
public class PaymentsStepDefinition {

    @Steps
    APIMethods apiMethods;

    @Given("^User has A Valid Access Token for Hosted Checkout Service$")
    public void generateToken() {
        apiMethods.setToken();
    }

    @Given("^User has created a transaction using (.*),(.*),(.*),(.*),(.*)$")
    public void createTransaction(String currencyCode,String amount,String countryCode,String variant,String locale) {
        apiMethods.createTransaction(currencyCode, amount, countryCode, variant, locale);
    }

    @Given("^Partial redirect URL is found based On Input data$")
    public void partial_redirect_URL_is_found_based_On_Input_data() throws Throwable{
        int statusCode=Serenity.sessionVariableCalled("status");
        assertThat(statusCode).isEqualTo(201);
        apiMethods.getPaymentURL();
    }

    @When("^he Attempts to make a payment using Ideal$")
    public void he_Attempts_to_make_a_payment_using_Ideal() {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^User Payment is successful$")
    public void user_Payment_is_successful() {
        // Write code here that turns the phrase above into concrete actions

    }
}
