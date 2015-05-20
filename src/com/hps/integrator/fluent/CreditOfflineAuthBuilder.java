package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class CreditOfflineAuthBuilder extends GatewayTransactionBuilder<CreditOfflineAuthBuilder, HpsTransaction> {
    public CreditOfflineAuthBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditOfflineAuthReqType item = new PosCreditOfflineAuthReqType();
        CreditOfflineAuthReqBlock1Type block1 = new CreditOfflineAuthReqBlock1Type();

        block1.CardData = new CardDataType();
        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.CreditOfflineAuth = item;
    }

    @Override
    protected CreditOfflineAuthBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsTransaction execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;

        HpsTransaction result = new HpsTransaction(hydrateTransactionHeader(header));
        result.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        result.setResponseCode("00");
        result.setResponseText("");

        return result;
    }

    public CreditOfflineAuthBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.CreditOfflineAuth.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditOfflineAuthBuilder requestMultiuseToken() {
        transaction.CreditOfflineAuth.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditOfflineAuthBuilder withAllowDuplicates(boolean allowDuplicates) {
        transaction.CreditOfflineAuth.Block1.AllowDup = allowDuplicates ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }

    public CreditOfflineAuthBuilder withGratuity(BigDecimal gratuity) {
        transaction.CreditOfflineAuth.Block1.GratuityAmtInfo = gratuity;
        return this;
    }

    public CreditOfflineAuthBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CreditOfflineAuth.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditOfflineAuthBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditOfflineAuth.Block1.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }

    public CreditOfflineAuthBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditOfflineAuth.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }

    public CreditOfflineAuthBuilder withSurcharge(BigDecimal amount) {
        transaction.CreditOfflineAuth.Block1.SurchargeAmtInfo = amount;
        return this;
    }

    public CreditOfflineAuthBuilder withOfflineAuthCode(String offlineAuthCode) {
        transaction.CreditOfflineAuth.Block1.OfflineAuthCode = offlineAuthCode;
        return this;
    }

    public CreditOfflineAuthBuilder withCpcRequest(boolean cpcRequest) {
        transaction.CreditOfflineAuth.Block1.CPCReq = cpcRequest ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }
}
