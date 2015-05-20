package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.entities.gift.HpsGiftCardSale;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.validation.HpsGatewayResponseValidation;
import com.hps.integrator.infrastructure.validation.HpsIssuerResponseValidation;

import java.math.BigDecimal;

public class GiftCardSaleBuilder extends GatewayTransactionBuilder<GiftCardSaleBuilder, HpsGiftCardSale> {
    public GiftCardSaleBuilder(IHpsServicesConfig config, BigDecimal amount, HpsGiftCard giftCard) {
        super(config);

        transaction = new PosRequestVer10Transaction();
        PosGiftCardSaleReqType item = new PosGiftCardSaleReqType();
        GiftCardSaleReqBlock1Type block1 = new GiftCardSaleReqBlock1Type();

        block1.Amt = amount;
        block1.CardData = hydrateGiftCardData(giftCard);

        item.Block1 = block1;
        transaction.GiftCardSale = item;
    }

    @Override
    protected GiftCardSaleBuilder getBuilder() {
        return this;
    }

    @Override
    public HpsGiftCardSale execute() throws HpsException {
        PosResponse resp = doTransaction();
        HpsGatewayResponseValidation.checkGatewayResponse(resp);

        PosGiftCardSaleRspType saleRsp = resp.Ver10.Transaction.GiftCardSale;
        HpsIssuerResponseValidation.checkIssuerResponse(resp.Ver10.Header.GatewayTxnId,
                String.valueOf(saleRsp.RspCode), saleRsp.RspText);

        HpsGiftCardSale sale = new HpsGiftCardSale(hydrateTransactionHeader(resp.Ver10.Header));
        sale.setTransactionID(resp.Ver10.Header.GatewayTxnId);
        sale.setAuthorizationCode(saleRsp.AuthCode);
        sale.setBalanceAmount(saleRsp.BalanceAmt);
        sale.setSplitTenderCardAmount(saleRsp.SplitTenderCardAmt);
        sale.setSplitTenderBalanceDue(saleRsp.SplitTenderBalanceDueAmt);
        sale.setPointsBalanceAmount(saleRsp.PointsBalanceAmt);
        sale.setResponseCode(Integer.toString(saleRsp.RspCode));
        sale.setResponseText(saleRsp.RspText);

        return sale;
    }

    public GiftCardSaleBuilder withCurrency(Enums.currencyType currency) {
        transaction.GiftCardSale.Block1.Currency = currency;
        return this;
    }

    public GiftCardSaleBuilder withGratuity(BigDecimal gratuity) {
        transaction.GiftCardSale.Block1.GratuityAmtInfo = gratuity;
        return this;
    }

    public GiftCardSaleBuilder withTax(BigDecimal tax) {
        transaction.GiftCardSale.Block1.TaxAmtInfo = tax;
        return this;
    }
}
