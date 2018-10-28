package com.ingenico.payments.pageMethods;

import com.ingenico.payments.apis.makePayment.MakePayment;
import net.thucydides.core.annotations.Step;

/**
 * Created by Srinivas on 10/28/2018.
 */
public class APIMethods {

    MakePayment makePayment=new MakePayment();

    @Step
    public void setToken(){
        makePayment.generateToken();
    }

    @Step
    public void createTransaction(String currencyCode,String amount,String countryCode,String variant,String locale){
        makePayment.SendRequest(currencyCode,amount,countryCode,variant,locale);
    }

    @Step
    public void getPaymentURL(){
        makePayment.retrievePaymentURLFromResponse();
    }

}
