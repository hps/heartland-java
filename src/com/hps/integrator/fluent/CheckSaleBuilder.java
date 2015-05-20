package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransactionDetails;
import com.hps.integrator.entities.check.HpsCheckHolder;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class CheckSaleBuilder extends GatewayTransactionBuilder<CheckSaleBuilder, HpsCheckResponse> {
    public CheckSaleBuilder(IHpsServicesConfig config, Enums.checkActionType action) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCheckSaleReqType item = new PosCheckSaleReqType();
        CheckSaleReqBlock1Type block1 = new CheckSaleReqBlock1Type();

        block1.AccountInfo = new AccountInfoType();
        block1.CheckAction = action;

        item.Block1 = block1;
        transaction.CheckSale = item;
    }

    @Override
    protected CheckSaleBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsCheckResponse execute() throws HpsException, HpsCheckException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosCheckSaleRspType saleRsp = resp.Ver10.Transaction.CheckSale;
        if (saleRsp.RspCode != 0) {
            throw new HpsCheckException(resp.Ver10.Header.GatewayTxnId, getResponseDetails(saleRsp.CheckRspInfo),
                    saleRsp.RspCode, saleRsp.RspMessage);
        }

        HpsCheckResponse response = new HpsCheckResponse(hydrateTransactionHeader(resp.Ver10.Header));
        response.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        response.setAuthorizationCode(saleRsp.AuthCode);
        response.setResponseCode(Integer.toString(saleRsp.RspCode));
        response.setResponseText(saleRsp.RspMessage);

        response.setDetails(getResponseDetails(saleRsp.CheckRspInfo));

        return response;
    }

    public CheckSaleBuilder withAccountNumber(String accountNumber) {
        transaction.CheckSale.Block1.AccountInfo.AccountNumber = accountNumber;
        return this;
    }

    public CheckSaleBuilder withRoutingNumber(String routingNumber) {
        transaction.CheckSale.Block1.AccountInfo.RoutingNumber = routingNumber;
        return this;
    }

    public CheckSaleBuilder withCheckNumber(String checkNumber) {
        transaction.CheckSale.Block1.AccountInfo.CheckNumber = checkNumber;
        return this;
    }

    public CheckSaleBuilder withMicrData(String micrData) {
        transaction.CheckSale.Block1.AccountInfo.MICRData = micrData;
        return this;
    }

    public CheckSaleBuilder withAccountType(Enums.accountTypeType accountType) {
        transaction.CheckSale.Block1.AccountInfo.AccountType = accountType;
        return this;
    }

    public CheckSaleBuilder withDataEntryMode(Enums.dataEntryModeType dataEntryMode) {
        transaction.CheckSale.Block1.DataEntryMode = dataEntryMode;
        return this;
    }

    public CheckSaleBuilder withCheckType(Enums.checkTypeType checkType) {
        transaction.CheckSale.Block1.CheckType = checkType;
        return this;
    }

    public CheckSaleBuilder withAmount(BigDecimal amount) {
        transaction.CheckSale.Block1.Amt = amount;
        return this;
    }

    public CheckSaleBuilder withSecCode(String secCode) {
        transaction.CheckSale.Block1.SECCode = secCode;
        return this;
    }

    public CheckSaleBuilder withCheckHolder(HpsCheckHolder checkHolder) {
        ConsumerInfoType consumer = new ConsumerInfoType();
        if(checkHolder.getAddress() != null) {
            consumer.Address1 = checkHolder.getAddress().getAddress();
            consumer.City = checkHolder.getAddress().getCity();
            consumer.State = checkHolder.getAddress().getState();
            consumer.Zip = checkHolder.getAddress().getZip();
        }
        
        consumer.CheckName = checkHolder.getCheckName();
        consumer.CourtesyCard = checkHolder.getCourtesyCard();
        consumer.DLNumber = checkHolder.getDlNumber();
        consumer.DLState = checkHolder.getDlState();
        consumer.EmailAddress = checkHolder.getEmail();
        consumer.FirstName = checkHolder.getFirstName();
        consumer.LastName = checkHolder.getLastName();
        consumer.PhoneNumber = checkHolder.getDlNumber();
        
        if(checkHolder.getDobYear() != null || checkHolder.getSsn4() != null) {
            consumer.IdentityInfo = new IdentityInfoType();
            if(checkHolder.getDobYear() != null) { consumer.IdentityInfo.DOBYear = checkHolder.getDobYear().toString(); }
            consumer.IdentityInfo.SSNL4 = checkHolder.getSsn4();
        }
        
        transaction.CheckSale.Block1.ConsumerInfo = consumer;
        
        return this;
    }

    public CheckSaleBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
        transaction.CheckSale.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
        return this;
    }

    public CheckSaleBuilder withPaymentMethodKey(String paymentMethodKey) {
        transaction.CheckSale.Block1.PaymentMethodKey = paymentMethodKey;
        return this;
    }

    public CheckSaleBuilder withTokenValue(String tokenValue) {
        transaction.CheckSale.Block1.TokenValue = tokenValue;
        return this;
    }

    public CheckSaleBuilder withRecurringData(RecurringDataType recurringData) {
        transaction.CheckSale.Block1.RecurringData = recurringData;
        return this;
    }
}
