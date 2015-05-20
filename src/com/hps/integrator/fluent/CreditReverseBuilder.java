package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsReversal;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class CreditReverseBuilder extends GatewayTransactionBuilder<CreditReverseBuilder, HpsReversal> {
    public CreditReverseBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditReversalReqType item = new PosCreditReversalReqType();
        CreditReversalReqBlock1Type block1 = new CreditReversalReqBlock1Type();

        block1.CardData = new CardDataType();
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.CreditReversal = item;
    }

    @Override
    protected CreditReverseBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsReversal execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType respReversal = resp.Ver10.Transaction.CreditReversal;
        HpsReversal reversal = new HpsReversal(hydrateTransactionHeader(header));

        reversal.setTransactionID(header.GatewayTxnId);
        reversal.setAvsResultCode(respReversal.AVSRsltCode);
        reversal.setAvsResultText(respReversal.AVSRsltText);
        reversal.setCpcIndicator(respReversal.CPCInd);
        reversal.setCvvResultCode(respReversal.CVVRsltCode);
        reversal.setCvvResultText(respReversal.CVVRsltText);
        reversal.setReferenceNumber(respReversal.RefNbr);
        reversal.setResponseCode(respReversal.RspCode);
        reversal.setResponseText(respReversal.RspText);

        return reversal;
    }

    public CreditReverseBuilder requestMultiuseToken() {
        transaction.CreditReversal.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditReverseBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CreditReversal.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditReverseBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditReversal.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }

    public CreditReverseBuilder withAuthorizedAmount(BigDecimal authorizedAmount) {
        transaction.CreditReversal.Block1.AuthAmt = authorizedAmount;
        return this;
    }
}
