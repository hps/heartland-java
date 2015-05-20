package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class DebitChargeBuilder extends GatewayTransactionBuilder<DebitChargeBuilder, HpsAuthorization> {
    public DebitChargeBuilder(IHpsServicesConfig config, BigDecimal amount, String trackData, String pinBlock) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosDebitSaleReqType item = new PosDebitSaleReqType();
        DebitSaleReqBlock1Type block1 = new DebitSaleReqBlock1Type();

        block1.AllowDup = Enums.booleanType.N;
        block1.Amt = amount;
        block1.TrackData = trackData;
        block1.PinBlock = pinBlock;

        item.Block1 = block1;
        transaction.DebitSale = item;
    }

    @Override
    protected DebitChargeBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAuthorization execute() throws HpsException, HpsCheckException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType saleResponse = resp.Ver10.Transaction.DebitSale;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, saleResponse.RspCode, saleResponse.RspText);

        return hydrateAuthorization(header, saleResponse);
    }

    public DebitChargeBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.DebitSale.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public DebitChargeBuilder allowDuplicates() {
        transaction.DebitSale.Block1.AllowDup = Enums.booleanType.Y;
        return this;
    }

    public DebitChargeBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.DebitSale.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public DebitChargeBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.DebitSale.Block1.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }

    public DebitChargeBuilder allowPartialAuth() {
        transaction.DebitSale.Block1.AllowPartialAuth = Enums.booleanType.Y;
        return this;
    }

    public DebitChargeBuilder withCashBackAmount(BigDecimal amount) {
        transaction.DebitSale.Block1.CashbackAmtInfo = amount;
        return this;
    }
}
