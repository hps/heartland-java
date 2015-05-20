package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsRefund;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class CreditRefundBuilder extends GatewayTransactionBuilder<CreditRefundBuilder, HpsRefund> {
    public CreditRefundBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditReturnReqType item = new PosCreditReturnReqType();
        CreditReturnReqBlock1Type block1 = new CreditReturnReqBlock1Type();

        block1.CardData = new CardDataType();
        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.CreditReturn = item;
    }

    @Override
    protected CreditRefundBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsRefund execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        HpsRefund refund = new HpsRefund(hydrateTransactionHeader(header));
        refund.setTransactionID(header.GatewayTxnId);
        refund.setResponseCode("00");
        refund.setResponseText("");

        return refund;
    }

    public CreditRefundBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.CreditReturn.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditRefundBuilder requestMultiuseToken() {
        transaction.CreditReturn.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditRefundBuilder allowDuplicates() {
        transaction.CreditReturn.Block1.AllowDup = Enums.booleanType.Y;
        return this;
    }

    public CreditRefundBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CreditReturn.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditRefundBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditReturn.Block1.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }

    public CreditRefundBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditReturn.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
