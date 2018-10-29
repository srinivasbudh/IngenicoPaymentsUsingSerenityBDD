package com.ingenico.payments.pageMethods;

import Utils.GetTestProperties;
import com.ingenico.payments.pageObjects.PaymentPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import java.io.InputStream;
import java.util.Properties;

/**
 * The PaymentPageMethods class implements steps to use a browser to redirect to Ingenico hosted checkout page
 */
public class PaymentPageMethods {

    private PaymentPage paymentPage;

    /**
     * This Method Launches web browser and navigates to payment URL
     */
    @Step
    public void launchWebBrowser(){
        paymentPage.openAt(Serenity.sessionVariableCalled("paymentURL"));
    }

    /**
     * This method returns if payment page is loaded or not
     * @return the paymentPage
     */
    @Step
    public boolean checkPaymentPageLoaded(){
        return paymentPage.isPaymentPageHeaderLoaded();
    }

    /**
     * This Method returns Total Value Of the Transaction
     * @return Transaction value
     */
    @Step
    public String getTransactionValueFromPaymentPage(){
        return paymentPage.getTransactionValue();
    }

    /**
     * This method select iDEAL as payment option from 20 options
     */
    @Step
    public void selectPaymentOption(String paymentMethod){
        if (paymentMethod.equals("iDeal")) {
            paymentPage.clickIdealPaymentOption();
        }
    }

    /**
     * This method selects RABO bank as payment option
     */
    @Step
    public void selectIssuer(){
        paymentPage.selectValue(GetTestProperties.getValue("paymentBankValue"));
        paymentPage.clickPayButton();
    }

    /**
     * This method confirms the Payment
     */
    @Step
    public void confirmPayment(){
        paymentPage.clickConfirmTransactionButton();
    }

    /**
     * This method returns payment staus message
     * @return the paymentPage
     */
    @Step
    public String getPaymentStatusMessage(){
        return paymentPage.getStatusMessage();
    }


}