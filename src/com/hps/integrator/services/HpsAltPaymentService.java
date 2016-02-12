package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.HpsTransactionStatus;
import com.hps.integrator.entities.altpayment.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsInputValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;
import java.util.List;

public class HpsAltPaymentService extends HpsSoapGatewayService {
    protected String transactionType = null;

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public HpsAltPaymentService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }
    public HpsAltPaymentService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
    }

    public HpsAltPaymentAuth authorize(String sessionId, BigDecimal amount, String currency, HpsBuyerData buyerData, HpsPaymentData paymentData) throws HpsException {
        return this.authorize(sessionId, amount, currency, buyerData, paymentData, null, null);
    }
    public HpsAltPaymentAuth authorize(String sessionId, BigDecimal amount, String currency, HpsBuyerData buyerData, HpsPaymentData paymentData, HpsShippingInfo shippingInfo) throws HpsException {
        return this.authorize(sessionId, amount, currency, buyerData, paymentData, shippingInfo, null);
    }
    public HpsAltPaymentAuth authorize(String sessionId, BigDecimal amount, String currency, HpsBuyerData buyerData, HpsPaymentData paymentData, HpsShippingInfo shippingInfo, List<HpsLineItem> lineItems) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("AltPaymentAuth");

        Et.subElement(transaction, "TransactionType").text(transactionType);
        Et.subElement(transaction, "SessionID").text(sessionId);
        transaction.append(hydrateBuyerData(buyerData));
        Et.subElement(transaction, "Amt").text(amount.toString());
        transaction.append(hydratePaymentData(paymentData));

        if (shippingInfo != null)
            transaction.append(hydrateShippingData(shippingInfo));
        if (lineItems != null)
            transaction.append(hydrateLineItems(lineItems));

        ElementTree response = submitTransaction(transaction, "AltPaymentAuth");
        return new HpsAltPaymentAuth().fromElementTree(response);
    }

    public HpsAltPaymentCapture capture(Integer transactionId, BigDecimal amount) throws HpsException {
        HpsInputValidation.checkAmount(amount);

        Element transaction = Et.element("AltPaymentAddToBatch");

        Et.subElement(transaction, "TransactionType").text(transactionType);
        Et.subElement(transaction, "GatewayTxnId").text(transactionId.toString());
        Et.subElement(transaction, "Amt").text(amount.toString());

        Element payment = Et.subElement(transaction, "Payment");
        Element nvp = Et.subElement(payment, "NameValuePair");
        Et.subElement(nvp, "Name").text("FullyCapturedFlag");
        Et.subElement(nvp, "Value").text("true");

        ElementTree response = submitTransaction(transaction, "AltPaymentAddToBatch");
        return new HpsAltPaymentCapture().fromElementTree(response);
    }

    public HpsAltPaymentCreateSession createSession(BigDecimal amount, String currency, HpsBuyerData buyer, HpsPaymentData payment) throws HpsException {
        return createSession(amount, currency, buyer, payment, null, null);
    }
    public HpsAltPaymentCreateSession createSession(BigDecimal amount, String currency, HpsBuyerData buyer, HpsPaymentData payment, HpsShippingInfo shippingAddress) throws HpsException {
        return createSession(amount, currency, buyer, payment, shippingAddress, null);
    }
    public HpsAltPaymentCreateSession createSession(BigDecimal amount, String currency, HpsBuyerData buyer, HpsPaymentData payment, HpsShippingInfo shippingAddress, List<HpsLineItem> lineItems) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("AltPaymentCreateSession");

        Et.subElement(transaction, "TransactionType").text(transactionType);
        transaction.append(hydrateBuyerData(buyer));
        Et.subElement(transaction, "Amt").text(amount.toString());
        transaction.append(hydratePaymentData(payment));
        if(shippingAddress != null)
            transaction.append(hydrateShippingData(shippingAddress));
        if(lineItems != null)
            transaction.append(hydrateLineItems(lineItems));

        ElementTree response = submitTransaction(transaction);
        return new HpsAltPaymentCreateSession().fromElementTree(response);
    }

    public HpsAltPaymentReturn refund(String  transactionId) throws HpsException {
        return refund(transactionId, false, null);
    }
    public HpsAltPaymentReturn refund(String  transactionId, boolean isPartial, BigDecimal partialAmount) throws HpsException {
        Element transaction = Et.element("AltPaymentReturn");

        Et.subElement(transaction, "TransactionType").text(transactionType);
        Et.subElement(transaction, "GatewayTxnId").text(transactionId);

        if(isPartial)
            Et.subElement(transaction, "Amt").text(partialAmount.toString());

        Element payment = Et.subElement(transaction, "Return");
        Element nvp = Et.subElement(payment, "NameValuePair");
        Et.subElement(nvp, "Name").text("ReturnType");
        Et.subElement(nvp, "Value").text(isPartial ? "partial" : "full");

        ElementTree response = submitTransaction(transaction);
        return new HpsAltPaymentReturn().fromElementTree(response);
    }

    public HpsAltPaymentSale sale(String sessionId, BigDecimal amount, String currency, HpsBuyerData buyer, HpsPaymentData payment) throws HpsException {
        return sale(sessionId, amount, currency, buyer, payment, null, null);
    }
    public HpsAltPaymentSale sale(String sessionId, BigDecimal amount, String currency, HpsBuyerData buyer, HpsPaymentData payment, HpsShippingInfo shippingAddress) throws HpsException {
        return sale(sessionId, amount, currency, buyer, payment, shippingAddress, null);
    }
    public HpsAltPaymentSale sale(String sessionId, BigDecimal amount, String currency, HpsBuyerData buyer, HpsPaymentData payment, HpsShippingInfo shippingAddress, List<HpsLineItem> lineItems) throws HpsException {
        HpsInputValidation.checkAmount(amount);
        HpsInputValidation.checkCurrency(currency);

        Element transaction = Et.element("AltPaymentSale");

        Et.subElement(transaction, "TransactionType").text(transactionType);
        Et.subElement(transaction, "SessionId").text(sessionId);
        transaction.append(hydrateBuyerData(buyer));
        Et.subElement(transaction, "Amt").text(amount.toString());
        transaction.append(hydratePaymentData(payment));
        if(shippingAddress != null)
            transaction.append(hydrateShippingData(shippingAddress));
        if(lineItems != null)
            transaction.append(hydrateLineItems(lineItems));

        ElementTree response = submitTransaction(transaction);
        return new HpsAltPaymentSale().fromElementTree(response);
    }

    public HpsAltPaymentVoid voidSale(String transactionId) throws HpsException {
        Element transaction = Et.element("AltPaymentVoid");
        Et.subElement(transaction, "TransactionType").text(transactionType);
        Et.subElement(transaction, "GatewayTxnId").text(transactionId);

        ElementTree response = submitTransaction(transaction);
        return new HpsAltPaymentVoid().fromElementTree(response);
    }

    public HpsAltPaymentSessionInfo sessionInfo(String sessionId) throws HpsException {
        Element transaction = Et.element("AltPaymentSessionInfo");
        Et.subElement(transaction, "TransactionType").text(transactionType);
        Et.subElement(transaction, "SessionId").text(sessionId);

        ElementTree response = submitTransaction(transaction);
        return new HpsAltPaymentSessionInfo().fromElementTree(response);
    }

    public HpsTransactionStatus status(String transactionId) throws HpsException {
        Element transaction = Et.element("GetTransactionStatus");
        Et.subElement(transaction, "GatewayTxnId").text(transactionId);

        ElementTree response = this.submitTransaction(transaction);
        return new HpsTransactionStatus().fromElementTree(response);
    }

    protected Element hydrateBuyerData(HpsBuyerData buyer) {
        Element data = Et.element("Buyer");
        if(buyer.getReturnUrl() != null)
            data.append(hydrateNameValuePair("ReturnUrl", buyer.getReturnUrl()));
        if(buyer.getCancelUrl() != null)
            data.append(hydrateNameValuePair("CancelUrl", buyer.getCancelUrl()));
        if(buyer.getEmailAddress() != null)
            data.append(hydrateNameValuePair("EmailAddress", buyer.getEmailAddress()));
        if(buyer.getPayerId() != null)
            data.append(hydrateNameValuePair("BuyerId", buyer.getPayerId()));
        if(buyer.getCredit() != null)
            data.append(hydrateNameValuePair("FundingSource", "credit"));

        return data;
    }

    protected Element hydrateLineItems(List<HpsLineItem> items) {
        Element lineItems = Et.element("LineItem");

        for(HpsLineItem item : items) {
            Element detail = Et.subElement(lineItems, "Detail");
            if(item.getName() != null)
                detail.append(hydrateNameValuePair("Name", item.getName()));
            if(item.getDescription() != null)
                detail.append(hydrateNameValuePair("Description", item.getDescription()));
            if(item.getNumber() != null)
                detail.append(hydrateNameValuePair("Number", item.getNumber()));
            if(item.getAmount() != null)
                detail.append(hydrateNameValuePair("Amount", item.getAmount()));
            if(item.getQuantity() != null)
                detail.append(hydrateNameValuePair("Quantity", item.getQuantity()));
            if(item.getTaxAmount() != null)
                detail.append(hydrateNameValuePair("TaxAmount", item.getTaxAmount()));
        }

        return lineItems;
    }

    protected Element hydrateNameValuePair(String name, String value) {
        Element nvp = Et.element("NameValuePair");
        Et.subElement(nvp, "Name").text(name);
        Et.subElement(nvp, "Value").text(value);
        return nvp;
    }

    protected Element hydratePaymentData(HpsPaymentData payment) {
        Element data = Et.element("Payment");
        data.append(hydrateNameValuePair("ItemAmount", payment.getSubTotal().toString()));
        if(payment.getShippingAmount() != null)
            data.append(hydrateNameValuePair("ShippingAmount", payment.getShippingAmount().toString()));
        if(payment.getTaxAmount() != null)
            data.append(hydrateNameValuePair("TaxAmount", payment.getTaxAmount().toString()));
        if(payment.getPaymentType() != null)
            data.append(hydrateNameValuePair("PaymentType", payment.getPaymentType()));
        if(payment.getInvoiceNumber() != null)
            data.append(hydrateNameValuePair("InvoiceNbr", payment.getInvoiceNumber()));

        return data;
    }

    protected Element hydrateShippingData(HpsShippingInfo info) {
        Element shipping = Et.element("Shipping");
        Element address = Et.subElement(shipping, "Address");

        HpsAddress addy = info.getAddress();
        address.append(hydrateNameValuePair("AllowAddressOverride", "false"));
        address.append(hydrateNameValuePair("ShipName", info.getName()));
        address.append(hydrateNameValuePair("ShipAddress", addy.getAddress()));
        address.append(hydrateNameValuePair("ShipCity", addy.getCity()));
        address.append(hydrateNameValuePair("ShipState", addy.getState()));
        address.append(hydrateNameValuePair("ShipZip", addy.getZip()));
        address.append(hydrateNameValuePair("ShipCountryCode", addy.getCountry()));
        return shipping;
    }

    private ElementTree submitTransaction(Element transaction) throws HpsException {
        return submitTransaction(transaction, null);
    }
    private ElementTree submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = doTransaction(transaction, clientTransactionId);

        BigDecimal amount = null;
        if(transaction.tag().equals("CreditSale") || transaction.tag().equals("CreditAuth"))
            amount = new BigDecimal(transaction.getString("Amt"));

        this.processGatewayResponse(rsp, transaction.tag(), amount);
        this.processIssuerResponse(rsp, transaction.tag());

        return rsp;
    }

    public void processIssuerResponse(ElementTree response, String expectedType) throws HpsException {
        Integer transactionId = response.get("Header").getInt("GatewayTxnId");
        Element transaction = response.get(expectedType);

        if(transaction != null) {
            String responseCode = transaction.has("RspCode") ? transaction.getString("RspCode") : null;
            String responseText = transaction.has("RspMessage") ? transaction.getString("RspText") : null;

            if(responseCode == null && transaction.has("TransactionStatus"))
                responseCode = transaction.get("TransactionStatus").getString("RspCode");
            if(responseCode == null && transaction.has("TransactionStatus"))
                responseText = transaction.get("TransactionStatus").getString("RspMessage");

            HpsIssuerResponseValidation.checkIssuerResponse(transactionId, responseCode, responseText);
        }
    }

    public void processGatewayResponse(ElementTree response, String expectedType, BigDecimal amount) throws HpsException {
        String responseCode = response.get("Header").getString("GatewayRspCode");
//        Integer transactionId = response.get("Header").getInt("GatewayTxnId");
        if(responseCode.equals("00"))
            return;

        if(responseCode.equals("30")){
//            try{
//                // this.reverse(transactionId, amount, "usd");
//            }
//            catch(HpsException e) {
//                throw new HpsGatewayException(HpsExceptionCodes.GatewayTimeoutReversalError, "Error occurred while reversing a charge due to a gateway timeout.", e);
//            }
            throw new HpsGatewayException(HpsExceptionCodes.GatewayTimeout, "Error occurred while processing due to a gateway timeout, please reverse the transaction.");
        }
        HpsGatewayResponseValidation.checkGatewayResponse(response, expectedType);
    }
}
