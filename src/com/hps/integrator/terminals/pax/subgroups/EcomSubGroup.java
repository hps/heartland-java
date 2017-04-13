package com.hps.integrator.terminals.pax.subgroups;

import com.hps.integrator.abstractions.IRequestSubGroup;
import com.hps.integrator.abstractions.IResponseSubGroup;
import com.hps.integrator.infrastructure.emums.ControlCodes;
import com.hps.integrator.infrastructure.utils.HpsStringUtils;
import com.hps.integrator.infrastructure.utils.MessageReader;

public class EcomSubGroup implements IRequestSubGroup, IResponseSubGroup {
    String ecomMode;
    String transactionType;
    String secureType;
    String orderNumber;
    Integer installments;
    Integer currentInstallment;

    public String getEcomMode() {
        return ecomMode;
    }
    public void setEcomMode(String ecomMode) {
        this.ecomMode = ecomMode;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getSecureType() {
        return secureType;
    }
    public void setSecureType(String secureType) {
        this.secureType = secureType;
    }
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public Integer getInstallments() {
        return installments;
    }
    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
    public Integer getCurrentInstallment() {
        return currentInstallment;
    }
    public void setCurrentInstallment(Integer currentInstallment) {
        this.currentInstallment = currentInstallment;
    }

    public EcomSubGroup() { }
    public EcomSubGroup(MessageReader br) {
        String values = br.readToCode(ControlCodes.FS);
        if (HpsStringUtils.isNullOrEmpty(values))
            return;

        String[] data = values.split("\\[US\\]");
        try {
            this.ecomMode = data[0];
            this.transactionType = data[1];
            this.secureType = data[2];
            this.orderNumber = data[3];
            this.installments = Integer.parseInt(data[4]);
            this.currentInstallment = Integer.parseInt(data[5]);
        }
        catch (IndexOutOfBoundsException e) {
            // nom nom
        }
    }

    public String getElementString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ecomMode);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(transactionType);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(secureType);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(orderNumber);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(installments);
        sb.append((char)ControlCodes.US.getByte());
        sb.append(currentInstallment);

        return HpsStringUtils.trimEnd(sb.toString(), ControlCodes.US);
    }
}
