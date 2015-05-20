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

public class CreditRecurringBuilder extends GatewayTransactionBuilder<CreditRecurringBuilder, HpsAuthorization> {

    public CreditRecurringBuilder(IHpsServicesConfig config, BigDecimal amount) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosRecurringBillReqType item = new PosRecurringBillReqType();
        RecurringBillReqBlock1Type block1 = new RecurringBillReqBlock1Type();

        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.RecurringData = new RecurringDataType();
        block1.RecurringData.OneTime = Enums.booleanType.N;

        item.Block1 = block1;
        transaction.RecurringBilling = item;
    }

    public CreditRecurringBuilder(IHpsServicesConfig config, BigDecimal amount, String scheduleId) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosRecurringBillReqType item = new PosRecurringBillReqType();
        RecurringBillReqBlock1Type block1 = new RecurringBillReqBlock1Type();

        block1.AllowDup = Enums.booleanType.fromString("N");
        block1.Amt = amount;
        block1.RecurringData = new RecurringDataType();
        block1.RecurringData.ScheduleID = scheduleId;
        block1.RecurringData.OneTime = Enums.booleanType.N;

        item.Block1 = block1;
        transaction.RecurringBilling = item;
    }

    @Override
    protected CreditRecurringBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsAuthorization execute() throws HpsException, HpsCheckException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosResponseVer10Header header = resp.Ver10.Header;
        AuthRspStatusType recurringRsp = resp.Ver10.Transaction.RecurringBilling;
        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, recurringRsp.RspCode, recurringRsp.RspText);

        return hydrateAuthorization(header, recurringRsp);
    }

    public CreditRecurringBuilder oneTime() {
        transaction.RecurringBilling.Block1.RecurringData.OneTime = Enums.booleanType.Y;
        return this;
    }

    public CreditRecurringBuilder withCardHolder(HpsCardHolder cardHolder) {
        transaction.RecurringBilling.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
        return this;
    }

    public CreditRecurringBuilder requestMultiuseToken() {
        transaction.RecurringBilling.Block1.CardData.TokenRequest = Enums.booleanType.Y;
        return this;
    }

    public CreditRecurringBuilder allowDuplicates() {
        transaction.RecurringBilling.Block1.AllowDup = Enums.booleanType.Y;
        return this;
    }

    public CreditRecurringBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.RecurringBilling.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CreditRecurringBuilder withEncryptionData(HpsEncryptionData encryptionData) {
        transaction.RecurringBilling.Block1.CardData.EncryptionData = hydrateEncryptionData(encryptionData);
        return this;
    }
}
