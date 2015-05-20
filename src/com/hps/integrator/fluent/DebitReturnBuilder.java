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

public class DebitReturnBuilder extends GatewayTransactionBuilder<DebitReturnBuilder, HpsAuthorization> {
    public DebitReturnBuilder(IHpsServicesConfig config, BigDecimal amount, String trackData, String pinBlock) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosDebitReturnReqType item = new PosDebitReturnReqType();
        DebitReturnReqBlock1Type block1 = new DebitReturnReqBlock1Type();

        block1.AllowDup = Enums.booleanType.N;
        block1.Amt = amount;
        block1.TrackData = trackData;
        block1.PinBlock = pinBlock;

        item.Block1 = block1;
        transaction.DebitReturn = item;
    }

    @Override
    protected DebitReturnBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAuthorization execute() throws HpsException, HpsCheckException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType returnResponse = resp.Ver10.Transaction.DebitReturn;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, returnResponse.RspCode, returnResponse.RspText);

        return hydrateAuthorization(header, returnResponse);
    }

    public DebitReturnBuilder withTransacitonId(int transacitonId) {
        transaction.DebitReturn.Block1.GatewayTxnId = transacitonId;
        return this;
    }

    public DebitReturnBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.DebitReturn.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public DebitReturnBuilder allowDuplicates() {
        transaction.DebitReturn.Block1.AllowDup = Enums.booleanType.Y;
        return this;
    }

    public DebitReturnBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.DebitReturn.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public DebitReturnBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.DebitReturn.Block1.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
