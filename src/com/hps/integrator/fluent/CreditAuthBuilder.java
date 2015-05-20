package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class CreditAuthBuilder extends GatewayTransactionBuilder<CreditAuthBuilder, HpsAuthorization> {
    public CreditAuthBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditAuthReqType item = new PosCreditAuthReqType();
        CreditAuthReqBlock1Type block1 = new CreditAuthReqBlock1Type();

        block1.CardData = new CardDataType();
        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.CreditAuth = item;
    }

    @Override
    protected CreditAuthBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAuthorization execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType authResponse = resp.Ver10.Transaction.CreditAuth;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, authResponse.RspCode, authResponse.RspText);

        HpsAuthorization auth = new HpsAuthorization(hydrateTransactionHeader(header));

        auth.setTransactionID(header.GatewayTxnId);
        auth.setAvsResultCode(authResponse.AVSRsltCode);
        auth.setAvsResultText(authResponse.AVSRsltText);
        auth.setCvvResultCode(authResponse.CVVRsltCode);
        auth.setCvvResultText(authResponse.CVVRsltText);
        auth.setAuthorizationCode(authResponse.AuthCode);
        auth.setAuthorizedAmount(authResponse.AuthAmt);
        auth.setReferenceNumber(authResponse.RefNbr);
        auth.setResponseCode(authResponse.RspCode);
        auth.setResponseText(authResponse.RspText);
        auth.setCardType(authResponse.CardType);
        auth.setCpcIndicator(authResponse.CPCInd);

        if (header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(header.TokenData.TokenValue);
            auth.setTokenData(tokenData);
        }

        return auth;
    }

    public CreditAuthBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.CreditAuth.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditAuthBuilder requestMultiuseToken() {
        transaction.CreditAuth.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditAuthBuilder allowDuplicates() {
        transaction.CreditAuth.Block1.AllowDup = Enums.booleanType.Y;
        return this;
    }

    public CreditAuthBuilder withDescriptor(String descriptor) {
        transaction.CreditAuth.Block1.TxnDescriptor = descriptor;
        return this;
    }

    public CreditAuthBuilder allowPartialAuth() {
        transaction.CreditAuth.Block1.AllowPartialAuth = Enums.booleanType.Y;
        return this;
    }

    public CreditAuthBuilder withGratuity(BigDecimal gratuity) {
        transaction.CreditAuth.Block1.GratuityAmtInfo = gratuity;
        return this;
    }

    public CreditAuthBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CreditAuth.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditAuthBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditAuth.Block1.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }

    public CreditAuthBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditAuth.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
