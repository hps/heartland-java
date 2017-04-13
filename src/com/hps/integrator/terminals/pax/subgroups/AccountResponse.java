package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.emums.PaxEntryMode;
import com.hps.integrator.infrastructure.utils.HpsEnumUtils;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class AccountResponse {
    String accountNumber;
    PaxEntryMode entryMode;
    String expireDate;
    String ebtType;
    String voucherNumber;
    String newAccountNumber;
    String cardType;
    String cardHolder;
    String cvdApprovalCode;
    String cvdMessage;
    boolean cardPresent;

    public String getAccountNumber() {
        return accountNumber;
    }
    public PaxEntryMode getEntryMode() {
        return entryMode;
    }
    public String getExpireDate() {
        return expireDate;
    }
    public String getEbtType() {
        return ebtType;
    }
    public String getVoucherNumber() {
        return voucherNumber;
    }
    public String getNewAccountNumber() {
        return newAccountNumber;
    }
    public String getCardType() {
        return cardType;
    }
    public String getCardHolder() {
        return cardHolder;
    }
    public String getCvdApprovalCode() {
        return cvdApprovalCode;
    }
    public String getCvdMessage() {
        return cvdMessage;
    }
    public boolean isCardPresent() {
        return cardPresent;
    }

    public AccountResponse(MessageReader br) {
        String values = br.readToCode(ControlCodes.FS);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try {
            accountNumber = data[0];
            entryMode = HpsEnumUtils.parse(PaxEntryMode.class, data[1]);
            expireDate = data[2];
            ebtType = data[3];
            voucherNumber = data[4];
            newAccountNumber = data[5];
            cardType = data[6];
            cardHolder = data[7];
            cvdApprovalCode = data[8];
            cvdMessage = data[9];
            cardPresent = data[10].equals("0");
        }
        catch (IndexOutOfBoundsException e) {
            // Nom nom
        }
    }
}
