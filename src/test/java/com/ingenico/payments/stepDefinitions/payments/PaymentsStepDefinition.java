package com.ingenico.payments.stepDefinitions.payments;

import com.ingenico.payments.pageMethods.APIMethods;
import com.ingenico.payments.pageMethods.PaymentPageMethods;
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

    @Steps
    PaymentPageMethods paymentPageSteps;

    @Given("^User has A Valid Access Token for Hosted Checkout Service$")
    public void generateToken() {
        apiMethods.setToken();
    }

    @Given("^User has created a transaction using (.*),(.*),(.*),(.*),(.*)$")
    public void createTransaction(String currencyCode,String amount,String countryCode,String variant,String locale) {
        apiMethods.createTransaction(currencyCode, amount, countryCode, variant, locale);
    }

    @Given("^Partial redirect URL is found based On Input data$")
    public void getPaymentURL(){
        int statusCode=Serenity.sessionVariableCalled("status");
        assertThat(statusCode).isEqualTo(201);
        apiMethods.getPaymentURL();
    }

    @When("^he launch payment page Based on URL from hosted Checkout response$")
    public void viewPaymentOptions() {
        paymentPageSteps.launchWebBrowser();
        paymentPageSteps.checkPaymentPageLoaded();
    }

    @When("^pay using (.*) with bank of his choice$")
    public void pay_using_bank_of_his_choice(String paymentMethod) {
        paymentPageSteps.selectPaymentOption(paymentMethod);
        paymentPageSteps.selectIssuer();
        paymentPageSteps.confirmPayment();
    }

    @Then("^User Payment is successful$")
    public void user_Payment_is_successful() {
        assertThat(paymentPageSteps.getPaymentStatusMessage()).isEqualToIgnoringCase("Your payment is successful.");
    }

    @Then("^Payment webPage has same (.*) and (.*)$")
    public void verifyPaymentValues(String currencyCode, String amount) {
        assertThat(paymentPageSteps.getTransactionValueFromPaymentPage()).containsIgnoringCase(currencyCode);
        assertThat(paymentPageSteps.getTransactionValueFromPaymentPage()).containsIgnoringCase(amount);
    }
}
