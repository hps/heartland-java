package com.hps.integrator.fluent;

public class DebitAddValueBuilder {} // extends GatewayTransactionBuilder<DebitAddValueBuilder, HpsAuthorization> {
//    public DebitAddValueBuilder(IHpsServicesConfig config, BigDecimal amount, String trackData, String pinBlock) {
//        super(config);
//
//        transaction = new PosRequestVer10Transaction();
//        PosDebitAddValueReqType item = new PosDebitAddValueReqType();
//        DebitAddValueReqBlock1Type block1 = new DebitAddValueReqBlock1Type();
//
//        block1.AllowDup = Enums.booleanType.N;
//        block1.Amt = amount;
//        block1.TrackData = trackData;
//        block1.PinBlock = pinBlock;
//
//        item.Block1 = block1;
//        transaction.DebitAddValue = item;
//    }
//
//    @Override
//    protected DebitAddValueBuilder getBuilder() {
//        return this;
//    }
//
//    @Override
//    public HpsAuthorization execute() throws HpsException, HpsCheckException {
//        PosResponse resp = doTransaction();
//        HpsGatewayResponseValidation.checkGatewayResponse(resp);
//
//        PosResponseVer10Header header = resp.Ver10.Header;
//        AuthRspStatusType addValueResponse = resp.Ver10.Transaction.DebitAddValue;
//        HpsIssuerResponseValidation.checkIssuerResponse(header.GatewayTxnId, addValueResponse.RspCode, addValueResponse.RspText);
//
//        return hydrateAuthorization(header, addValueResponse);
//    }
//
//    public DebitAddValueBuilder withCardHolder(HpsCardHolder cardHolder) {
//        transaction.DebitAddValue.Block1.CardHolderData = hydrateCardHolderData(cardHolder);
//        return this;
//    }
//
//    public DebitAddValueBuilder allowDuplicates() {
//        transaction.DebitAddValue.Block1.AllowDup = Enums.booleanType.Y;
//        return this;
//    }
//
//    public DebitAddValueBuilder withAdditionalTransactionFields(HpsTransactionDetails details) {
//        transaction.DebitAddValue.Block1.AdditionalTxnFields = hydrateAdditionalTxnFields(details);
//        return this;
//    }
//
//    public DebitAddValueBuilder withEncryptionData(HpsEncryptionData encryptionData) {
//        transaction.DebitAddValue.Block1.EncryptionData = hydrateEncryptionData(encryptionData);
//        return this;
//    }
//}
