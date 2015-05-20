package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class CreditAddValueBuilder extends GatewayTransactionBuilder<CreditAddValueBuilder, HpsAuthorization> {
    public CreditAddValueBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosPrePaidAddValueReqType item = new PosPrePaidAddValueReqType();

        item.Block1 = new PrePaidAddValueReqBlock1Type();
        item.Block1.CardData = new CardDataType();
        item.Block1.Amt = amount;

        transaction.PrePaidAddValue = item;
    }

    @Override
    protected CreditAddValueBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType balanceInquiry = resp.Ver10.Transaction.PrePaidAddValue;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, balanceInquiry.RspCode, balanceInquiry.RspText);

        return hydrateAuthorization(header, balanceInquiry);
    }

    public CreditAddValueBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.PrePaidAddValue.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditAddValueBuilder requestMultiuseToken() {
        transaction.PrePaidAddValue.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditAddValueBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.PrePaidAddValue.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
