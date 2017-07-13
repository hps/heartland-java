package com.hps.integrator.entities.credit;

import com.hps.integrator.abstractions.IHpsReportTransaction;
import com.hps.integrator.entities.HpsTokenData;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.entities.HpsTransactionType;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HpsReportTransactionDetails extends HpsAuthorization implements
        IHpsReportTransaction {

    private BigDecimal settlementAmount;
    private int originalTransactionId;
    private String maskedCardNumber;
    private HpsTransactionType transactionType;
    private Date transactionDate;
    private String descriptor;
    private String memo;
    private String invoiceNumber;
    private String customerId;
    private HpsCreditExceptions exceptions;
	private BigDecimal convenienceAmount;
    private BigDecimal shippingAmount;
    
    public BigDecimal getConvenienceAmount() {
		return convenienceAmount;
	}
	public void setConvenienceAmount(BigDecimal convenienceAmount) {
		this.convenienceAmount = convenienceAmount;
	}
	public BigDecimal getShippingAmount() {
		return shippingAmount;
	}
	public void setShippingAmount(BigDecimal shippingAmount) {
		this.shippingAmount = shippingAmount;
	}
    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public int getOriginalTransactionId() {
        return originalTransactionId;
    }

    public void setOriginalTransactionId(int originalTransactionId) {
        this.originalTransactionId = originalTransactionId;
    }

    public String getMaskedCardNumber() {
        return maskedCardNumber;
    }

    public void setMaskedCardNumber(String maskedCardNumber) {
        this.maskedCardNumber = maskedCardNumber;
    }

    public HpsTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(HpsTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public HpsCreditExceptions getExceptions() {
        return exceptions;
    }

    public void setExceptions(HpsCreditExceptions exceptions) {
        this.exceptions = exceptions;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public HpsReportTransactionDetails fromElementTree(ElementTree rsp) {
        Element reportResponse = rsp.get("Transaction").firstChild();

        super.fromElementTree(rsp);

        this.setOriginalTransactionId(reportResponse.getInt("OriginalGatewayTxnId"));
        this.setTransactionType(HpsTransaction.serviceNameToTransactionType(reportResponse.getString("ServiceName")));

        Element data = reportResponse.get("Data");
        if(data.has("SettlementAmt"))
            this.setSettlementAmount(new BigDecimal(data.getString("SettlementAmt")));
        this.setMaskedCardNumber(data.getString("MaskedCardNbr"));
        if(data.has("ReqUtcDT")){
            try {
                this.setTransactionDate(new SimpleDateFormat("YYmmddTHHMMSSZ").parse(data.getString("ReqUtcDT")));
            } catch(ParseException e) { this.setTransactionDate(null); }
        }
        if(data.has("AuthAmt"))
            this.setAuthorizedAmount(new BigDecimal(data.getString("AuthAmt")));
        this.setAvsResultCode(data.getString("AVSRsltCode"));
        this.setAvsResultText(data.getString("AVSRsltText"));
        this.setCardType(data.getString("CardType"));
        this.setTransactionDescriptor(data.getString("TxnDescriptor"));
        this.setCpcIndicator(data.getString("CPCInd"));
        this.setAvsResultCode(data.getString("CVVRsltCode"));
        this.setAvsResultText(data.getString("CVVRsltText"));
        this.setReferenceNumber(data.getString("RefNbr"));
        this.setResponseCode(data.getString("RspCode"));
        this.setResponseText(data.getString("RspText"));
        
        if(data.has("ConvenienceAmtInfo")){
            this.setConvenienceAmount(new BigDecimal(data.getString("ConvenienceAmtInfo")));
        }
        if (data.has("ShippingAmtInfo")){
           this.setShippingAmount(new BigDecimal(data.getString("ShippingAmtInfo")));
        }
        if(data.has("TokenizationMsg")){
            this.setTokenData(new HpsTokenData());
            this.getTokenData().setTokenRspMsg(data.getString("TokenizationMsg"));
        }
        if(data.has("AdditionalTxnFields")){
            Element atf = data.get("AdditionalTxnFields");
            this.setMemo(atf.getString("Description"));
            this.setInvoiceNumber(atf.getString("InvoiceNbr"));
            this.setCustomerId(atf.getString("CustomerID"));
        }

        if(!data.getString("RspCode").equals("0")) {
            if(this.exceptions == null)
                this.setExceptions(new HpsCreditExceptions());
            this.getExceptions().setHpsIssuerException(HpsIssuerResponseValidation.getException(
                    rsp.get("Header").getInt("GatewayTxnId"),
                    data.getString("RspCode"),
                    data.getString("RspText")
            ));
        }

        return this;
    }
}