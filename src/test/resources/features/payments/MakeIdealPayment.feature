Feature: Make Ideal Payment

  As a user I should be able to able to generate the payment transaction URL from Hosted Checkout Service
  And I should be able to make a payment for a transaction using IDeal

  Background:
    Given User has A Valid Access Token for Hosted Checkout Service

  @VerifySuccessFulPaymentUsingIdeal
  Scenario Outline: Verify user has successfully made a Payment using Ideal
    Given User has created a transaction using <currencyCode>,<amount>,<countryCode>,<variant>,<locale>
    And Partial redirect URL is found based On Input data
    When he launch payment page Based on URL from hosted Checkout response
    And pay using iDeal with bank of his choice
    Then User Payment is successful
    Examples:
      | currencyCode  | amount  | countryCode | variant | locale |
      | EUR           | 2000    | NL          | test    | en_NL  |

  @VerifyPaymentOptionsAreSameAsExpected
  Scenario Outline: Verify user pays exact amount as per the input
    Given User has created a transaction using <currencyCode>,<amount>,<countryCode>,<variant>,<locale>
    And Partial redirect URL is found based On Input data
    When he launch payment page Based on URL from hosted Checkout response
    Then Payment webPage has same <currencyCode> and <amount>
    Examples:
      | currencyCode  | amount  | countryCode | variant | locale |
      | GBP           | 1002    | GB          | test    | en_GB  |
      | INR           | 2502    | IN          | test    | en_IN  |