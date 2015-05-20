package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class CreditChargeBuilder extends GatewayTransactionBuilder<CreditChargeBuilder, HpsCharge> {
    public CreditChargeBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditSaleReqType item = new PosCreditSaleReqType();
        CreditSaleReqBlock1Type block1 = new CreditSaleReqBlock1Type();

        block1.CardData = new CardDataType();
        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.CreditSale = item;
    }

    @Override
    protected CreditChargeBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsCharge execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType creditSaleRsp = resp.Ver10.Transaction.CreditSale;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, creditSaleRsp.RspCode, creditSaleRsp.RspText);

        HpsCharge charge = new HpsCharge(hydrateTransactionHeader(resp.Ver10.Header));

        charge.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        charge.setAuthorizedAmount(creditSaleRsp.AuthAmt);
        charge.setAuthorizationCode(creditSaleRsp.AuthCode);
        charge.setAvsResultCode(creditSaleRsp.AVSRsltCode);
        charge.setAvsResultText(creditSaleRsp.AVSRsltText);
        charge.setCardType(creditSaleRsp.CardType);
        charge.setCpcIndicator(creditSaleRsp.CPCInd);
        charge.setCvvResultCode(creditSaleRsp.CVVRsltCode);
        charge.setCvvResultText(creditSaleRsp.CVVRsltText);
        charge.setReferenceNumber(creditSaleRsp.RefNbr);
        charge.setResponseCode(creditSaleRsp.RspCode);
        charge.setResponseText(creditSaleRsp.RspText);

        if (resp.Ver10.Header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(resp.Ver10.Header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(resp.Ver10.Header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(resp.Ver10.Header.TokenData.TokenValue);
            charge.setTokenData(tokenData);
        }

        return charge;
    }

    public CreditChargeBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.CreditSale.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditChargeBuilder withRequestMultiuseToken(boolean requestMultiuseToken) {
        transaction.CreditSale.Block1.CardData.TokenRequest = requestMultiuseToken ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }

    public CreditChargeBuilder withAllowDuplicates(boolean allowDuplicates) {
        transaction.CreditSale.Block1.AllowDup = allowDuplicates ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }

    public CreditChargeBuilder withDescriptor(String descriptor) {
        transaction.CreditSale.Block1.TxnDescriptor = descriptor;
        return this;
    }

    public CreditChargeBuilder withAllowPartialAuth(boolean allowPartialAuth) {
        transaction.CreditSale.Block1.AllowPartialAuth = allowPartialAuth ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }

    public CreditChargeBuilder withGratuity(BigDecimal gratuity) {
        transaction.CreditSale.Block1.GratuityAmtInfo = gratuity;
        return this;
    }

    public CreditChargeBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CreditSale.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditChargeBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditSale.Block1.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }

    public CreditChargeBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditSale.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }

    public CreditChargeBuilder withCpcReq(boolean cpcRequested) {
        transaction.CreditSale.Block1.CPCReq = cpcRequested ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }
}
