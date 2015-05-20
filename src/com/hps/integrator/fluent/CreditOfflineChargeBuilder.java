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

public class CreditOfflineChargeBuilder extends GatewayTransactionBuilder<CreditOfflineChargeBuilder, HpsTransaction> {
    public CreditOfflineChargeBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditOfflineSaleReqType item = new PosCreditOfflineSaleReqType();
        CreditOfflineSaleReqBlock1Type block1 = new CreditOfflineSaleReqBlock1Type();

        block1.CardData = new CardDataType();
        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;

        item.Block1 = block1;
        transaction.CreditOfflineSale = item;
    }

    @Override
    protected CreditOfflineChargeBuilder getBuilder() {
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

    public CreditOfflineChargeBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.CreditOfflineSale.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditOfflineChargeBuilder requestMultiuseToken() {
        transaction.CreditOfflineSale.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditOfflineChargeBuilder allowDuplicates() {
        transaction.CreditOfflineSale.Block1.AllowDup = Enums.booleanType.Y;
        return this;
    }

    public CreditOfflineChargeBuilder withGratuity(BigDecimal gratuity) {
        transaction.CreditOfflineSale.Block1.GratuityAmtInfo = gratuity;
        return this;
    }

    public CreditOfflineChargeBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CreditOfflineSale.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditOfflineChargeBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditOfflineSale.Block1.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }

    public CreditOfflineChargeBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.CreditOfflineSale.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }

    public CreditOfflineChargeBuilder withSurcharge(BigDecimal amount) {
        transaction.CreditOfflineSale.Block1.SurchargeAmtInfo = amount;
        return this;
    }

    public CreditOfflineChargeBuilder withOfflineAuthCode(String offlineAuthCode) {
        transaction.CreditOfflineSale.Block1.OfflineAuthCode = offlineAuthCode;
        return this;
    }

    public CreditOfflineChargeBuilder withCpcRequest(boolean cpcRequest) {
        transaction.CreditOfflineSale.Block1.CPCReq = cpcRequest ? Enums.booleanType.Y : Enums.booleanType.N;
        return this;
    }
}
