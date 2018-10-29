# Hosted Checkout API Automation using Serenity BDD

In this Project I have automated Hoseted Checkout API using Serenity Rest assured and Web Automation is handled by Serenity BDD.
 
 Serenity is an open source project available as maven dependency which has vast range of options provided for Test automation of web application 
 


# About Hosted Check out API

Hosted check out API is a Rest API for generating Payment URL  for the transactions

# Task

Task 1: Generate authorization keys. You can find the documentation here. **_Completed_** 

Task 2: Use Java/Groovy and create REST requests based on our API’s Created hosted checkout method. **_Completed_** 

Task 3: Get the redirection URL from the response returned in above step and perform a browser redirect (use Google’s Chrome browser). **_Completed_**

End Goal:
   * Source Repository to run tests in Headless browser on Jenkins **_Achieved_**(can be achieved with this framework need to setup Jenkin runner using GIT repository)
   * Should generate html test execution report showing test cases, test steps along with screenshots and request, responses **_Achieved_**

# Task split down
In order to complete a payment transaction using Ingenico Connect, the following operations are done:
1. Generate Authorization signature for the API
2. Invoke `CreateHostedCheckout` resource
3. Fetch the `partialRedirectUrl` from API response and form the payment URL
4. Navigate to the webpage in browser
5. Select `iDeal` payment method and pay as an `Issuer`
6. Complete the payment transaction

#Task Deliverables

1) Test Automation Frame work Performing all the 3 tasks using Serneity BDD (Open Source)
2) All test cases Listed in BDD Format in the Gherkin(cucumber) file
3) Detailed test reports With Screen shots and Rest API Requests and Response Can be viewed
4) Test Framework Build using Maven
5) Headless Browser execution support
6) 
7) Git source repository ready for Jenkins Build

# Test Approach

To complete the Above task I have a listed positive and negative cases in BDD format as below 

I have chosen BDD Test cases as these can be easily validated across  acceptance criteria

# Run Tests using Maven commands

	Open a command window and run:

		WindowsOS:	mvn clean verify

		MacOs : mvn clean verify -Dwebdriver.chrome.driver=DriverPathAsInput

	To Run Specific Tags of a test (Specific group of tests):

	  	mvn clean verify -Dcucumber.options="--tags @TAGNAME"

# To View Test Results

Navigate to `(target\site\serenity\index.html)` test results along with screenshots and API Request Response can be found under **_`index.html`_**.
