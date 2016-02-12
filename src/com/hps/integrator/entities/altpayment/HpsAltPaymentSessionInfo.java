package com.hps.integrator.entities.altpayment;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class HpsAltPaymentSessionInfo extends HpsAltPaymentResponse {
    private String status;
    private HpsBuyerData buyer;
    private HpsPaymentData payment;
    private HpsShippingInfo shipping;
    private List<HpsLineItem> lineItems;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public HpsBuyerData getBuyer() {
        return buyer;
    }
    public void setBuyer(HpsBuyerData buyer) {
        this.buyer = buyer;
    }
    public HpsPaymentData getPayment() {
        return payment;
    }
    public void setPayment(HpsPaymentData payment) {
        this.payment = payment;
    }
    public HpsShippingInfo getShipping() {
        return shipping;
    }
    public void setShipping(HpsShippingInfo shipping) {
        this.shipping = shipping;
    }
    public List<HpsLineItem> getLineItems() {
        return lineItems;
    }
    public void setLineItems(List<HpsLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public HpsAltPaymentSessionInfo fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);

        Element sessionInfo = rsp.get("Transaction").firstChild();
        Dictionary<String, String> _buyer = nvpToArray(sessionInfo.get("Buyer"));
        Dictionary<String, String> _payment = nvpToArray(sessionInfo.get("Payment"));
        Dictionary<String, String> _shipping = nvpToArray(sessionInfo.get("Shipping").get("Address"));
        List<Dictionary<String, String>> _lineItems = new ArrayList<Dictionary<String, String>>();
        for(Element lineItem: sessionInfo.getAll("LineItem"))
            _lineItems.add(nvpToArray(lineItem.get("Detail")));

        this.setStatus(sessionInfo.has("Status") ? sessionInfo.getString("Status") : null);

        this.buyer = new HpsBuyerData();
        this.buyer.setEmailAddress(_buyer.get("EmailAddress") != null ? _buyer.get("EmailAddress") : null);
        this.buyer.setPayerId(_buyer.get("BuyerId") != null ? _buyer.get("BuyerId") : null);
        this.buyer.setStatus(_buyer.get("Status") != null ? _buyer.get("Status") : null);
        this.buyer.setCountryCode(_buyer.get("CountryCode") != null ? _buyer.get("CountryCode") : null);
        this.buyer.setFirstName(_buyer.get("FirstName") != null ? _buyer.get("FirstName") : null);
        this.buyer.setLastName(_buyer.get("LastName") != null ? _buyer.get("LastName") : null);

        this.shipping = new HpsShippingInfo();
        this.shipping.setName(_shipping.get("ShipName") != null ? _shipping.get("ShipName") : null);
        this.shipping.setAddress(new HpsAddress());
        this.shipping.address.setAddress(_shipping.get("ShipAddress") != null ? _shipping.get("ShipAddress") : null);
        this.shipping.address.setCity(_shipping.get("ShipCity") != null ? _shipping.get("ShipCity") : null);
        this.shipping.address.setState(_shipping.get("ShipState") != null ? _shipping.get("ShipState") : null);
        this.shipping.address.setZip(_shipping.get("ShipZip") != null ? _shipping.get("ShipZip") : null);
        this.shipping.address.setCountry(_shipping.get("ShipCountryCode") != null ? _shipping.get("ShipCountryCode") : null);

        this.payment = new HpsPaymentData();
        if(_payment.get("ItemAmount") != null)
            this.payment.setSubTotal(new BigDecimal(_payment.get("ItemAmount")));
        if(_payment.get("ShippingAmount") != null)
            this.payment.setShippingAmount(new BigDecimal(_payment.get("ShippingAmount")));
        if(_payment.get("TaxAmount") != null)
            this.payment.setTaxAmount(new BigDecimal(_payment.get("TaxAmount")));

        this.lineItems = new ArrayList<HpsLineItem>();
        for(Dictionary<String, String> item: _lineItems) {
            HpsLineItem lineItem = new HpsLineItem();
            lineItem.setName(item.get("Name") != null ? item.get("Name") : null);
            lineItem.setAmount(item.get("Amount") != null ? item.get("Amount") : null);
            lineItem.setNumber(item.get("Number") != null ? item.get("Number") : null);
            lineItem.setQuantity(item.get("Quantity") != null ? item.get("Quantity") : null);
            lineItem.setTaxAmount(item.get("TaxAmount") != null ? item.get("TaxAmount") : null);

            this.lineItems.add(lineItem);
        }

        return this;
    }
}
