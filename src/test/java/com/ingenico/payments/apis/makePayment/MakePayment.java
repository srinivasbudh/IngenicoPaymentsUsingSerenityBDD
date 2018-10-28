package com.ingenico.payments.apis.makePayment;

import com.google.gson.JsonObject;
import com.ingenico.payments.apis.AuthenticationToken;
import helper.ConvertTime;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import java.util.ResourceBundle;
import static net.serenitybdd.rest.SerenityRest.given;

/**
 * Created by Srinivas Budha on 10/28/2018.
 */
public class MakePayment {

    //Setting Json Objects for Creating Request Body
    private JsonObject order = new JsonObject();
    private JsonObject jsonBody = new JsonObject();

    //Creating config-files to access propety files
    ResourceBundle config = ResourceBundle.getBundle("test-setup");
    ResourceBundle configvalues = ResourceBundle.getBundle("testing-system-settings");

    //Assigning Header and other varaibles
    String merchantKey = config.getString("api.merchantKey");
    String httpMethod = "POST";
    String contentType = "application/json;charset=UTF-8";
    String xGcsMessageid = "6480071e-039d-4dca-a966-4ce3c1bc201b";
    String xGcsRequestid = "1cc6daff-a305-4d7b-94b0-c580fd5ba6b4";
    String uri = "/v1/" + merchantKey + "/hostedcheckouts";
    String utcDateTime = ConvertTime.getGMTTime();

    //message created to generate a Signature
    String message = httpMethod + "\n" + contentType + "\n" + utcDateTime + "\n" + "x-gcs-messageid:"+xGcsMessageid + "\n" + "x-gcs-requestid:"+xGcsRequestid + "\n" + uri + "\n";

    String baseUrl = configvalues.getString("api.BasePath") + "/hostedcheckouts";

    /**
     * Creating Json Request Body based on the input parameter
     * @param currencyCode
     * @param amount
     * @param countryCode
     * @param variant
     * @param locale
     * @return Request Body
     */
    public JsonObject createResponseBody(String currencyCode,String amount,String countryCode,String variant,String locale){
        JsonObject amountOfMoney = new JsonObject();
        amountOfMoney.addProperty("currencyCode", currencyCode);
        amountOfMoney.addProperty("amount", amount);

        JsonObject billingAddress = new JsonObject();
        billingAddress.addProperty("countryCode", countryCode);

        JsonObject customer = new JsonObject();
        customer.addProperty("merchantCustomerId", "2885");
        customer.add("billingAddress", billingAddress);

        JsonObject hostedCheckoutSpecificInput = new JsonObject();
        hostedCheckoutSpecificInput.addProperty("variant", variant);
        hostedCheckoutSpecificInput.addProperty("locale", locale);

        order.add("amountOfMoney", amountOfMoney);
        order.add("customer", customer);

        jsonBody.add("order", order);
        jsonBody.add("hostedCheckoutSpecificInput", hostedCheckoutSpecificInput);

        return jsonBody;
    }

    /**
     * This method send a post request to create an order
     * @param currencyCode
     * @param amount
     * @param countryCode
     * @param variant
     * @param locale
     */
    public void SendRequest(String currencyCode,String amount,String countryCode,String variant,String locale){
        try{
            Response addRegulatoryDocResponse = given()
                    .header("Content-Type", contentType)
                    .header("Date", utcDateTime)
                    .header("X-GCS-MessageId", xGcsMessageid)
                    .header("X-GCS-RequestId", xGcsRequestid)
                    .header("Authorization", Serenity.sessionVariableCalled("accessToken")).body(createResponseBody(currencyCode, amount, countryCode, variant, locale)).when().log()
                    .all().post(baseUrl);
            JsonPath addRegulatoryDocResponseObject = new JsonPath(addRegulatoryDocResponse.getBody().asString());
            Serenity.setSessionVariable("status").to(addRegulatoryDocResponse.getStatusCode());
            Serenity.setSessionVariable("createPaymentResponse").to(addRegulatoryDocResponseObject);
        }catch (Exception e){

        }
    }

    /**
     * This method generates a token for a transcation to take place
     */
    public void generateToken(){
        AuthenticationToken authenticationToken=new AuthenticationToken();
        Serenity.setSessionVariable("accessToken").to(authenticationToken.createAuthenticationToken(message));
    }

    /**
     * This method retrieves Payment URL from the Response Created
     */
    public void retrievePaymentURLFromResponse(){
        JsonPath createPaymentResponse = Serenity.sessionVariableCalled("createPaymentResponse");
        String initialURL=createPaymentResponse.getString("partialRedirectUrl");
        String paymentURL= "https://payment."+initialURL;
        Serenity.setSessionVariable("paymentURL").to(paymentURL);
        System.out.println("paymrnt URL is "+paymentURL);
    }
}
