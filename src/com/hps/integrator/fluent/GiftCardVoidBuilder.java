package com.hps.integrator.fluent;

import com.hps.integrator.entities.gift.HpsGiftCardResponse;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.services.fluent.HpsFluentGiftService;

public class GiftCardVoidBuilder extends HpsBuilderAbstract<HpsFluentGiftService, HpsGiftCardResponse> {
    Integer transactionId;

    public GiftCardVoidBuilder withTransactionId(Integer value) {
        this.transactionId = value;
        return this;
    }

    public GiftCardVoidBuilder(HpsFluentGiftService service) {
        super(service);
    }

    @Override
    public HpsGiftCardResponse execute() throws HpsException {
        super.execute();

        Element transaction = Et.element("GiftCardVoid");
        Element block1 = Et.subElement(transaction, "Block1");
        Et.subElement(block1, "GatewayTxnId").text(transactionId.toString());

        ElementTree response = service.submitTransaction(transaction);
        return new HpsGiftCardResponse().fromElementTree(response);
    }

    @Override
    protected void setupValidations() throws HpsException {
        this.addValidation(new HpsBuilderValidation("transactionIDIsNotNull", "Transaction ID is required."));
    }

    private boolean transactionIDIsNotNull(){
        return this.transactionId != null;
    }
}
