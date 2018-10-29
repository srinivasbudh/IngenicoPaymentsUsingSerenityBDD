package com.ingenico.payments.pageObjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * The PageObject class holds the web elements
 */
public class PaymentPage extends PageObject {


    @FindBy(id="logoimage")
    WebElement paymentPageHeader;

    @FindBy(css="#shoppingcartcontent .amount")
    WebElement orderValueText;

    @FindBy(xpath="//button[@name='paymentProductId']//div[contains(string(),'iDEAL')]")
    WebElement iDealPaymentOption;

    @FindBy(id="issuerId")
    WebElement selectBankDropDown;

    @FindBy(id="primaryButton")
    WebElement paymentButton;

    @FindBy(xpath="//input[@value='Confirm Transaction']")
    WebElement confirmTransactionButton;

    @FindBy(css="#paymentoptionswrapper p")
    WebElement paymentStatus;

    /**
     * This method selects Value From Dropdown
     */
    public void selectValue(String bankName){
        waitFor(selectBankDropDown);
        Select dropDown = new Select(selectBankDropDown);
        dropDown.selectByValue(bankName);
    }

    /**
     * This method return is paymentPageHeader is loaded
     * @return the paymentPageHeader
     */
    public boolean isPaymentPageHeaderLoaded(){
        return paymentPageHeader.isDisplayed();
    }

    /**
     * This Method returns Total Value Of the Transaction
     * @return Transaction value
     */
    public String getTransactionValue(){
        return orderValueText.getText();
    }

    /**
     * This Method clicks IdealPayment
     */
    public void clickIdealPaymentOption(){
       if(iDealPaymentOption.isDisplayed()){
           iDealPaymentOption.click();
       }
    }

    /**
     * This Method clicks on Payment Button
     */
    public void clickPayButton(){
        paymentButton.click();
    }

    /**
     * This method clicks on Confirm transaction button
     */
    public void clickConfirmTransactionButton(){
        waitFor(confirmTransactionButton);
        confirmTransactionButton.click();
    }

    /**
     * This method return the payment status message
     * @return the paymentStatus
     */
    public String getStatusMessage(){
        return paymentStatus.getText();
    }
}
