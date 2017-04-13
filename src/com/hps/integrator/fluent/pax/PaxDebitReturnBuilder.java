package com.hps.integrator.fluent.pax;


import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.fluent.HpsBuilderValidation;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.PaxExtData;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.DebitResponse;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.math.BigDecimal;

public class PaxDebitReturnBuilder extends HpsBuilderAbstract<PaxDevice, DebitResponse> {
        boolean allowDuplicates = false;
        BigDecimal amount;
        Integer referenceNumber;
        Integer transactionId;

public PaxDebitReturnBuilder withTransactionId(int transactionId) {
        this.transactionId = transactionId;
        return this;
        }
public PaxDebitReturnBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
        }

public PaxDebitReturnBuilder withAmount(BigDecimal value) {
        this.amount = value;
        return this;
        }
public PaxDebitReturnBuilder(PaxDevice device) {
        super(device);
        }

@Override
public DebitResponse execute() throws HpsException {
    super.execute();
    AmountRequest amounts = new AmountRequest();
    amounts.setTransactionAmount(HpsStringUtils.toNumeric(amount));

    AccountRequest account = new AccountRequest();
    TraceRequest trace = new TraceRequest();
    trace.setReferenceNumber(referenceNumber.toString());

    CashierSubGroup cashier = new CashierSubGroup();

    // Additional Info sub group
    ExtDataSubGroup extData = new ExtDataSubGroup();
    if (transactionId != null)
        extData.set(PaxExtData.HOST_REFERENCE_NUMBER, transactionId.toString());
    return service.DoDebit(PaxTxnType.RETURN, amounts, account, trace, cashier, extData);
}
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
    }
    private boolean amountIsNotNull(){
        return this.amount != null;
    }
}
