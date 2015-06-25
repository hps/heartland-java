package com.hps.integrator.services.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.fluent.*;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;
import com.hps.integrator.services.HpsSoapGatewayService;

import java.math.BigDecimal;

public class HpsFluentGiftService extends HpsSoapGatewayService {
    public HpsFluentGiftService() throws HpsException { }
    public HpsFluentGiftService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }
    public HpsFluentGiftService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
    }

    public HpsFluentGiftService withConfig(IHpsServicesConfig config) {
        this.servicesConfig = config;
        return this;
    }

    public GiftCardActivateBuilder activate(){
        return activate(null);
    }
    public GiftCardActivateBuilder activate(BigDecimal amount) {
        return new GiftCardActivateBuilder(this).withAmount(amount).withCurrency("USD");
    }

    public GiftCardAddValueBuilder addValue() {
        return addValue(null);
    }
    public GiftCardAddValueBuilder addValue(BigDecimal amount) {
        return new GiftCardAddValueBuilder(this).withAmount(amount).withCurrency("USD");
    }

    public GiftCardAliasBuilder alias() {
        return new GiftCardAliasBuilder(this);
    }

    public GiftCardBalanceBuilder balance() {
        return new GiftCardBalanceBuilder(this);
    }

    public GiftCardDeactivateBuilder deactivate() {
        return new GiftCardDeactivateBuilder(this);
    }

    public GiftCardReplaceBuilder replace() {
        return new GiftCardReplaceBuilder(this);
    }

    public GiftCardReverseBuilder reverse() {
        return reverse(null);
    }
    public GiftCardReverseBuilder reverse(BigDecimal amount) {
        return new GiftCardReverseBuilder(this).withAmount(amount);
    }

    public GiftCardRewardBuilder reward(){
        return reward(null);
    }
    public GiftCardRewardBuilder reward(BigDecimal amount) {
        return new GiftCardRewardBuilder(this).withAmount(amount).withCurrency("USD");
    }

    public GiftCardSaleBuilder sale() {
        return sale(null);
    }
    public GiftCardSaleBuilder sale(BigDecimal amount) {
        return new GiftCardSaleBuilder(this).withAmount(amount).withCurrency("USD");
    }

    public GiftCardVoidBuilder voidSale(){
        return voidSale(null);
    }
    public GiftCardVoidBuilder voidSale(Integer transactionId) {
        return new GiftCardVoidBuilder(this).withTransactionId(transactionId);
    }

    public ElementTree submitTransaction(Element transaction) throws HpsException {
        return submitTransaction(transaction, null);
    }
    public ElementTree submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = doTransaction(transaction, clientTransactionId);
        HpsGatewayResponseValidation.checkGatewayResponse(rsp, transaction.tag());
        HpsIssuerResponseValidation.checkIssuerResponse(
                rsp.get("Header").getInt("GatewayTxnId"),
                rsp.get(transaction.tag()).getString("RspCode"),
                rsp.get(transaction.tag()).getString("RspText")
        );

        return rsp;
    }
}
