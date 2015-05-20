package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.CardDataType;
import PosGateway.Exchange.Hps.CardDataTypeTokenData;
import com.hps.integrator.entities.HpsTrackData;
import com.hps.integrator.entities.credit.HpsCreditCard;

public class CreditRecurringPaymentTypeBuilder {
    private CreditRecurringBuilder parent;

    public CreditRecurringPaymentTypeBuilder(CreditRecurringBuilder parent) {
        this.parent = parent;
    }

    public CreditRecurringBuilder withCard(HpsCreditCard card) {
        parent.transaction.RecurringBilling.Block1.CardData = new CardDataType();
        parent.transaction.RecurringBilling.Block1.CardData.ManualEntry = GatewayTransactionBuilder.hydrateCardManualEntry(card);
        return parent;
    }

    public CreditRecurringBuilder withToken(String token) {
        CardDataTypeTokenData tokenData = new CardDataTypeTokenData();
        tokenData.TokenValue = token;

        parent.transaction.RecurringBilling.Block1.CardData = new CardDataType();
        parent.transaction.RecurringBilling.Block1.CardData.TokenData = tokenData;

        return parent;
    }

    public CreditRecurringBuilder withTrackData(HpsTrackData trackData) {
        parent.transaction.RecurringBilling.Block1.CardData = new CardDataType();
        parent.transaction.RecurringBilling.Block1.CardData.TrackData = GatewayTransactionBuilder.hydrateCardTrackData(trackData);
        return parent;
    }

    public CreditRecurringBuilder withPaymentMethodKey(String paymentmethodKey) {
        parent.transaction.RecurringBilling.Block1.PaymentMethodKey = paymentmethodKey;
        return parent;
    }
}
