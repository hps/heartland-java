## Heartland SecureSubmit Java SDK now supporting ApplePay
>Java developers can easily integrate with Heartland Payment Systems through our stream-lined SDK. 


#### Obtaining Payment Data
Below is one of the delegate methods for `PKPaymentAuthorizationViewControllerDelegate`.  It is called after the iOS user has selected their method of payment and authorized the ApplePay transaction.  The first portion converts the payment data to a JSON string, which is what is expected when you create a new `PaymentToken` object in our Java SDK.
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
These three lines of code are all that is required to decrypt your PKPaymentToken.  The JSON string in the `PaymentToken` constructor represents JSON data obtained from the iOS device as displayed in the previous code block.
```java
PaymentToken token = new PaymentToken("{ data: ..., header: { ... }, signature: ... }");
DecryptService service = new DecryptService("PrivateKeyExport.p12", "PrivateKeyPassword");
PaymentData paymentData = service.decrypt(token);
```

#### Gateway Authentication
All you need to authenticate with our gateway is a secret api key.  You can obtain a new key for our certification ("sandbox") environment by simply registering and navigating to Account->Profile on our [DeveloperPortal]
```java
HpsServicesConfig config = new HpsServicesConfig();
config.setSecretAPIKey("skapi_cert_...");
```
#### Cardholder Information
The following code illustrates how to properly build out a HpsCardHolder object.
```java
HpsCardHolder cardHolder = new HpsCardHolder();
cardHolder.setFirstName("Bill");
cardHolder.setLastName("Johnson");

HpsAddress address = cardHolder.getAddress();
address.setAddress("6860 Dallas Pkwy");
address.setCity("Irvine");
address.setState("TX");
address.setZip("75024");
address.setCountry("United States");
```
#### Completing the credit sale
We can complete our credit sale example now that we have both configuration and cardholder information.  Simply create a new HpsCreditService with the configuration object and call the charge() method.  The boolean flag indicates that we do not want to allow duplicate transactions on our gateway.  This behavior is not desired in most cases, however, some merchants may opt to allow it.
``` java
HpsCreditService creditService = new HpsCreditService(config);
HpsCharge response = creditService.charge(paymentData, cardHolder, false);
```

## Requirements
1. Java 1.7
2. ApplePay MerchantID Certificate in p12 format

## Contributing

1. Fork it
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -am 'Add some feature'`)
4. Push to the branch (`git push origin my-new-feature`)
5. Create new Pull Request
[DeveloperPortal]: https://developer.heartlandpaymentsystems.com/securesubmit
