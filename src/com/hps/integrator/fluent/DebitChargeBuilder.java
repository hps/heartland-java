package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.debit.HpsDebitAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentDebitService;

import java.math.BigDecimal;

public class DebitChargeBuilder extends HpsBuilderAbstract<HpsFluentDebitService, HpsDebitAuthorization> {
    boolean allowDuplicates = false;
    boolean allowPartialAuth = false;
    BigDecimal amount;
    HpsCardHolder cardHolder;
    BigDecimal cashBack;
    String pinBlock;
    String token;
    HpsTrackData trackData;
    HpsTransactionDetails details;

    public DebitChargeBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public DebitChargeBuilder withAllowPartialAuth(boolean value) {
        this.allowPartialAuth = value;
        return this;
    }
    public DebitChargeBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public DebitChargeBuilder withCardHolder(HpsCardHolder value) {
        this.cardHolder = value;
        return this;
    }
    public DebitChargeBuilder withCashBack(BigDecimal value) {
        this.cashBack = value;
        return this;
    }
    public DebitChargeBuilder withPinBlock(String value) {
        this.pinBlock = value;
        return this;
    }
    public DebitChargeBuilder withToken(String value) {
        this.token = value;
        return this;
    }
    public DebitChargeBuilder withTrackData(HpsTrackData value) {
        this.trackData = value;
        return this;
    }
    public DebitChargeBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }

    public DebitChargeBuilder(HpsFluentDebitService service) {
        super(service);
    }

    @Override
    public HpsDebitAuthorization execute() throws HpsException {
        super.execute();

        HpsInputValidation.checkAmount(amount);

        Element transaction = Et.element("DebitSale");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");
        Et.subElement(block1, "AllowPartialAuth").text(allowPartialAuth ? "Y" : "N");

        if(trackData != null) {
            Et.subElement(block1, "TrackData").text(trackData.getValue());
            if(trackData.getEncryptionData() != null)
                block1.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        if(token != null)
            Et.subElement(block1, "TokenData").text(token);

        if(pinBlock != null)
            Et.subElement(block1, "PinBlock").text(pinBlock);

        if(cardHolder != null)
            block1.append(service.hydrateCardHolder(cardHolder));

        if(cashBack != null)
            Et.subElement(block1, "CashbackAmtInfo").text(cashBack.toString());

        if(details != null)
            block1.append(service.hydrateAdditionalTxnFields(details));

        String clientTxnId = service.getClientTxnId(details);
        return service.submitTransaction(transaction, clientTxnId);
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
        if(trackData != null) count++;
        if(token != null) count++;

        return count == 1;
    }
}
