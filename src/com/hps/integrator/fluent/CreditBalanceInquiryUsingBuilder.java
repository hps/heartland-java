package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditBalanceInquiryUsingBuilder {
    private CreditBalanceInquiryBuilder parent;

    public CreditBalanceInquiryUsingBuilder(CreditBalanceInquiryBuilder parent) {
        this.parent = parent;
    }

    public CreditBalanceInquiryBuilder withCard(HpsCreditCard card) {
        parent.transaction.PrePaidBalanceInquiry.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditBalanceInquiryBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.PrePaidBalanceInquiry.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditBalanceInquiryBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.PrePaidBalanceInquiry.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }
}
