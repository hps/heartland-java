package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.check.HpsCheckResponse;
import com.hps.integrator.infrastructure.HpsCheckException;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;

public class CheckVoidBuilder extends GatewayTransactionBuilder<CheckVoidBuilder, HpsCheckResponse> {
    public CheckVoidBuilder(IHpsServicesConfig config) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosCheckVoidReqType item = new PosCheckVoidReqType();

        item.Block1 = new CheckVoidReqBlock1Type();
        transaction.CheckVoid = item;
    }

    @Override
    protected CheckVoidBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsCheckResponse execute() throws HpsException, HpsCheckException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosCheckVoidRspType voidRsp = resp.Ver10.Transaction.CheckVoid;
        if (voidRsp.RspCode != 0) {
            throw new HpsCheckException(resp.Ver10.Header.GatewayTxnId, getResponseDetails(voidRsp.CheckRspInfo),
                    voidRsp.RspCode, voidRsp.RspMessage);
        }

        HpsCheckResponse response = new HpsCheckResponse(hydrateTransactionHeader(resp.Ver10.Header));
        response.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        response.setAuthorizationCode(voidRsp.AuthCode);
        response.setResponseCode(Integer.toString(voidRsp.RspCode));
        response.setResponseText(voidRsp.RspMessage);

        response.setDetails(getResponseDetails(voidRsp.CheckRspInfo));

        return response;
    }
}
