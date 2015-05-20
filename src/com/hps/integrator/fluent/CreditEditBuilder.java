package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.PosCreditTxnEditReqType;
import PosGateway.Exchange.Hps.PosRequestVer10Transaction;
import PosGateway.Exchange.Hps.PosResponse;
import PosGateway.Exchange.Hps.PosResponseVer10Header;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsDirectMarketData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

import java.math.BigDecimal;

public class CreditEditBuilder extends GatewayTransactionBuilder<CreditEditBuilder, HpsTransaction> {
    public CreditEditBuilder(IHpsServicesConfig config) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        transaction.CreditTxnEdit = new PosCreditTxnEditReqType();
    }

    @Override
    protected CreditEditBuilder getBuilder() {
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

    public CreditEditBuilder withGratuity(BigDecimal gratuity) {
        transaction.CreditTxnEdit.GratuityAmtInfo = gratuity;
        return this;
    }

    public CreditEditBuilder withAmount(BigDecimal amount) {
        transaction.CreditTxnEdit.Amt = amount;
        return this;
    }

    public CreditEditBuilder withDirectMarketData(HpsDirectMarketData directMarketData) {
        transaction.CreditTxnEdit.DirectMktData = hydrateDirectMktData(directMarketData);
        return this;
    }

    public CreditEditBuilder withSurchargeAmount(BigDecimal amount) {
        transaction.CreditTxnEdit.SurchargeAmtInfo = amount;
        return this;
    }
}
