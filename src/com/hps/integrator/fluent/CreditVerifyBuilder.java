package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.credit.HpsAccountVerify;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class CreditVerifyBuilder extends GatewayTransactionBuilder<CreditVerifyBuilder, HpsAccountVerify> {
    public CreditVerifyBuilder(IHpsServicesConfig config) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditAccountVerifyReqType item = new PosCreditAccountVerifyReqType();
        CreditAccountVerifyBlock1Type block1 = new CreditAccountVerifyBlock1Type();

        block1.CardData = new CardDataType();

        item.Block1 = block1;
        transaction.CreditAccountVerify = item;
    }

    @Override
    protected CreditVerifyBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAccountVerify execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        AuthRspStatusType creditVerifyRsp = resp.Ver10.Transaction.CreditAccountVerify;
        HpsAccountVerify accountVerify = new HpsAccountVerify(hydrateTransactionHeader(resp.Ver10.Header));

        accountVerify.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        accountVerify.setAvsResultCode(creditVerifyRsp.AVSRsltCode);
        accountVerify.setAvsResultText(creditVerifyRsp.AVSRsltText);
        accountVerify.setReferenceNumber(creditVerifyRsp.RefNbr);
        accountVerify.setResponseCode(creditVerifyRsp.RspCode);
        accountVerify.setResponseText(creditVerifyRsp.RspText);
        accountVerify.setCardType(creditVerifyRsp.CardType);
        accountVerify.setCpcIndicator(creditVerifyRsp.CPCInd);
        accountVerify.setCvvResultCode(creditVerifyRsp.CVVRsltCode);
        accountVerify.setCvvResultText(creditVerifyRsp.CVVRsltText);
        accountVerify.setAuthorizationCode(creditVerifyRsp.AuthCode);
        accountVerify.setAuthorizedAmount(creditVerifyRsp.AuthAmt);

        if(resp.Ver10.Header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(resp.Ver10.Header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(resp.Ver10.Header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(resp.Ver10.Header.TokenData.TokenValue);
            accountVerify.setTokenData(tokenData);
        }

        HpsIssuerResponseValidation.checkIssuerResponse(accountVerify.getTransactionID(),
                accountVerify.getResponseCode(), accountVerify.getResponseText());

        return accountVerify;
    }

    public CreditVerifyBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.CreditAccountVerify.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditVerifyBuilder withRequestMultiuseToken(boolean requestMultiUseToken) {
        transaction.CreditAccountVerify.Block1.CardData.TokenRequest = requestMultiUseToken ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }

    public CreditVerifyBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditAccountVerify.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }

    public CreditVerifyBuilder withAuthorizedAmount(BigDecimal authorizedAmount) {
        transaction.CreditReversal.Block1.AuthAmt = authorizedAmount;
        return this;
    }
}
