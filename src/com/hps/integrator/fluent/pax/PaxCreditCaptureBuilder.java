package com.hps.integrator.fluent.pax;

import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.CreditResponse;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.math.BigDecimal;

public class PaxCreditCaptureBuilder extends HpsBuilderAbstract<PaxDevice, CreditResponse> {
    Integer referenceNumber;
    BigDecimal amount;
    BigDecimal gratuity;
    Integer transactionId;

    public PaxCreditCaptureBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
    }
    public PaxCreditCaptureBuilder withGratuity(BigDecimal value) {
        this.gratuity = value;
        return this;
    }
    public PaxCreditCaptureBuilder withTransactionId(int value) {
        this.transactionId = value;
        return this;
    }
    public PaxCreditCaptureBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public PaxCreditCaptureBuilder(PaxDevice device) {
        super(device);
    }

    @Override
    public CreditResponse execute() throws HpsException {
        super.execute();

        AmountRequest amounts = new AmountRequest();
        amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));
        amounts.setTipAmount(HpsStringUtils.toNumeric(gratuity));

        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());

        ExtDataSubGroup extData = new ExtDataSubGroup();
        if (transactionId != null)
            extData.set(PaxExtData.HOST_REFERENCE_NUMBER, transactionId.toString());

        return service.doCredit(
                PaxTxnType.POSTAUTH,
                amounts,
                new AccountRequest(),
                trace,
                new AvsRequest(),
                new CashierSubGroup(),
                new CommercialRequest(),
                new EcomSubGroup(),
                extData
        );
    }

    @Override
    protected void setupValidations() {
        addValidation(new HpsBuilderValidation("transactionIdNotNull", "Transaction ID is required."));
    }

    private boolean transactionIdNotNull(){
        return transactionId != null;
    }
}
