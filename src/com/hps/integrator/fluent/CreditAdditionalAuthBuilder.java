package com.hps.integrator.fluent;

import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.services.fluent.HpsFluentCreditService;

import java.math.BigDecimal;

public class CreditAdditionalAuthBuilder extends HpsBuilderAbstract<HpsFluentCreditService, HpsAuthorization> {
    private BigDecimal amount;
    private Integer transactionId;
    private boolean allowDuplicates = false;

    public CreditAdditionalAuthBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public CreditAdditionalAuthBuilder withAllowDuplicates(boolean value) {
        this.allowDuplicates = value;
        return this;
    }
    public CreditAdditionalAuthBuilder withTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public CreditAdditionalAuthBuilder(HpsFluentCreditService service) {
        super(service);
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        super.execute();

        HpsInputValidation.checkAmount(amount);

        Element transaction = Et.element("CreditAdditionalAuth");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());
        Et.subElement(block1, "Amt").text(amount.toString());
        Et.subElement(block1, "AllowDup").text(allowDuplicates ? "Y" : "N");

        ElementTree response = service.submitTransaction(transaction);
        return new HpsAuthorization().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount is required."));
    }

    private boolean amountIsNotNull(){
        return this.amount != null;
    }
}
