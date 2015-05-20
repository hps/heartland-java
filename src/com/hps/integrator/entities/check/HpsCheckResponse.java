package com.hps.integrator.entities.check;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HpsCheckResponse extends HpsTransaction {
    public HpsCheckResponse(HpsTransactionHeader header) {
        super(header);
    }

    private String authorizationCode;
    private String customerId;
    private List<HpsCheckResponseDetails> details;

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<HpsCheckResponseDetails> getDetails() {
        return details;
    }

    public void setDetails(List<HpsCheckResponseDetails> details) {
        this.details = details;
    }

    public static HpsCheckResponse fromElementTree(ElementTree rsp) {
        Element rspHeader = rsp.get("Header");

        int gatewayRspCode = rspHeader.getInt("GatewayRspCode");
        String gatewayRspMsg = rspHeader.getString("GatewayRspMsg");
        Date rspDT = rspHeader.getDate("RspDT");
        long clientTxnId = rspHeader.getLong("ClientTxnId");

        HpsTransactionHeader header = new HpsTransactionHeader(
                gatewayRspCode,
                gatewayRspMsg,
                rspDT,
                clientTxnId
        );
        HpsCheckResponse sale = new HpsCheckResponse(header);

        Element response = rsp.get("Transaction");
        sale.setResponseCode(response.getString("RspCode"));
        sale.setResponseText(response.getString("RspMessage"));
        sale.setAuthorizationCode(response.getString("AuthCode"));

        if(response.has("CheckRspInfo")){
            sale.details = new ArrayList<HpsCheckResponseDetails>();
            for(Element rspInfo : response.getAll("CheckRspInfo")){
                HpsCheckResponseDetails detail = new HpsCheckResponseDetails();
                detail.setMessageType(rspInfo.getString("Type"));
                detail.setCode(rspInfo.getString("Code"));
                detail.setMessage(rspInfo.getString("Message"));
                detail.setFieldNumber(rspInfo.getString("FieldNumber"));
                detail.setFieldNumber(rspInfo.getString("FieldName"));

                sale.details.add(detail);
            }
        }

        return sale;
    }
}
