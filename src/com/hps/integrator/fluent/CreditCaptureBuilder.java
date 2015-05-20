package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.PosCreditAddToBatchReqType;
import PosGateway.Exchange.Hps.PosRequestVer10Transaction;
import PosGateway.Exchange.Hps.PosResponse;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.credit.HpsReportTransactionDetails;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.services.HpsCreditService;

import java.math.BigDecimal;

public class CreditCaptureBuilder extends GatewayTransactionBuilder<CreditCaptureBuilder, HpsReportTransactionDetails> {
    public CreditCaptureBuilder(IHpsServicesConfig config, int transactionId) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCreditAddToBatchReqType item = new PosCreditAddToBatchReqType();
        item.GatewayTxnId = transactionId;
        transaction.CreditAddToBatch = item;
    }

    @Override
    protected CreditCaptureBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsReportTransactionDetails execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        return new HpsCreditService(servicesConfig).get(resp.Ver10.Header.GatewayTxnId);
    }

    public CreditCaptureBuilder withAmount(BigDecimal amount) {
        transaction.CreditAddToBatch.Amt = amount;
        return this;
    }

    public CreditCaptureBuilder withGratuity(BigDecimal gratuity) {
        transaction.CreditAddToBatch.GratuityAmtInfo = gratuity;
        return this;
    }

    public CreditCaptureBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditAddToBatch.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }
}
