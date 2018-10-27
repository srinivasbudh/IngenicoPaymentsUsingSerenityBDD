Feature: Make Ideal Payment

  As a user I should be able to able to generate the payment transaction URL from Hosted Checkout Service
  And I should be able to make a payment for a transaction using IDeal

  Background:
    Given User has A Valid Access Token for Hosted Checkout Service

  Scenario Outline: Verify user has successfully made a Payment using Ideal
    Given User has created a transaction using <currencyCode>,<amount>,<countryCode>,<variant>,<locale>
    And Partial redirect URL is found based On Input data
    When he Attempts to make a payment using Ideal
    Then User Payment is successful
    Examples:
      | currencyCode  | amount  | countryCode | variant | locale |
      | EUR           | 100	  | NL          | test    | en_GB  |