package com.hps.integrator.fluent.pax;

import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.CreditResponse;
import com.hps.integrator.terminals.pax.subgroups.*;


public class PaxCreditVoidBuilder extends HpsBuilderAbstract<PaxDevice, CreditResponse> {
    Integer referenceNumber;
    Integer transactionId;

    public PaxCreditVoidBuilder withTransactionId(int value) {
        this.transactionId = value;
        return this;
    }
    public PaxCreditVoidBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public PaxCreditVoidBuilder(PaxDevice device) {
        super(device);
    }

    @Override
    public CreditResponse execute() throws HpsException {
        super.execute();

        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());

        ExtDataSubGroup extData = new ExtDataSubGroup();
        extData.set(PaxExtData.HOST_REFERENCE_NUMBER, transactionId.toString());

        return service.doCredit(PaxTxnType.VOID,
                new AmountRequest(),
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
