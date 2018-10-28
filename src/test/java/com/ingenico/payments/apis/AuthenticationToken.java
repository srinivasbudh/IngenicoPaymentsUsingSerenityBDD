package com.ingenico.payments.apis;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

/**
 * Created by Srinivas Budha on 10/28/2018.
 */
public class AuthenticationToken {
    ResourceBundle config = ResourceBundle.getBundle("test-setup");
    String apiKey= config.getString("api.key");
    String apiSecret= config.getString("api.secret");

    /**
     * This Methods Creates a signature based on the input
     * @param message input created using all the required fields of headers
     * @return Signature generated
     */
    public String createSignatureForAuthentication(String message){
        Mac sha256Hmac;
           try {

            sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(apiSecret.getBytes(Charset.forName("UTF-8")), "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] unencodedResult = sha256Hmac.doFinal(message.getBytes(Charset.forName("UTF-8")));
            return Base64.encodeBase64String(unencodedResult);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such HMAC algorithm ", e);
        } catch (InvalidKeyException e) {
               throw new RuntimeException("Invalid HMAC key", e);
        }
    }

    /**
     * This method Generates the required authorization Token
     * @param message input created using all the required fields of headers
     * @return Authorization token
     */
    public String createAuthenticationToken(String message) {
        return "GCS v1HMAC:"  + apiKey + ":" + createSignatureForAuthentication(message);
    }

}
