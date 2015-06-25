package com.hps.integrator.services.fluent;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.ebt.HpsEbtAuthorization;
import com.hps.integrator.fluent.*;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;
import com.hps.integrator.services.HpsSoapGatewayService;

import java.math.BigDecimal;

public class HpsFluentEbtService extends HpsSoapGatewayService {
    public HpsFluentEbtService() throws HpsException { }
    public HpsFluentEbtService(IHpsServicesConfig config) throws HpsException {
        super(config);
    }
    public HpsFluentEbtService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        super(config, enableLogging);
    }

    public HpsFluentEbtService withConfig(IHpsServicesConfig config) {
        this.servicesConfig = config;
        return this;
    }

    public EbtBalanceInquiryBuilder balanceInquiry() throws HpsException {
        return new EbtBalanceInquiryBuilder(this).withAmount(new BigDecimal("0.00"));
    }

    public EbtBenefitWithdrawalBuilder benefitWithdrawl(BigDecimal amount) throws HpsException {
        return new EbtBenefitWithdrawalBuilder(this).withAmount(amount);
    }

    public EbtCashBackPurchaseBuilder cashBackPurchase(BigDecimal amount) throws HpsException {
        return new EbtCashBackPurchaseBuilder(this).withAmount(amount);
    }

    public EbtPurchaseBuilder purchase() throws HpsException {
        return purchase(null);
    }
    public EbtPurchaseBuilder purchase(BigDecimal amount) throws HpsException {
        return new EbtPurchaseBuilder(this).withAmount(amount);
    }

    public EbtRefundBuilder refund() throws HpsException {
        return refund(null);
    }
    public EbtRefundBuilder refund(BigDecimal amount) throws HpsException {
        return new EbtRefundBuilder(this).withAmount(amount);
    }

    public EbtVoucherPurchaseBuilder voucherPurchase() throws HpsException {
        return voucherPurchase(null);
    }
    public EbtVoucherPurchaseBuilder voucherPurchase(BigDecimal amount) throws HpsException {
        return new EbtVoucherPurchaseBuilder(this).withAmount(amount);
    }

    public HpsEbtAuthorization submitTransaction(Element transaction) throws HpsException {
        return submitTransaction(transaction, null);
    }
    public HpsEbtAuthorization submitTransaction(Element transaction, String clientTransactionId) throws HpsException {
        ElementTree rsp = this.doTransaction(transaction, clientTransactionId);
        HpsGatewayResponseValidation.checkGatewayResponse(rsp, transaction.tag());
        HpsIssuerResponseValidation.checkIssuerResponse(
                rsp.get("Header").getInt("GatewayTxnId"),
                rsp.get(transaction.tag()).getString("RspCode"),
                rsp.get(transaction.tag()).getString("RspText")
        );

        return new HpsEbtAuthorization().fromElementTree(rsp);
    }
}
