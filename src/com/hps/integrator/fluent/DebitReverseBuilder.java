package com.hps.integrator.fluent;

import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.debit.HpsDebitAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentDebitService;

import java.math.BigDecimal;

public class DebitReverseBuilder extends HpsBuilderAbstract<HpsFluentDebitService, HpsDebitAuthorization> {
    BigDecimal amount;
    BigDecimal authorizedAmount;
    HpsTransactionDetails details;
    HpsTrackData trackData;
    Integer transactionId;

    public DebitReverseBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public DebitReverseBuilder withAuthorizedAmount(BigDecimal value) {
        this.authorizedAmount = value;
        return this;
    }
    public DebitReverseBuilder withDetails(HpsTransactionDetails value) {
        this.details = value;
        return this;
    }
    public DebitReverseBuilder withTrackData(HpsTrackData value) {
        this.trackData = value;
        return this;
    }
    public DebitReverseBuilder withTransactionId(Integer value) {
        this.transactionId = value;
        return this;
    }

    public DebitReverseBuilder(HpsFluentDebitService service) {
        super(service);
    }

    @Override
    public HpsDebitAuthorization execute() throws HpsException {
        super.execute();

        HpsInputValidation.checkAmount(amount);

        Element transaction = Et.element("DebitReversal");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "Amt").text(amount.toString());

        if(authorizedAmount != null)
            Et.subElement(block1, "AuthAmt").text(authorizedAmount.toString());

        if(trackData != null) {
            Et.subElement(block1, "TrackData").text(trackData.getValue());
            if(trackData.getEncryptionData() != null)
                block1.append(service.hydrateEncryptionData(trackData.getEncryptionData()));
        }
        if(transactionId != null)
            Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());

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
        if(transactionId != null) count++;

        return count == 1;
    }
}
