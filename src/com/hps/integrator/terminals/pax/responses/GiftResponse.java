package com.hps.integrator.terminals.pax.responses;

import com.hps.integrator.infrastructure.HpsMessageException;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxMsgId;
import com.hps.integrator.infrastructure.utils.MessageReader;
import com.hps.integrator.terminals.pax.subgroups.*;

import java.math.BigDecimal;

public class GiftResponse extends PaxDeviceResponse {
    private BigDecimal balanceAmount;

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public GiftResponse(byte[] buffer) throws HpsMessageException {
        super(buffer, PaxMsgId.T07_RSP_DO_GIFT, PaxMsgId.T09_RSP_DO_LOYALTY);
    }

    @Override
    protected void parseResponse(MessageReader br) throws HpsMessageException {
        super.parseResponse(br);

        if (deviceResponseCode.equals("000000")) {
            hostResponse = new HostResponse(br);
            transactionType = br.readToCode(ControlCodes.FS);
            amountResponse = new AmountResponse(br);
            accountResponse = new AccountResponse(br);
            traceResponse = new TraceResponse(br);
            extDataResponse = new ExtDataSubGroup(br);

            mapResponse();
        }
    }

    @Override
    protected void mapResponse() {
        super.mapResponse();

        if(amountResponse != null) {
            balanceAmount = amountResponse.getBalance1();
        }
    }

}
