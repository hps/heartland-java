package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

public class CreditBalanceInquiryBuilder extends GatewayTransactionBuilder<CreditBalanceInquiryBuilder, HpsAuthorization> {
    public CreditBalanceInquiryBuilder(IHpsServicesConfig config) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosPrePaidBalanceInquiryReqType item = new PosPrePaidBalanceInquiryReqType();

        item.Block1 = new PrePaidBalanceInquiryReqBlock1Type();
        item.Block1.CardData = new CardDataType();
        transaction.PrePaidBalanceInquiry = item;
    }

    @Override
    protected CreditBalanceInquiryBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType balanceInquiry = resp.Ver10.Transaction.PrePaidBalanceInquiry;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, balanceInquiry.RspCode, balanceInquiry.RspText);

        return hydrateAuthorization(header, balanceInquiry);
    }

    public CreditBalanceInquiryBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.PrePaidBalanceInquiry.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditBalanceInquiryBuilder requestMultiuseToken() {
        transaction.PrePaidBalanceInquiry.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditBalanceInquiryBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.PrePaidBalanceInquiry.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
