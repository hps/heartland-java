package com.hps.integrator.fluent;

import com.hps.integrator.applepay.ecv1.PaymentData;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsEMVDataType;
import com.hps.integrator.entities.HpsTagDataType;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsAutoSubstantiation;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditOfflineChargeBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsTransaction> {
    protected BigDecimal amount;
    protected HpsCreditCard card;
    protected String token;
    protected HpsTrackData trackData;
    protected HpsCardHolder cardHolder;
    protected boolean requestMultiUseToken = false;
    protected HpsTransactionDetails details;
    protected String txnDescriptor;
    protected boolean cpcReq = false;
    protected HpsDirectMarketData directMarketData;
    protected boolean allowDuplicates = false;
    protected PaymentData paymentData;
    protected boolean cardPresent = false;
    protected boolean readerPresent = false;
    protected BigDecimal gratuity;
    protected HpsAutoSubstantiation autoSubstantiation;
    protected String offlineAuthCode;
    protected BigDecimal convenienceAmount;
    protected BigDecimal shippingAmount;
    protected HpsTagDataType tagData;
    protected HpsEMVDataType emvData;
    
    public CreditOfflineChargeBuilder withEMVData(HpsEMVDataType emvData){
        this.emvData = emvData;
        return this;
    }
    public CreditOfflineChargeBuilder withTagData(HpsTagDataType tagData){
        this.tagData = tagData;
        return this;
    }
    public CreditOfflineChargeBuilder withConvenienceAmount(BigDecimal convenienceAmount){
        this.convenienceAmount = convenienceAmount;
        return this;
    }
    public CreditOfflineChargeBuilder withShippingAmount(BigDecimal shippingAmount){
        this.shippingAmount = shippingAmount;
        return this;
    }
    public CreditOfflineChargeBuilder withAmount(BigDecimal amount){
        this.amount = amount;
        return this;
    }
    public CreditOfflineChargeBuilder withCard(HpsCreditCard card){
        this.card = card;
        return this;
    }
    public CreditOfflineChargeBuilder withToken(String token){
        this.token = token;
        return this;
    }
    public CreditOfflineChargeBuilder withTrackData(HpsTrackData trackData){
        this.trackData = trackData;
        return this;
    }
    public CreditOfflineChargeBuilder withCardHolder(HpsCardHolder cardHolder){
        this.cardHolder = cardHolder;
        return this;
    }
    public CreditOfflineChargeBuilder withRequestMultiUseToken(boolean requestMultiUseToken){
        this.requestMultiUseToken = requestMultiUseToken;
        return this;
    }
    public CreditOfflineChargeBuilder withDetails(HpsTransactionDetails details){
        this.details = details;
        return this;
    }
    public CreditOfflineChargeBuilder withTxnDescriptor(String txnDescriptor){
        this.txnDescriptor = txnDescriptor;
        return this;
    }
    public CreditOfflineChargeBuilder withCpcReq(boolean cpcReq){
        this.cpcReq = cpcReq;
        return this;
    }
    public CreditOfflineChargeBuilder withAllowDuplicates(boolean allowDuplicates){
        this.allowDuplicates = allowDuplicates;
        return this;
    }
    public CreditOfflineChargeBuilder withPaymentData(PaymentData paymentData){
        this.paymentData = paymentData;
        return this;
    }
    public CreditOfflineChargeBuilder withCardPresent(boolean cardPresent){
        this.cardPresent = cardPresent;
        return this;
    }
    public CreditOfflineChargeBuilder withReaderPresent(boolean readerPresent){
        this.readerPresent = readerPresent;
        return this;
    }
    public CreditOfflineChargeBuilder withGratuity(BigDecimal gratuity){
        this.gratuity = gratuity;
        return this;
    }
    public CreditOfflineChargeBuilder withAutoSubstantiation(HpsAutoSubstantiation autoSubstantiation){
        this.autoSubstantiation = autoSubstantiation;
        return this;
    }
    public CreditOfflineChargeBuilder withOfflineAuthCode(String code) {
        this.offlineAuthCode = code;
        return this;
    }
    public CreditOfflineChargeBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        this.directMarketData = directMarketData;
        return this;
    }

    public CreditOfflineChargeBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsTransaction execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("CreditOfflineSale");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");
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
        if(token != null)
            cardData.append(service.hydrateTokenData(token, cardPresent, readerPresent));
        if(trackData != null) {
            block1.append(service.hydrateTrackData(trackData));
            if(trackData.getEncryptionData() != null)
                cardData.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        if(paymentData != null) {
            Element manualEntry = Et.element("ManualEntry");
            Et.subElement(manualEntry, "CardNbr").text(paymentData.getApplicationPrimaryAccountNumber());
            String expDate = paymentData.getApplicationExpirationDate();
            Et.subElement(manualEntry, "ExpMonth").text(expDate.substring(2, 4));
            Et.subElement(manualEntry, "ExpYear").text(expDate.substring(0, 2));
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
        if(directMarketData != null)
            block1.append(service.hydrateDirectMarketData(directMarketData));
        Et.subElement(block1, "OfflineAuthCode").text(offlineAuthCode);
        if(tagData != null) {
            block1.append(service.hydrateTagData(tagData));
        }
        if(emvData != null) {
            block1.append(service.hydrateEMVData(emvData));
        }
        String clientTransactionId = service.getClientTxnId(details);
        ElementTree response = service.submitTransaction(transaction, clientTransactionId);
        HpsTransaction trans = new HpsTransaction().fromElementTree(response);
        trans.setResponseCode("00");
        trans.setResponseText("");
        return trans;
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
        this.addValidation(new HpsBuilderValidation("onlyOnePaymentMethod", "Only one payment method is required."));
        this.addValidation(new HpsBuilderValidation("offlineAuthCodeIsNotNull", "Offline auth code is required."));
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

    private boolean offlineAuthCodeIsNotNull() {
        return (this.offlineAuthCode != null) || ((this.offlineAuthCode==null && tagData.getTagData().length() > 0) || (this.offlineAuthCode==null && emvData.getEmvTagData().length() > 0));
        
    }
}
