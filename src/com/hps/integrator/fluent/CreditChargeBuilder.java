package com.hps.integrator.fluent;

import com.hps.integrator.applepay.ecv1.PaymentData;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsEMVDataType;
import com.hps.integrator.entities.HpsTagDataType;
import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditChargeBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsCharge> {
    private BigDecimal amount;
    private String currency;
    private HpsCreditCard card;
    private HpsTokenData token;
    private HpsTrackData trackData;
    private HpsCardHolder cardHolder;
    private boolean requestMultiUseToken = false;
    private HpsTransactionDetails details;
    private String txnDescriptor;
    private boolean allowPartialAuth = false;
    private boolean cpcReq = false;
    private HpsDirectMarketData directMarketData;
    private boolean allowDuplicates = false;
    private PaymentData paymentData;
    private boolean cardPresent = false;
    private boolean readerPresent = false;
    private BigDecimal gratuity;
    private HpsAutoSubstantiation autoSubstantiation;
    private HpsTxnReferenceData originalTxnReferenceData;
    private BigDecimal convenienceAmount;
    private BigDecimal shippingAmount;
    private HpsTagDataType tagData;
    private HpsEMVDataType emvData;
    
    public CreditChargeBuilder withEMVData(HpsEMVDataType emvData){
        this.emvData = emvData;
        return this;
    }
    public CreditChargeBuilder withTagData(HpsTagDataType  tagData){
        this.tagData = tagData;
        return this;
    }
    public CreditChargeBuilder withConvenienceAmount(BigDecimal convenienceAmount){
        this.convenienceAmount = convenienceAmount;
        return this;
    }
    public CreditChargeBuilder withShippingAmount(BigDecimal shippingAmount){
        this.shippingAmount = shippingAmount;
        return this;
    }
    public CreditChargeBuilder withAmount(BigDecimal amount){
        this.amount = amount;
        return this;
    }
    public CreditChargeBuilder withCurrency(String currency){
        this.currency = currency;
        return this;
    }
    public CreditChargeBuilder withCard(HpsCreditCard card){
        this.card = card;
        return this;
    }
    public CreditChargeBuilder withToken(String token){
        if (this.token == null) {
            this.token = new HpsTokenData();
        }
        this.token.setTokenValue(token);
        return this;
    }
    public CreditChargeBuilder withToken(HpsTokenData token){
        this.token = token;
        return this;
    }
    public CreditChargeBuilder withTrackData(HpsTrackData trackData){
        this.trackData = trackData;
        return this;
    }
    public CreditChargeBuilder withCardHolder(HpsCardHolder cardHolder){
        this.cardHolder = cardHolder;
        return this;
    }
    public CreditChargeBuilder withRequestMultiUseToken(boolean requestMultiUseToken){
        this.requestMultiUseToken = requestMultiUseToken;
        return this;
    }
    public CreditChargeBuilder withDetails(HpsTransactionDetails details){
        this.details = details;
        return this;
    }
    public CreditChargeBuilder withTxnDescriptor(String txnDescriptor){
        this.txnDescriptor = txnDescriptor;
        return this;
    }
    public CreditChargeBuilder withAllowPartialAuth(boolean allowPartialAuth){
        this.allowPartialAuth = allowPartialAuth;
        return this;
    }
    public CreditChargeBuilder withCpcReq(boolean cpcReq){
        this.cpcReq = cpcReq;
        return this;
    }
    public CreditChargeBuilder withAllowDuplicates(boolean allowDuplicates){
        this.allowDuplicates = allowDuplicates;
        return this;
    }
    public CreditChargeBuilder withPaymentData(PaymentData paymentData){
        this.paymentData = paymentData;
        return this;
    }
    public CreditChargeBuilder withCardPresent(boolean cardPresent){
        this.cardPresent = cardPresent;
        return this;
    }
    public CreditChargeBuilder withReaderPresent(boolean readerPresent){
        this.readerPresent = readerPresent;
        return this;
    }
    public CreditChargeBuilder withGratuity(BigDecimal gratuity){
        this.gratuity = gratuity;
        return this;
    }
    public CreditChargeBuilder withOriginalTxnReferenceData(HpsTxnReferenceData originalTxnReferenceData){
        this.originalTxnReferenceData = originalTxnReferenceData;
        return this;
    }
    public CreditChargeBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        this.directMarketData = directMarketData;
        return this;
    }
    public CreditChargeBuilder withAutoSubstantiation(HpsAutoSubstantiation autoSubstantiation){
        this.autoSubstantiation = autoSubstantiation;
        return this;
    }

    public CreditChargeBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsCharge execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditSale");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");
        Et.subElement(block1, "AllowPartialAuth").text(allowPartialAuth ? "Y" : "N");
        Et.subElement(block1, "Amt").text(amount.toString());
       
        if(convenienceAmount != null){
        	HpsInputValidation.checkAmount(convenienceAmount);
            Et.subElement(block1, "ConvenienceAmtInfo").text(convenienceAmount.toString());
         }
        if(shippingAmount != null){
        	HpsInputValidation.checkAmount(shippingAmount);
        	Et.subElement(block1, "ShippingAmtInfo").text(shippingAmount.toString()); 
         }       
        
        if(gratuity != null)
            Et.subElement(block1, "GratuityAmtInfo").text(gratuity.toString());

        if(cardHolder != null)
            block1.append(service.hydrateCardHolder(cardHolder));

        Element cardData = Et.subElement(block1, "CardData");
        if(card != null) {
            cardData.append(service.hydrateCardManualEntry(card, cardPresent, readerPresent));
            if(card.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(card.getEncryptionData()));
        }
        if(token != null) {
            this.token.setCardPresent(cardPresent);
            this.token.setReaderPresent(readerPresent);
            cardData.append(service.hydrateTokenData(token));
        }
        if(trackData != null) {
            cardData.append(service.hydrateTrackData(trackData));
            if(trackData.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        if(paymentData != null) {
            Element manualEntry = Et.element("ManualEntry");
            Et.subElement(manualEntry, "CardNbr").text(paymentData.getApplicationPrimaryAccountNumber());
            String expDate = paymentData.getApplicationExpirationDate();
            Et.subElement(manualEntry, "ExpMonth").text(expDate.substring(2, 4));
            Et.subElement(manualEntry, "ExpYear").text("20" + expDate.substring(0, 2));
            cardData.append(manualEntry);

            block1.append(service.hydrateSecureECommerce(paymentData.getPaymentData()));
        }
        Et.subElement(cardData, "TokenRequest").text(requestMultiUseToken ? "Y" : "N");

        if(cpcReq) Et.subElement(block1, "CPCReq").text("Y");
        if(details != null)
            block1.append(service.hydrateAdditionalTxnFields(details));
        if(txnDescriptor != null)
            Et.subElement(block1, "TxnDescriptor").text(txnDescriptor);
        if(autoSubstantiation != null)
            block1.append(service.hydrateAutoSubstantiation(autoSubstantiation));
        if(originalTxnReferenceData != null) {
            Element refElement = Et.subElement(block1, "OrigTxnRefData");
            Et.subElement(refElement, "AuthCode").text(originalTxnReferenceData.getAuthorizationCode());
            Et.subElement(refElement, "CardNbrLastFour").text(originalTxnReferenceData.getCardNumberLast4());
        }
        if(directMarketData != null)
            block1.append(service.hydrateDirectMarketData(directMarketData));
        if(tagData != null) {
            block1.append(service.hydrateTagData(tagData));
        }
        if(emvData != null) {
            block1.append(service.hydrateEMVData(emvData));
        }
        String clientTransactionId = service.getClientTxnId(details);
        ElementTree response = service.submitTransaction(transaction, clientTransactionId);
        return new HpsCharge().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }

    private boolean onlyOnePaymentMethod(){
        int count = 0;
        if(card != null) count++;
        if(trackData != null) count++;
        if(token != null) count++;
        if(paymentData != null) count++;

        return count == 1;
    }
}
