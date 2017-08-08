package com.hps.integrator.entities;

import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The HPS transaction.
 */
public class HpsTransaction {
    private HpsTransactionHeader mHeader;
    private int mTransactionID;
    private String mResponseCode;
    private String mResponseText;
    private String mReferenceNumber;
    private String mClientTransactionId;
    private String mEmvIssuerResp;
    
    public HpsTransaction() {}
    
    public String getEmvIssuerResp() {
		return mEmvIssuerResp;
	}
	public void setEmvIssuerResp(String emvIssuerResp) {
		this.mEmvIssuerResp = emvIssuerResp;
	}

	public HpsTransaction(HpsTransactionHeader header) {
        mHeader = header;
    }

    protected HpsTransactionHeader getHeader() {
        return mHeader;
    }

    protected void setHeader(HpsTransactionHeader header) {
        mHeader = header;
    }

    public int getTransactionID() {
        return mTransactionID;
    }

    public void setTransactionID(int transactionID) {
        this.mTransactionID = transactionID;
    }

    public String getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(String responseCode) {
        this.mResponseCode = responseCode;
    }

    public String getResponseText() {
        return mResponseText;
    }

    public void setResponseText(String responseText) {
        this.mResponseText = responseText;
    }

    public String getReferenceNumber() {
        return mReferenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.mReferenceNumber = referenceNumber;
    }

    public String getClientTransactionId() {
        return mClientTransactionId;
    }

    public void setClientTransactionId(String clientTransactionId) {
        this.mClientTransactionId = clientTransactionId;
    }

    public static String transactionTypeToServiceName(HpsTransactionType transactionType) {
        switch (transactionType) {
            case Authorize:
                return "CreditAuth";
            case Capture:
                return "CreditAddToBatch";
            case Charge:
                return "CreditSale";
            case Refund:
                return "CreditReturn";
            case Reverse:
                return "CreditReversal";
            case Verify:
                return "CreditAccountVerify";
            case List:
                return "ReportActivity";
            case Get:
                return "ReportTxnDetail";
            case Void:
                return "CreditVoid";
            case BatchClose:
                return "BatchClose";
            case SecurityError:
                return "SecurityError";
            default:
                return "";
        }
    }

    public static HpsTransactionType serviceNameToTransactionType(String serviceName) {
        if (serviceName.equals("CreditAddToBatch")) {
            return HpsTransactionType.Capture;
        } else if (serviceName.equals("CreditSale")) {
            return HpsTransactionType.Charge;
        } else if (serviceName.equals("CreditReturn")) {
            return HpsTransactionType.Refund;
        } else if (serviceName.equals("CreditReversal")) {
            return HpsTransactionType.Reverse;
        } else if (serviceName.equals("CreditAuth")) {
            return HpsTransactionType.Authorize;
        } else if (serviceName.equals("CreditAccountVerify")) {
            return HpsTransactionType.Verify;
        } else if (serviceName.equals("ReportActivity")) {
            return HpsTransactionType.List;
        } else if (serviceName.equals("ReportTxnDetail")) {
            return HpsTransactionType.Get;
        } else if (serviceName.equals("CreditVoid")) {
            return HpsTransactionType.Void;
        } else if (serviceName.equals("BatchClose")) {
            return HpsTransactionType.BatchClose;
        } else if (serviceName.equals("SecurityError")) {
            return HpsTransactionType.SecurityError;
        } else {
            return null;
        }
    }

    public HpsTransaction fromElementTree(ElementTree rsp){
        Element header = rsp.get("Header");
        Date date = null;
        if(header.has("RspDt")) {
            try {
                date = new SimpleDateFormat("YYmmddTHHMMSS").parse(header.getString("RspDt"));
            } catch(ParseException e) { date = null; }
        }

        String clientTransactionId = null;
        if(header.has("ClientTxnId"))
            clientTransactionId = header.getString("ClientTxnId");

        this.setHeader(new HpsTransactionHeader(
                header.getInt("GatewayRspCode"),
                header.getString("GatewayRspMsg"),
                date,
                clientTransactionId
        ));

        this.setTransactionID(header.getInt("GatewayTxnId"));
        this.setClientTransactionId(clientTransactionId);

        Element transaction = rsp.get("Transaction");
        if (transaction != null) {
            Element item = transaction.firstChild();
            if (item != null) {
                if (item.has("RspCode")) {
                    this.setResponseCode(item.getString("RspCode"));
                }
                if (item.has("RspText")) {
                    this.setResponseText(item.getString("RspText"));
                }
                if (item.has("RefNbr")) {
                    this.setReferenceNumber(item.getString("RefNbr"));
                }
                if (item.has("EMVIssuerResp")) {
                    this.setEmvIssuerResp(item.getString("EMVIssuerResp"));
                }
            }
        }

        return this;
    }
}