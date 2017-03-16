<a href="http://developer.heartlandpaymentsystems.com/SecureSubmit" target="_blank">
	<img src="http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-heartland-logo" alt="Heartland logo" title="Heartland" align="right" />
</a>

# Heartland Java SDK

This SDK makes it easy to integrate your Java application with Heartland's [**Portico Gateway API**](http://developer.heartlandpaymentsystems.com/Portico). Supported features include:

* Card Not Present (eCommerce and mobile)
* Card Present (Retail and Restaurant)
* <a href="http://developer.heartlandpaymentsystems.com/SecureSubmit" target="_blank">Secure Submit</a> single-use tokenization and multi-use tokenization
* <a href="https://www.heartlandpaymentsystems.com/about-heartland-secure/" target="_blank">Heartland Secure</a> End-to-End Encryption (E3)
* [Credit](http://developer.heartlandpaymentsystems.com/Documentation/credit-card-payments/), [Gift & Loyalty](http://developer.heartlandpaymentsystems.com/Documentation/gift-card-payments/)</a>,and eCheck/ACH
* [Recurring Payments](http://developer.heartlandpaymentsystems.com/Documentation/recurring-payments/)
* Apple Pay 
* Android Mobile


|  <a href="#data-security"><b>Data Security</b></a> | <a href="#api-reference"><b>API Reference</b></a>  |  <a href="#testing--certification"><b>Testing & Certification</b></a> | <a href="#api-keys">API Keys</a> | Links |
|:--:|:--:|:--:|:--:|:--|
| [![](http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-secure)](#data-security)  | [![](http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-resources)](#documentation-and-examples)  | [![](http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-tools)](#certification--testing) | [![](http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-keys)](#api-keys) | <a href="http://developer.heartlandpaymentsystems.com/Account/Register" target="_blank">Register an Account</a> <br> <a href="http://www.heartlandpaymentsystems.com/partners/" target="_blank">Partner with Heartland</a> <br> <a href="http://developer.heartlandpaymentsystems.com/SecureSubmit/Support" target="_blank">Developer Support</a>  |


####Developer Support

You are not alone! If you have any questions while you are working through your development process, please feel free to <a href="mailto:entapp_devportal@e-hps.com?Subject=Developer Support Request">reach out to our team for assistance</a>.
 
 
## Requirements

 1. Java 1.7
 2. ApplePay MerchantID Certificate in p12 format
 3. [PCI DSS TLS 1.1+ Capability](https://cdn2.hubspot.net/hubfs/281302/Migrating_from_SSL_and_Early_TLS_-v12.pdf?t=1473353189573)
 
## Installation

Download and unzip or, using Git, [clone the repository](https://help.github.com/articles/cloning-a-repository/) where our [Java SDK](https://github.com/hps/heartland-java) is located on Github. Link in the project using the IDE of your choice and run! Because this SDK uses [Maven](https://maven.apache.org/what-is-maven.html) your build process should automatically download the external dependencies needed to build your application.

### Project Setup

When adding the SDK to your project, you may need to follow these additional steps:

1. Upgrade to the latest version of the Heartland SDK
2. Add a new repository for the [`ksoap2-android` library](http://simpligility.github.io/ksoap2-android/getting-started.html) so that the dependency is resolved
3. Install the “Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files” for your JDK. See [Java documentation](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html) for more details.

## API Keys

<img src="http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-keys" align="right"/>
To begin creating test transactions you will need to obtain a set of public and private keys. These are easily obtained by creating an account on our [developer portal](http://developer.heartlandpaymentsystems.com/).
Your keys are located under your profile information. 

[![Developer Keys](http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-devportal-keys)](http://developer.heartlandpaymentsystems.com/Account/KeysAndCredentials)

You will use your public key when implementing card tokenization and your private key will be used when communicating with our Portico Gateway. More details can be found in our [documentation](http://developer.heartlandpaymentsystems.com/SecureSubmit).

Note: Multi-Use tokenization is not enabled by default when creating an account. You can contact <a href="mailto:SecureSubmitCert@e-hps.com?subject=Multi-use Token Request&body=Please enable multi-use tokens on my developer portal account which was signed up under the following email : ">Heartland's Specialty Products Team</a> to have this enabled. This is also true if you wish to use Gift & Loyalty, ACH, and Debit.


## Data Security
 
 <img src="http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-secure" align="right"/>
 If your app stores, processes, or transmits cardholder data in cleartext then it is in-scope for PA-DSS. If your app is hosted, or the data in question otherwise comes into your organization, then the app and your entire company are in-scope for PCI DSS (either as a merchant or a service provider). Heartland offers a suite of solutions to help keep integrators' applications and/or environments shielded from cardholder data, whether at motion or at rest.
 
 * **Secure Submit** for eCommerce web or mobile applications ("card-not-present"), which leverages single-use tokenization to prevent card data from passing through the merchant or integrator's webserver. It only requires a simple JavaScript inclusion and provides two options for payment field hosting:
 
  * **Self-Hosted Fields** - this approach relies upon the standard, appropriately named, HTML form controls on the integrator's served web page.
  
  - **Heartland Hosted Fields** - this approach combines the Secure Submit service with iframes to handle presentation of the form fields and collection of sensitive data on Heartland servers. Since PCI version 3.1 the PCI Council and many QSAs advocate the iframe-based approach as enabling a merchant to more readily achieve PCI compliance via the simplified SAQ-A form. Check out the CoalFire's [whitepaper](http://developer.heartlandpaymentsystems.com/Resource/Download/coalfire-white-paper) for more information.
  
 - **Heartland Secure** for card-present retailers, hospitality, and other "POS" applications, comprises three distinct security technologies working in concert:
  - **End-to-End Encryption** (E3) - combines symmetric and asymmetric cryptography to form an "Identity-Based Encryption" methodology which keeps cardholder data encrypted from the moment of the swipe.
  
  - **Tokenization** - replaces sensitive data values with non-sensitive representations which may be stored for recurring billing, future orders, etc.
  
  - **EMV** - though less about data security and more about fraud prevention, EMV or chip card technology guarantees the authenticity of the payment card and is thus an important concern for retailers.
 
 Depending on your (or your customers') payment acceptance environment, you may need to support one or more of these technologies in addition to this SDK. This SDK also supports the ability to submit cleartext card numbers as input, but any developer who does so will be expected to demonstrate compliance with PA-DSS. Likewise any third party integrator who is planning on handling cleartext card data on behalf of other merchants will be expected to demonstrate their PCI DSS compliance as a Service Provider prior to completing certification with Heartland.
 
 If you implement Secure Submit tokenization for your web or mobile application you will never have to deal with handling a card number - Heartland will take care of it for you and return a token to initiate the charge from your servers.
 
 Similarly, if you implement Heartland Secure with E3 (for both swiped and keyed entry methods) then your POS application will be out-of-scope for PA-DSS. Heartland Secure certified devices will only ever return E3 encrypted data which can safely be passed through your systems as input to this SDK. Heartland Secure devices include many popular models manufactured by PAX and Ingenico.
 
 To summarize, when you create a `paymentMethod` using this SDK you have the following options for securely avoiding interaction with sensitive cardholder data:
 
 - Card data (track or PAN) may be sent directly from a web browser to Heartland, returning a SecureSubmit single use token that is then sent to your server.
 
 - Encrypted card data (track or PAN) may be obtained directly from a Heartland Secure device and passed to the SDK

 
## Documentation and Examples 

 <img src="http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-resources" align="right"/>
  You can find the latest SDK documentation along with code examples on our [Developer Portal](http://developer.heartlandpaymentsystems.com/documentation).
  In addition you can find working examples in the our example code repository:
  
  * [End-to-End](https://github.com/hps/heartland-java/tree/master/examples/end-to-end) 
  * [PayPal](https://github.com/hps/heartland-java/tree/master/examples/paypal)

*Quick Tip*: The included [test suite](https://github.com/hps/heartland-java/tree/master/tests/com/hps/integrator/tests) can be a great source of code samples for using the SDK!

#### Fluent Interface

Note that our SDK provides a [Fluent Interface](https://en.wikipedia.org/wiki/Fluent_interface) which allows you to replace a charge call that looks like this:

 ```java 
 HpsCharge response = charge(new BigDecimal("13.01"), "USD", card, cardHolder, true, true)
 ````
  
 With one that reads like this:
 
 ```java
 HpsAuthorization response = creditService.charge(BigDecimal.valueOf(13.01))
                            .withCard(card)
                            .withCardHolder(cardHolder)
                            .withRequestMultiUseToken(true)
                            .execute();

````

Between the two samples you can more easily read and understand what the code is doing in the second example than the first. That is a big advantage and helps speed development and reduce errors when using methods that allow a large number of parameters.


## ApplePay

Java developers can easily integrate with Heartland Payment Systems for ApplePay. 

#### Obtaining Payment Data

Below is one of the delegate methods for `PKPaymentAuthorizationViewControllerDelegate`.  It is called after the iOS user has selected their method of payment and authorized the ApplePay transaction.  The first portion converts the payment data to a JSON string, which is what is expected when you create a new `PaymentToken` object in the SDK.

```objective-c
- (void) paymentAuthorizationViewController:(PKPaymentAuthorizationViewController *)controller
                        didAuthorizePayment:(PKPayment *)payment
                                 completion:(void (^)(PKPaymentAuthorizationStatus))completion
{
    NSString *json = [[NSString alloc] initWithData:payment.token.paymentData
                                          encoding:NSUTF8StringEncoding];
    
    // Here is where you might post the json to a service for decryption and authorization. 
    
    if( transactionSuccessful ) // pseudo code to complete the transaction in PassKit
    {
        completion(PKPaymentAuthorizationStatusSuccess);
    }
    else
    {
        completion(PKPaymentAuthorizationStatusFailure);
    }
}
```

#### Decrypting the PKPaymentToken JSON

These three lines of code are all that is required to decrypt your PKPaymentToken. The JSON string in the `PaymentToken` constructor represents JSON data obtained from the iOS device as displayed in the previous code block.

```java
PaymentToken token = new PaymentToken("{ data: ..., header: { ... }, signature: ... }");
DecryptService service = new DecryptService("PrivateKeyExport.p12", "PrivateKeyPassword");
PaymentData paymentData = service.decrypt(token);
```


## Testing & Certification

<img src="http://developer.heartlandpaymentsystems.com/Resource/Download/sdk-readme-icon-tools" align="right"/>
Testing your implementation in our Certification/Sandbox environment helps to identify and squash bugs before you begin processing transactions in the production environment. While you are encouraged to run as many test transactions as you can, Heartland provides a specific series of tests that you are required to complete before receiving Certification. Please <a href="mailto:SecureSubmitCert@e-hps.com?Subject=Certification Request&Body=I am ready to start certifying my integration! ">contact</a> Heartland in order to initiate a certification project. 

*Quick Tip*: You can get a head start on your certification by reviewing the [certification tests](https://github.com/hps/heartland-java/tree/master/tests/com/hps/integrator/tests/certification) in the included test suite.

####Test Card Data

The following card numbers are used by our Certification environment to verify that your tests worked. Note that while variations (such as 4111111111111111) will work for general testing the cards listed below are required to complete certification.

<table align="center">
	<thead>
		<tr>
			<th scope="col">Name</th>
			<th scope="col">Number</th>
			<th scope="col">Exp Month</th>
			<th scope="col">Exp Year</th>
			<th scope="col">CVV</th>
			<th scope="col">Address</th>
			<th scope="col">Zip</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Visa</td>
			<td>4012002000060016</td>
			<td>12</td>
			<td>2025</td>
			<td>123</td>
			<td>6860 Dallas Pkwy</td>
			<td>750241234</td>
		</tr>
		<tr>
			<td>MasterCard</td>
			<td>5473500000000014</td>
			<td>12</td>
			<td>2025</td>
			<td>123</td>
			<td>6860 Dallas Pkwy</td>
			<td>75024</td>
		</tr>
		<tr>
			<td>Discover</td>
			<td>6011000990156527</td>
			<td>12</td>
			<td>2025</td>
			<td>123</td>
			<td>6860</td>
			<td>750241234</td>
		</tr>
		<tr>
			<td>Amex</td>
			<td>372700699251018</td>
			<td>12</td>
			<td>2025</td>
			<td>1234</td>
			<td>6860</td>
			<td>75024</td>
		</tr>
		<tr>
			<td>JCB</td>
			<td>3566007770007321</td>
			<td>12</td>
			<td>2025</td>
			<td>123</td>
			<td>6860</td>
			<td>75024</td>
		</tr>
	</tbody>
</table>

####Testing Exceptions

During your integration you will want to test for specific issuer responses such as 'Card Declined'. Because our sandbox does not actually reach out to issuers we have devised specific transaction amounts that will trigger  [issuer response codes](https://cert.api2.heartlandportico.com/Gateway/PorticoDevGuide/build/PorticoDeveloperGuide/Issuer%20Response%20Codes.html) and [gateway response codes](https://cert.api2.heartlandportico.com/Gateway/PorticoDevGuide/build/PorticoDeveloperGuide/Gateway%20Response%20Codes.html). Please <a href="mailto:SecureSubmitCert@e-hps.com?subject=Hard Coded Values Spreadsheet Request">contact</a> Heartland for a complete listing of values you can charge to simulate AVS, CVV and Transaction declines, errors, and other responses that you can catch in your code:

```java
try
    {
        chargeService.charge(new BigDecimal("-5"), "usd", creditCard, cardHolder);
    }
catch (HpsInvalidRequestException e)
    {
        // handle error for amount less than zero dollars
    }
catch (HpsAuthenticationException e)
    {
        // handle errors related to your HpsServiceConfig
    }
catch (HpsCreditException e)
    {
        // handle card-related exceptions: card declined, processing error, etc
    }
catch (HpsGatewayException e)
    {
        // handle gateway-related exceptions: invalid cc number, gateway-timeout, etc
    }
````

More exceptions can be found [here](https://github.com/hps/heartland-java/tree/master/src/com/hps/integrator/infrastructure).

## Contributing

All our code is open sourced and we encourage fellow developers to contribute and help improve it!

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Ensure SDK tests are passing
4. Commit your changes (`git commit -am 'Add some feature'`)
5. Push to the branch (`git push origin my-new-feature`)
6. Create new Pull Request


####Included Test Suite

The included test suite can help ensure your contribution doesn't cause unexpected errors and is a terrific resource of working examples that you can reference. As mentioned earlier, the [certification folder](https://github.com/hps/heartland-java/tree/master/tests/com/hps/integrator/tests/certification) contains tests that mirror the types of requirements you will encounter when you certify your integration for production.


