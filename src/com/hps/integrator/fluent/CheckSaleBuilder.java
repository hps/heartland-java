package com.hps.integrator.fluent;

import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentCheckService;

import java.math.BigDecimal;

public class CheckSaleBuilder extends HpsBuilderAbstract<HpsFluentCheckService, HpsCheckResponse> {
    private Boolean achVerify;
    private BigDecimal amount;
    private HpsCheck check;
    private Boolean checkVerify;
    private String clientTransactionId;

    public CheckSaleBuilder withAchVerify(boolean value) {
        this.achVerify = value;
        return this;
    }
    public CheckSaleBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    public CheckSaleBuilder withCheck(HpsCheck check) {
        this.check = check;
        return this;
    }
    public CheckSaleBuilder withCheckVerify(boolean value) {
        this.checkVerify = value;
        return this;
    }
    public CheckSaleBuilder withClientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public CheckSaleBuilder(HpsFluentCheckService service) {
        super(service);
    }

    @Override
    public HpsCheckResponse execute() throws HpsException {
        super.execute();

        return this.service.buildTransaction("SALE", this.check, this.amount, this.clientTransactionId, this.checkVerify, this.achVerify);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("amountIsNotNull", "Amount cannot be null."));
        this.addValidation(new HpsBuilderValidation("checkIsNotNull", "Check cannot be null."));
    }

    private boolean amountIsNotNull() { return this.amount != null; }
    private boolean checkIsNotNull() { return this.check != null; }
}
