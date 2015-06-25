package com.hps.integrator.entities.credit;

import com.hps.integrator.abstractions.IHpsReportTransaction;
import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.entities.HpsTransactionType;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HpsReportTransactionSummary extends HpsTransaction implements
        IHpsReportTransaction {

    private BigDecimal amount;
    private BigDecimal settlementAmount;
    private int originalTransactionId;
    private String maskedCardNumber;
    private HpsTransactionType transactionType;
    private Date transactionDate;
    private HpsCreditExceptions exceptions;

    public HpsReportTransactionSummary() {}

    public HpsReportTransactionSummary(HpsTransactionHeader header) {
        super(header);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public HpsReportTransactionSummary[] fromElementTree(ElementTree rsp, HpsTransactionType filterBy){
        Element reportResponse = rsp.get("ReportActivity");

        List<HpsReportTransactionSummary> transactions = new ArrayList<HpsReportTransactionSummary>();
        String serviceName = "";
        if(filterBy != null)
            serviceName = HpsTransaction.transactionTypeToServiceName(filterBy);

        for(Element charge: reportResponse.getAll("Details")){
            if(filterBy == null || charge.getString("ServiceName").equals(serviceName)){
                HpsReportTransactionSummary trans = new HpsReportTransactionSummary();

                trans.fromElementTree(rsp);

                if(charge.has("OriginalGatewayTxnId"))
                    trans.setOriginalTransactionId(charge.getInt("OriginalGatewayTxnId"));
                if(charge.has("MaskedCardNbr"))
                    trans.setMaskedCardNumber(charge.getString("MaskedCardNbr"));
                if(charge.has("IssuerRspCode"))
                    trans.setResponseCode(charge.getString("IssuerRspCode"));
                if(charge.has("IssuerRspText"))
                    trans.setResponseText(charge.getString("IssuerRspText"));
                if(charge.has("Amt"))
                    trans.setAmount(new BigDecimal(charge.getString("Amt")));
                if(charge.has("SettlementAmt"))
                    trans.setSettlementAmount(new BigDecimal(charge.getString("SettlementAmt")));
                if(charge.has("TxnUtcDT")) {
                    try {
                        String date = charge.getString("TxnUtcDT");
                        String pattern = "yyyy-MM-dd'T'HH:mm:ss";
                        if(date.contains("."))
                            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS";
                        trans.setTransactionDate(new SimpleDateFormat(pattern).parse(date));
                    } catch (ParseException e) {
                        trans.setTransactionDate(null);
                    }
                }
                trans.setTransactionType(HpsTransaction.serviceNameToTransactionType(charge.getString("ServiceName")));
                if(filterBy != null)
                    trans.setTransactionType(filterBy);

                String gatewayRspCode = charge.getString("GatewayRspCode");
                String issuerRspCode = charge.getString("IssuerRspCode");
                if(!gatewayRspCode.equals("0") || !issuerRspCode.equals("00")){
                    trans.setExceptions(new HpsCreditExceptions());
                    if(!gatewayRspCode.equals("0"))
                        trans.getExceptions().setHpsException(HpsGatewayResponseValidation.getException(
                                Integer.parseInt(gatewayRspCode),
                                charge.getString("GatewayRspMsg")
                        ));
                    if(!issuerRspCode.equals("00"))
                        trans.getExceptions().setHpsIssuerException(HpsIssuerResponseValidation.getException(
                                charge.getInt("GatewayTxnId"),
                                issuerRspCode,
                                charge.getString("IssuerRspText")
                        ));
                }
                transactions.add(trans);
            }
        }
        return transactions.toArray(new HpsReportTransactionSummary[transactions.size()]);
    }
}