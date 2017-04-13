package com.hps.integrator.fluent.pax;

import com.hps.integrator.entities.HpsAddress;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.fluent.HpsBuilderAbstract;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.emums.PaxTxnType;
import com.hps.integrator.terminals.pax.PaxDevice;
import com.hps.integrator.terminals.pax.responses.CreditResponse;
import com.hps.integrator.terminals.pax.subgroups.*;

public class PaxCreditVerifyBuilder extends HpsBuilderAbstract<PaxDevice, CreditResponse> {
    Integer referenceNumber;
    HpsCreditCard card;
    HpsAddress address;
    boolean requestMultiUseToken = false;

    public PaxCreditVerifyBuilder withCard(HpsCreditCard card) {
        this.card = card;
        return this;
    }
    public PaxCreditVerifyBuilder withAddress(HpsAddress address) {
        this.address = address;
        return this;
    }
    public PaxCreditVerifyBuilder withRequestMultiUseToken(boolean requestMultiUseToken) {
        this.requestMultiUseToken = requestMultiUseToken;
        return this;
    }
    public PaxCreditVerifyBuilder withReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
        return this;
    }

    public PaxCreditVerifyBuilder(PaxDevice device) { super(device); }

    @Override
    public CreditResponse execute() throws HpsException {
        super.execute();

        AccountRequest account = new AccountRequest();
        if (card != null) {
            account.setAccountNumber(card.getNumber());
            account.setExpd(String.format("%s%s", card.getExpMonth(), card.getExpYear()));
        }

        // Avs Sub Group
        AvsRequest avs = new AvsRequest();
        if (address != null) {
            avs.setZipCode(address.getZip());
            avs.setAddress(address.getAddress());
        }

        // Trace Sub Group
        TraceRequest trace = new TraceRequest();
        trace.setReferenceNumber(referenceNumber.toString());

        return service.doCredit(
                requestMultiUseToken ? PaxTxnType.TOKENIZE : PaxTxnType.VERIFY,
                new AmountRequest(),
                account,
                trace,
                avs,
                new CashierSubGroup(),
                new CommercialRequest(),
                new EcomSubGroup(),
                new ExtDataSubGroup()
        );
    }
}
