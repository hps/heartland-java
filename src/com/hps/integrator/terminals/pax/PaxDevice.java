package com.hps.integrator.terminals.pax;

import com.hps.integrator.abstractions.*;
import com.hps.integrator.fluent.pax.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.emums.*;
import com.hps.integrator.terminals.DeviceMessage;
import com.hps.integrator.terminals.pax.interfaces.*;
import com.hps.integrator.terminals.pax.responses.*;
import com.hps.integrator.terminals.pax.subgroups.*;
import com.hps.integrator.terminals.TerminalUtilities;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaxDevice implements IDisposable {
    private ITerminalConfiguration _settings;
    private IDeviceCommInterface _interface;

    private IMessageSentInterface onMessageSent;
    public void setOnMessageSent(IMessageSentInterface onMessageSent) {
        this.onMessageSent = onMessageSent;
    }

    public PaxDevice(ITerminalConfiguration settings) throws HpsConfigurationException {
        settings.validate();
        this._settings = settings;

        switch (_settings.getConnectionMode()){
            case TCP_IP:
                _interface = new PaxTcpInterface(settings);
                break;
            case HTTP:
                _interface = new PaxHttpInterface(settings);
                break;
            case SERIAL:
            case SSL_TCP:
                throw new NotImplementedException();
        }

        _interface.connect();
        _interface.setMessageSentHandler(new IMessageSentInterface() {
            public void messageSent(String message) {
                if(onMessageSent != null)
                    onMessageSent.messageSent(message);
            }
        });
    }

    // A00 - INITIALIZE
    public InitializeResponse initialize() throws HpsException {
        byte[] response = _interface.send(TerminalUtilities.buildRequest(PaxMsgId.A00_INITIALIZE));
        return new InitializeResponse(response);
    }

    // A14 - CANCEL
    public void cancel() throws HpsException {
        if(_settings.getConnectionMode() == ConnectionModes.HTTP)
            throw new HpsMessageException("The cancel command is not available in HTTP mode.");
        _interface.send(TerminalUtilities.buildRequest(PaxMsgId.A14_CANCEL));
    }

    // A16 - RESET
    public PaxDeviceResponse reset() throws HpsException {
        byte[] response = _interface.send(TerminalUtilities.buildRequest(PaxMsgId.A16_RESET));
        return new PaxDeviceResponse(response, PaxMsgId.A17_RSP_RESET);
    }

    // A26 - REBOOT
    public PaxDeviceResponse reboot() throws HpsException {
        byte[] response = _interface.send(TerminalUtilities.buildRequest(PaxMsgId.A26_REBOOT));
        return new PaxDeviceResponse(response, PaxMsgId.A27_RSP_REBOOT);
    }

    //<editor-fold desc="TRANSACTION COMMANDS">
    private byte[] doTransaction(PaxMsgId messageId, PaxTxnType transactionType, IRequestSubGroup... subGroups) throws HpsException {
        ArrayList<Object> commands = new ArrayList<Object>();
        commands.add(transactionType);
        commands.add(ControlCodes.FS);
        if(subGroups.length > 0){
            commands.add(subGroups[0]);
            for(int i = 1; i < subGroups.length; i++){
                commands.add(ControlCodes.FS);
                commands.add(subGroups[i]);
            }
        }

        DeviceMessage message = TerminalUtilities.buildRequest(messageId, commands.toArray());
        return _interface.send(message);
    }

    public CreditResponse doCredit(PaxTxnType transactionType, AmountRequest amounts, AccountRequest accounts, TraceRequest trace, AvsRequest avs, CashierSubGroup cashier, CommercialRequest commercial, EcomSubGroup ecom, ExtDataSubGroup extData) throws HpsException {
        byte[] response = doTransaction(PaxMsgId.T00_DO_CREDIT, transactionType, amounts, accounts, trace, avs, cashier, commercial, ecom, extData);
        return new CreditResponse(response);
    }

    public GiftResponse doGift(PaxMsgId messageId, PaxTxnType transactionType, AmountRequest amounts, AccountRequest accounts, TraceRequest trace, CashierSubGroup cashier,  ExtDataSubGroup extData) throws HpsException {
        byte[] response = doTransaction(messageId, transactionType, amounts, accounts, trace, cashier,extData);
        return new GiftResponse(response);
    }
    //</editor-fold>

    public DebitResponse DoDebit(PaxTxnType transactionType, AmountRequest amounts, AccountRequest accounts, TraceRequest trace, CashierSubGroup cashier, ExtDataSubGroup extData) throws HpsException {
        byte[] response = doTransaction(PaxMsgId.T02_DO_DEBIT, transactionType, amounts, accounts, trace, cashier, extData);
        return new DebitResponse(response);
    }
    // T00 - CREDIT METHODS
    public PaxCreditAuthBuilder creditAuth(int referenceNumber) {
        return creditAuth(referenceNumber, null);
    }
    public PaxCreditAuthBuilder creditAuth(int referenceNumber, BigDecimal amount) {
        return new PaxCreditAuthBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }

    public PaxCreditCaptureBuilder creditCapture(int referenceNumber) {
        return creditCapture(referenceNumber, null);
    }
    public PaxCreditCaptureBuilder creditCapture(int referenceNumber, BigDecimal amount) {
        return new PaxCreditCaptureBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }

    public PaxCreditReturnBuilder creditReturn(int referenceNumber) {
        return creditReturn(referenceNumber, null);
    }
    public PaxCreditReturnBuilder creditReturn(int referenceNumber, BigDecimal amount) {
        return new PaxCreditReturnBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }

    public PaxCreditSaleBuilder creditSale(int referenceNumber) {
        return creditSale(referenceNumber, null);
    }
    public PaxCreditSaleBuilder creditSale(int referenceNumber, BigDecimal amount) {
        return new PaxCreditSaleBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }
    public PaxCreditVerifyBuilder creditVerify(int referenceNumber) {
        return new PaxCreditVerifyBuilder(this).withReferenceNumber(referenceNumber);
    }

    public PaxCreditVoidBuilder creditVoid(int referenceNumber) {
        return new PaxCreditVoidBuilder(this).withReferenceNumber(referenceNumber);
    }
    //</editor-fold>

    //<editor-fold desc="T06 - GIFT METHODS">
    public PaxGiftSaleBuilder giftSale(int referenceNumber) {
        return giftSale(referenceNumber, null);
    }
    public PaxGiftSaleBuilder giftSale(int referenceNumber, BigDecimal amount) {
        return new PaxGiftSaleBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }

    public PaxGiftBalanceBuilder giftBalance(int referenceNumber) {
        return new PaxGiftBalanceBuilder(this).withReferenceNumber(referenceNumber);
    }

    public PaxGiftVoidBuilder giftVoid(int referenceNumber) {
        return new PaxGiftVoidBuilder(this).withReferenceNumber(referenceNumber);
    }

    public PaxGiftAddValueBuilder giftAddValue(int referenceNumber) {
        return giftAddValue(referenceNumber, null);
    }
    public PaxGiftAddValueBuilder giftAddValue(int referenceNumber, BigDecimal amount) {
        return new PaxGiftAddValueBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }

    //</editor-fold>

    public PaxDebitSaleBuilder debitSale(int referenceNumber) {
        return debitSale(referenceNumber, null);
    }
    public PaxDebitSaleBuilder debitSale(int referenceNumber, BigDecimal amount) {
        return new PaxDebitSaleBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }
    public PaxDebitReturnBuilder debitReturn(int referenceNumber) {
        return debitReturn(referenceNumber, null);
    }
    public PaxDebitReturnBuilder debitReturn(int referenceNumber, BigDecimal amount) {
        return new PaxDebitReturnBuilder(this).withReferenceNumber(referenceNumber).withAmount(amount);
    }

    // B00 - BATCH CLOSE
    public BatchCloseResponse batchClose() throws HpsException {
        byte[] response = _interface.send(TerminalUtilities.buildRequest(
                PaxMsgId.B00_BATCH_CLOSE,
                new SimpleDateFormat("YYYYMMDDhhmmss").format(new Date())));
        return new BatchCloseResponse(response);
    }

    public void dispose() {
        _interface.disconnect();
    }
}
