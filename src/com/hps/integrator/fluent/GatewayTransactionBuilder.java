package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.*;
import com.hps.integrator.entities.check.HpsCheckResponseDetails;
import com.hps.integrator.entities.credit.HpsAuthorization;
import com.hps.integrator.entities.credit.HpsCardHolder;
import com.hps.integrator.entities.credit.HpsCpcData;
import com.hps.integrator.entities.credit.HpsCreditCard;
import com.hps.integrator.entities.gift.HpsEncryptionData;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.infrastructure.HpsException;
import com.hps.integrator.infrastructure.HpsExceptionCodes;
import com.hps.integrator.infrastructure.HpsInvalidRequestException;
import com.hps.integrator.infrastructure.HpsTrackDataMethod;
import org.ksoap2.SoapFault;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GatewayTransactionBuilder<TBuilder, TExecutionResult> extends TransactionBuilder<TExecutionResult> {
    protected PosRequestVer10Transaction transaction;
    protected long clientTransactionId = 0;

    protected GatewayTransactionBuilder(IHpsServicesConfig config) {
        super(config);
    }

    public TBuilder withClientTransactionId(int clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return getBuilder();
    }

    protected abstract TBuilder getBuilder();

    protected PosResponse doTransaction() throws HpsException {
        if (isConfigInvalid()) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidConfiguration, "Invalid SDK configuration.");
        } else if (transaction == null) {
            throw new IllegalArgumentException("V10 transaction not initialized.");
        }

        PosRequest req = new PosRequest();
        PosRequestVer10 item = new PosRequestVer10();
        PosRequestVer10Header header = new PosRequestVer10Header();

        if (servicesConfig.getSecretAPIKey() == null || servicesConfig.getSecretAPIKey().length() == 0) {
            header.UserName = servicesConfig.getUserName();
            header.Password = servicesConfig.getPassword();
        } else {
            header.SecretAPIKey = servicesConfig.getSecretAPIKey();
        }

        if (!"".equals(servicesConfig.getVersionNumber())) {
            header.VersionNbr = servicesConfig.getVersionNumber();
        }

        if (!"".equals(servicesConfig.getDeveloperId())) {
            header.DeveloperID = servicesConfig.getDeveloperId();
        }

        if (!"".equals(servicesConfig.getSiteTrace())) {
            header.SiteTrace = servicesConfig.getSiteTrace();
        }

        if (this.clientTransactionId != 0) {
            header.ClientTxnId = this.clientTransactionId;
        }

        if (servicesConfig.getLicenseId() != 0) {
            header.LicenseId = servicesConfig.getLicenseId();
        }
        if (servicesConfig.getSiteId() != 0) {
            header.SiteId = servicesConfig.getSiteId();
        }
        if (servicesConfig.getDeviceId() != 0) {
            header.DeviceId = servicesConfig.getDeviceId();
        }

        item.Transaction = transaction;
        item.Header = header;
        req.Ver10 = item;

        String url = servicesConfig.getServiceUri();
        String key = servicesConfig.getSecretAPIKey();

        if (key != null && key.length() > 0) {
            if (key.contains("_cert_")) {
                url = "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            } else {
                url = "https://posgateway.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            }
        }

        try {
            PosGatewayInterface client = new PosGatewayInterface(url);
            client.enableLogging = false;
            return client.DoTransaction(req);
        } catch (MalformedURLException e) {
            throw new HpsException("Malformed URL", e);
        } catch (SoapFault e) {
            throw new HpsException("Unhandled SOAP error", e);
        } catch (Exception e) {
            throw new HpsException("Unknown error occurred", e);
        }
    }

    protected static HpsTransactionHeader hydrateTransactionHeader(PosResponseVer10Header header) {
        return new HpsTransactionHeader(
                header.GatewayRspCode,
                header.GatewayRspMsg,
                header.RspDT,
                header.GatewayTxnId
        );
    }

    private boolean isConfigInvalid() {
        return servicesConfig.getSecretAPIKey() == null &&
                (
                        servicesConfig.getLicenseId() == -1 ||
                                servicesConfig.getDeviceId() == -1 ||
                                servicesConfig.getSiteId() == -1 ||
                                servicesConfig.getUserName() == null ||
                                servicesConfig.getPassword() == null ||
                                servicesConfig.getServiceUri() == null
                );
    }

    protected static CardDataTypeManualEntry hydrateCardManualEntry(HpsCreditCard card) {
        CardDataTypeManualEntry result = new CardDataTypeManualEntry();
        
        result.CardNbr = card.getNumber();
        result.ExpMonth = card.getExpMonth();
        result.ExpYear = card.getExpYear();
        result.CVV2 = card.getCvv();
        result.CardPresent = Enums.booleanType.N;
        result.ReaderPresent = Enums.booleanType.N;

        return result;
    }

    protected static CardHolderDataType hydrateCardHolderData(HpsCardHolder cardHolder) {
        CardHolderDataType result = new CardHolderDataType();

        result.CardHolderFirstName = cardHolder.getFirstName();
        result.CardHolderLastName = cardHolder.getLastName();
        result.CardHolderEmail = cardHolder.getEmail();
        result.CardHolderPhone = cardHolder.getPhone();
        result.CardHolderAddr = cardHolder.getAddress().getAddress();
        result.CardHolderCity = cardHolder.getAddress().getCity();
        result.CardHolderState = cardHolder.getAddress().getState();
        result.CardHolderZip = cardHolder.getAddress().getZip();

        return result;
    }

    protected static AdditionalTxnFieldsType hydrateAdditionalTxnFields(HpsTransactionDetails details) {
        if (details == null) return null;

        AdditionalTxnFieldsType result = new AdditionalTxnFieldsType();
        if (details.getMemo() != null && details.getMemo().length() > 0) result.Description = details.getMemo();
        if (details.getInvoiceNumber() != null && details.getInvoiceNumber().length() > 0)
            result.InvoiceNbr = details.getInvoiceNumber();
        if (details.getCustomerId() != null && details.getCustomerId().length() > 0)
            result.CustomerID = details.getCustomerId();

        return result;
    }

    protected static EncryptionDataType hydrateEncryptionData(HpsEncryptionData encryptionData) {
        if (encryptionData == null) return null;

        EncryptionDataType result = new EncryptionDataType();
        result.EncryptedTrackNumber = encryptionData.getEncryptedTrackNumber();
        result.KSN = encryptionData.getKsn();
        result.KTB = encryptionData.getKtb();
        result.Version = encryptionData.getVersion();
        
        return result;
    }

    protected static DirectMktDataType hydrateDirectMktData(HpsDirectMarketData directMarketData)
    {
        if (directMarketData == null) return null;

        DirectMktDataType result = new DirectMktDataType();
        result.DirectMktInvoiceNbr = directMarketData.getInvoiceNumber();
        result.DirectMktShipDay = directMarketData.getShipDay();
        result.DirectMktShipMonth = directMarketData.getShipMonth();
        
        return result;
    }

    protected static CardDataTypeTrackData hydrateCardTrackData(HpsTrackData trackData)
    {
        if (trackData == null) return null;

        CardDataTypeTrackData result = new CardDataTypeTrackData();
        result.method = trackData.getTrackDataMethod() == HpsTrackDataMethod.Swipe ? Enums.CardDataType_TrackData_method.swipe : Enums.CardDataType_TrackData_method.proximity;
        result.value = trackData.getValue();

        return result;
    }

    protected static GiftCardDataType hydrateGiftCardData(HpsGiftCard giftCard)
    {
        GiftCardDataType cardDataType = new GiftCardDataType();

        if(giftCard.isTrackData()) {
            cardDataType.TrackData = giftCard.getNumber();
        } else {
            cardDataType.CardNbr = giftCard.getNumber();
        }

        if(giftCard.getAlias() != null && !giftCard.getAlias().isEmpty()) {
            cardDataType.Alias = giftCard.getAlias();
        }

        if(giftCard.getEncryptionData() != null) {
            EncryptionDataType encryptionData = new EncryptionDataType();
            encryptionData.EncryptedTrackNumber = giftCard.getEncryptionData().getEncryptedTrackNumber();
            encryptionData.KSN = giftCard.getEncryptionData().getKsn();
            encryptionData.KTB = giftCard.getEncryptionData().getKsn();
            encryptionData.Version = giftCard.getEncryptionData().getVersion();
            cardDataType.EncryptionData = encryptionData;
        }

        return cardDataType;
    }

    protected static List<HpsCheckResponseDetails> getResponseDetails(ArrayList<CheckRspInfoType> responseInfo) {
        List<HpsCheckResponseDetails> result = new ArrayList<HpsCheckResponseDetails>();

        for (CheckRspInfoType info : responseInfo) {
            HpsCheckResponseDetails newItem = new HpsCheckResponseDetails();
            newItem.setCode(info.Code);
            newItem.setFieldName(info.FieldName);
            newItem.setFieldNumber(info.FieldNumber);
            newItem.setMessage(info.Message);
            newItem.setMessageType(info.Type);
        }

        return result;
    }
    
    protected static HpsAuthorization hydrateAuthorization(PosResponseVer10Header header, AuthRspStatusType authResponse) {
        HpsAuthorization auth = new HpsAuthorization(hydrateTransactionHeader(header));

        auth.setTransactionID(header.GatewayTxnId);
        auth.setAvsResultCode(authResponse.AVSRsltCode);
        auth.setAvsResultText(authResponse.AVSRsltText);
        auth.setCvvResultCode(authResponse.CVVRsltCode);
        auth.setCvvResultText(authResponse.CVVRsltText);
        auth.setAuthorizationCode(authResponse.AuthCode);
        auth.setAuthorizedAmount(authResponse.AuthAmt);
        auth.setReferenceNumber(authResponse.RefNbr);
        auth.setResponseCode(authResponse.RspCode);
        auth.setResponseText(authResponse.RspText);
        auth.setCardType(authResponse.CardType);
        auth.setCpcIndicator(authResponse.CPCInd);

        if (header.TokenData != null) {
            HpsTokenData tokenData = new HpsTokenData();
            tokenData.setTokenRspCode(header.TokenData.TokenRspCode);
            tokenData.setTokenRspMsg(header.TokenData.TokenRspMsg);
            tokenData.setTokenValue(header.TokenData.TokenValue);
            auth.setTokenData(tokenData);
        }

        return auth;
    }

    protected static CPCDataType hydrateCpcData(HpsCpcData cpcData) {
        if(cpcData == null) return null;

        CPCDataType result = new CPCDataType();
        result.CardHolderPONbr = cpcData.getCardHolderPoNumber();
        result.TaxType = cpcData.getTaxType();
        result.TaxAmt = cpcData.getTaxAmount();

        return result;
    }
}
