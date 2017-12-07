package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.applepay.ecv1.PaymentData3DS;
import com.hps.integrator.entities.*;
import com.hps.integrator.entities.check.HpsCheck;
import com.hps.integrator.entities.check.HpsCheckHolder;
import com.hps.integrator.entities.credit.*;
import com.hps.integrator.entities.gift.HpsGiftCard;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.emums.EncodingType;
import com.hps.integrator.infrastructure.emums.TokenMappingType;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public abstract class HpsSoapGatewayService {
    private boolean enableLogging = false;
    protected String clientTransactionId;
    protected IHpsServicesConfig servicesConfig;
    protected ElementTree Et;
    protected String url;

    protected HpsSoapGatewayService() throws HpsException {
        this(null, false);
    }

    protected HpsSoapGatewayService(IHpsServicesConfig config) throws HpsException {
        this(config, false);
    }

    protected HpsSoapGatewayService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        this.servicesConfig = (config == null) ? new HpsConfiguration() : config;
        this.enableLogging = enableLogging;
        this.Et = new ElementTree();

        String secretApiKey = this.servicesConfig.getSecretAPIKey();
        if(secretApiKey != null && !"".equals(secretApiKey)){
            if(secretApiKey.contains("_uat_"))
                this.url = "https://api-uat.heartlandportico.com/paymentserver.v1/POSGatewayService.asmx?wsdl";
            else if (secretApiKey.contains("_cert_"))
                this.url = "https://cert.api2.heartlandportico.com/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            else this.url = "https://api2.heartlandportico.com/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
        }
    }

    protected ElementTree doTransaction(Element transaction) throws HpsException {
        return this.doTransaction(transaction, null);
    }
    protected ElementTree doTransaction(Element transaction, String clientTransactionId) throws HpsException {
        if (isConfigInvalid()) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidConfiguration, "Invalid SDK configuration.");
        }

        Element envelope = Et.element("soap:Envelope");
        envelope.set("xmlns:soap", "http://schemas.xmlsoap.org/soap/envelope/");
        envelope.set("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        envelope.set("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");

        Element body = Et.subElement(envelope, "soap:Body");

        // Request
        Element request = Et.subElement(body, "PosRequest");
        request.set("xmlns", "http://Hps.Exchange.PosGateway");
        Element version1 = Et.subElement(request, "Ver1.0");

        // Header
        Element header = Et.subElement(version1, "Header");

        String secretApiKey = this.servicesConfig.getSecretAPIKey();
        if(secretApiKey != null && !secretApiKey.equals(""))
            Et.subElement(header, "SecretAPIKey").text(secretApiKey);
        else {
            Element siteId = Et.subElement(header, "SiteId");
            Element deviceId = Et.subElement(header, "DeviceId");
            Element licenseId = Et.subElement(header, "LicenseId");
            Element username = Et.subElement(header, "UserName");
            Element password = Et.subElement(header, "Password");

            siteId.text(Integer.toString(this.servicesConfig.getSiteId()));
            deviceId.text(Integer.toString(this.servicesConfig.getDeviceId()));
            licenseId.text(Integer.toString(this.servicesConfig.getLicenseId()));
            username.text(this.servicesConfig.getUserName());
            password.text(this.servicesConfig.getPassword());
        }

        String developerId = this.servicesConfig.getDeveloperId();
        String versionNumber = this.servicesConfig.getVersionNumber();
        if(developerId != null && !developerId.equals(""))
            Et.subElement(header, "DeveloperID").text(developerId);
        if(versionNumber != null && !versionNumber.equals(""))
            Et.subElement(header, "VersionNbr").text(versionNumber);
        if(clientTransactionId != null)
            Et.subElement(header, "ClientTxnId").text(clientTransactionId);

        // Transaction
        Element trans = Et.subElement(version1, "Transaction");
        trans.append(transaction);

        String xml = Et.toString(envelope);
        if(this.enableLogging)
            System.out.println("Request: " + xml);

        HttpsURLConnection conn;
        try {
            String mUrl = this.servicesConfig.getServiceUri();
            if(mUrl == null || "".equals(mUrl))
                mUrl = this.url;

            conn = (HttpsURLConnection)new URL(mUrl).openConnection();
        }
        catch (IOException e) { throw new HpsException(e.getMessage(), e); }

        String rawResponse = "";
        try {
            byte[] data = xml.getBytes();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            conn.addRequestProperty("Content-Length", String.valueOf(data.length));
            conn.addRequestProperty("Host", "cert.api2.heartlandportico.com");

            DataOutputStream requestStream = new DataOutputStream(conn.getOutputStream());
            requestStream.write(data);
            requestStream.flush();
            requestStream.close();

            InputStream responseStream = conn.getInputStream();
            rawResponse += readFully(responseStream);
            responseStream.close();
            if(this.enableLogging)
                System.out.println("Response: " + rawResponse);

            return ElementTree.parse(rawResponse);
        } catch (IOException e) {
            throw new HpsGatewayException(HpsExceptionCodes.UnknownGatewayError, e.getMessage());
        }
    }

    private String readFully(InputStream stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        Reader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
        int c;
        while((c = reader.read()) != -1)
            sb.append((char)c);
        return sb.toString();
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

    public Element hydrateGiftCardData(HpsGiftCard card) {
        return this.hydrateGiftCardData(card, "CardData");
    }
    public Element hydrateGiftCardData(HpsGiftCard card, String elementName) {
        Element cardElement = Et.element(elementName);
        if(card.getCardNumber() != null)
            Et.subElement(cardElement, "CardNbr").text(card.getCardNumber());
        else if(card.getTrackData() != null)
            Et.subElement(cardElement, "TrackData").text(card.getTrackData());
        else if(card.getAlias() != null)
            Et.subElement(cardElement, "Alias").text(card.getAlias());
        else if(card.getTokenValue() != null)
            Et.subElement(cardElement, "TokenValue").text(card.getTokenValue());

        if(card.getEncryptionData() != null)
            cardElement.append(this.hydrateEncryptionData(card.getEncryptionData()));

        if(card.getPin() != null)
            Et.subElement(cardElement, "PIN").text(card.getPin());

        return cardElement;
    }

    public Element hydrateCardHolder(HpsCardHolder cardHolder) {
        Element holder = Et.element("CardHolderData");
        if(cardHolder.getFirstName() != null)
        Et.subElement(holder, "CardHolderFirstName").text(cardHolder.getFirstName());
        if(cardHolder.getLastName() != null)
        Et.subElement(holder, "CardHolderLastName").text(cardHolder.getLastName());
        if(cardHolder.getEmail() != null)
            Et.subElement(holder, "CardHolderEmail").text(cardHolder.getEmail());
        if(cardHolder.getPhone() != null)
            Et.subElement(holder, "CardHolderPhone").text(cardHolder.getPhone());

        if(cardHolder.getAddress() != null) {
            HpsAddress address = cardHolder.getAddress();
            if(address.getAddress() != null)
                Et.subElement(holder, "CardHolderAddr").text(address.getAddress());
            if(address.getCity() != null)
                Et.subElement(holder, "CardHolderCity").text(address.getCity());
            if(address.getState() != null)
                Et.subElement(holder, "CardHolderState").text(address.getState());
            if(address.getZip() != null)
                Et.subElement(holder, "CardHolderZip").text(address.getZip());
        }

        return holder;
    }

    public Element hydrateCardManualEntry(HpsCreditCard card, boolean cardPresent, boolean readerPresent) {
        Element manualEntry = Et.element("ManualEntry");
        Et.subElement(manualEntry, "CardNbr").text(card.getNumber());
        Et.subElement(manualEntry, "ExpMonth").text(card.getExpMonth().toString());
        Et.subElement(manualEntry, "ExpYear").text(card.getExpYear().toString());
        if(card.getCvv() != null)
            Et.subElement(manualEntry, "CVV2").text(card.getCvv());
        Et.subElement(manualEntry, "CardPresent").text(cardPresent ? "Y" : "N");
        Et.subElement(manualEntry, "ReaderPresent").text(readerPresent ? "Y" : "N");

        return manualEntry;
    }

    public Element hydrateAdditionalTxnFields(HpsTransactionDetails details) {
        if(details != null) {
            Element addons = Et.element("AdditionalTxnFields");
            if (details.getMemo() != null)
                Et.subElement(addons, "Description").text(details.getMemo());
            if (details.getCustomerId() != null)
                Et.subElement(addons, "CustomerID").text(details.getCustomerId());
            if (details.getInvoiceNumber() != null)
                Et.subElement(addons, "InvoiceNbr").text(details.getInvoiceNumber());

            return addons;
        }
        return null;
    }

    public Element hydrateConsumerInfo(HpsCheckHolder checkHolder) {
        Element consumerInfo = Et.element("ConsumerInfo");

        if(checkHolder.getAddress() != null) {
            HpsAddress address = checkHolder.getAddress();

            if (address.getAddress() != null) {
                Et.subElement(consumerInfo, "Address1").text(address.getAddress());
            }

            if (address.getCity() != null) {
                Et.subElement(consumerInfo, "City").text(address.getCity());
            }

            if (address.getState() != null) {
                Et.subElement(consumerInfo, "State").text(address.getState());
            }

            if (address.getZip() != null) {
                Et.subElement(consumerInfo, "Zip").text(address.getZip());
            }
        }

        if(checkHolder.getCheckName() != null)
            Et.subElement(consumerInfo, "CheckName").text(checkHolder.getCheckName());

        if(checkHolder.getCourtesyCard() != null)
            Et.subElement(consumerInfo, "CourtesyCard").text(checkHolder.getCourtesyCard());

        if(checkHolder.getDlNumber() != null)
            Et.subElement(consumerInfo, "DLNumber").text(checkHolder.getDlNumber());

        if(checkHolder.getDlState() != null)
            Et.subElement(consumerInfo, "DLState").text(checkHolder.getDlState());

        if(checkHolder.getEmail() != null)
            Et.subElement(consumerInfo, "EmailAddress").text(checkHolder.getEmail());

        if(checkHolder.getFirstName() != null)
            Et.subElement(consumerInfo, "FirstName").text(checkHolder.getFirstName());

        if(checkHolder.getLastName() != null)
            Et.subElement(consumerInfo, "LastName").text(checkHolder.getLastName());

        if(checkHolder.getPhone() != null)
            Et.subElement(consumerInfo, "PhoneNumber").text(checkHolder.getPhone());

        if(checkHolder.getSsn4() != null || checkHolder.getDobYear() != null){
            Element identityElement = Et.subElement(consumerInfo, "IdentityInfo");
            if(checkHolder.getSsn4() != null)
                Et.subElement(identityElement, "SSNL4").text(checkHolder.getSsn4());
            if(checkHolder.getDobYear() != null)
                Et.subElement(identityElement, "DOBYear").text(checkHolder.getDobYear());
        }

        return consumerInfo;
    }

    public Element hydrateCheckData(HpsCheck check){
        Element accountInfo = Et.element("AccountInfo");
        if(check.getAccountNumber() != null)
            Et.subElement(accountInfo, "AccountNumber").text(check.getAccountNumber());
        if(check.getCheckNumber() != null)
            Et.subElement(accountInfo, "CheckNumber").text(check.getCheckNumber());
        if(check.getMicrNumber() != null)
            Et.subElement(accountInfo, "MICRData").text(check.getMicrNumber());
        if(check.getRoutingNumber() != null)
            Et.subElement(accountInfo, "RoutingNumber").text(check.getRoutingNumber());
        if(check.getAccountType() != null)
            Et.subElement(accountInfo, "AccountType").text(check.getAccountType().getValue());

        return accountInfo;
    }

    public Element hydrateDirectMarketData(HpsDirectMarketData directMarketData) {
        if(directMarketData != null) {
            Element marketData = Et.element("DirectMktData");
            Et.subElement(marketData, "DirectMktInvoiceNbr").text(directMarketData.getInvoiceNumber());
            Et.subElement(marketData, "DirectMktShipDay").text(directMarketData.getShipDay().toString());
            Et.subElement(marketData, "DirectMktShipMonth").text(directMarketData.getShipMonth().toString());

            return marketData;
        }
        return null;
    }

    public Element hydrateCpcData(HpsCpcData cpcData) {
        if(cpcData != null) {
            Element cpcElement = Et.element("CPCData");
            if(cpcData.getCardHolderPoNumber() != null)
                Et.subElement(cpcElement, "CardHolderPONbr").text(cpcData.getCardHolderPoNumber());
            if(cpcData.getTaxAmount() != null)
                Et.subElement(cpcElement, "TaxAmt").text(cpcData.getTaxAmount().toString());
            Et.subElement(cpcElement, "TaxType").text(cpcData.getTaxType().getValue());

            return cpcElement;
        }
        return null;
    }

    public Element hydrateSecureECommerce(PaymentData3DS paymentData) {
        if(paymentData == null)
            return null;

        Element se_element = Et.element("SecureECommerce");
        if (paymentData.getPaymentDataTye().equals("ApplePay")) {
            Et.subElement(se_element, "TypeOfPaymentData").text("3DSecure");
        } else {
            Et.subElement(se_element, "TypeOfPaymentData").text(paymentData.getPaymentDataTye());
        }
        Et.subElement(se_element, "PaymentDataSource").text("ApplePay");

        Element pd_element = Et.subElement(se_element, "PaymentData").text(paymentData.getOnlinePaymentCryptogram());
        pd_element.set("encoding", EncodingType.base64.getValue());

        if(!paymentData.getEciIndicator().equals(""))
            Et.subElement(se_element, "ECommerceIndicator").text(paymentData.getEciIndicator());

        return se_element;
    }

    public Element hydrateTrackData(HpsTrackData trackData) {
        if(trackData != null) {
            Element trackDataElement = Et.element("TrackData").text(trackData.getValue());
            if(trackData.getTrackDataMethod() != null)
                trackDataElement.set("method", trackData.getTrackDataMethod().getValue());

            return trackDataElement;
        }
        return null;
    }

    public Element hydrateEncryptionData(HpsEncryptionData encryptionData) {
        if(encryptionData != null) {
            Element element = Et.element("EncryptionData");
            Et.subElement(element, "Version").text(encryptionData.getVersion());
            if(encryptionData.getEncryptedTrackNumber() != null)
                Et.subElement(element, "EncryptedTrackNumber").text(encryptionData.getEncryptedTrackNumber());
            if(encryptionData.getKtb() != null)
                Et.subElement(element, "KTB").text(encryptionData.getKtb());
            if(encryptionData.getKsn() != null)
                Et.subElement(element, "KSN").text(encryptionData.getKsn());

            return element;
        }
        return null;
    }

    public Element hydrateTokenData(String token, boolean cardPresent, boolean readerPresent) {
        HpsTokenData tokenData = new HpsTokenData();
        tokenData.setTokenValue(token);
        tokenData.setCardPresent(cardPresent);
        tokenData.setReaderPresent(readerPresent);

        return this.hydrateTokenData(tokenData);
    }

    public Element hydrateTokenData(HpsTokenData token) {
        Element tokenData = Et.element("TokenData");
        Et.subElement(tokenData, "TokenValue").text(token.getTokenValue());
        Et.subElement(tokenData, "CardPresent").text(token.getCardPresent() ? "Y" : "N");
        Et.subElement(tokenData, "ReaderPresent").text(token.getReaderPresent() ? "Y" : "N");

        if (token.getCvv() != null) {
            Et.subElement(tokenData, "CVV2").text(token.getCvv());
        }

        if (token.getExpMonth() != null) {
            Et.subElement(tokenData, "ExpMonth").text(token.getExpMonth().toString());
        }

        if (token.getExpYear() != null) {
            Et.subElement(tokenData, "ExpYear").text(token.getExpYear().toString());
        }

        return tokenData;
    }

    public Element hydrateTokenParams(TokenMappingType tokenParams) {
        if(tokenParams != null) {
            Element paramsElement = Et.element("TokenParameters");
            Et.subElement(paramsElement, "Mapping").text(tokenParams.getValue());

            return paramsElement;
        }
        return null;
    }

    public Element hydrateAutoSubstantiation(HpsAutoSubstantiation autoSubstantiation) {
        if(autoSubstantiation != null) {
            Element autoElement = Et.element("AutoSubstantiation");
            if(autoSubstantiation.getMerchantVerificationValue() != null)
                Et.subElement(autoElement, "MerchantVerificationValue").text(autoSubstantiation.getMerchantVerificationValue());
            Et.subElement(autoElement, "RealTimeSubstantiation").text(autoSubstantiation.isRealTimeSubstantiation() ? "Y" : "N");

            HpsAdditionalAmount[] values = autoSubstantiation.getAdditionalAmounts();
            String[] amountCount = new String[] {"First", "Second", "Third", "Fourth"};
            for (int i = 0; i < values.length; i++) {
                HpsAdditionalAmount value = values[i];

                Element amtElement = Et.subElement(autoElement, amountCount[i] + "AdditionalAmtInfo");
                Et.subElement(amtElement, "AmtType").text(value.getAmountType().getValue());
                Et.subElement(amtElement, "Amt").text(value.getAmount().toString());
            }

            return autoElement;
        }
        return null;
    }

    public Element hydrateTagData(HpsTagDataType tagDataType) {
		if(tagDataType != null) {
			 Element tagDataElement = Et.element("TagData");
			 Element tagDataSubElement = Et.subElement(tagDataElement, "TagValues");
			 if (tagDataType.getTagData() != null) {
				 tagDataSubElement.text(tagDataType.getTagData().toString());
			 }
			 if (tagDataType.getSource() != null) {
				 tagDataSubElement.set("source", tagDataType.getSource().getValue());
			 }
			 return tagDataElement;
	    }
	    return null;
    }

    public Element hydrateEMVData(HpsEMVDataType emvData) {
		if (emvData != null) {
			Element emvElement = Et.element("EMVData");
			if (emvData.getEmvTagData() != null) {
				Et.subElement(emvElement, "EMVTagData").text(emvData.getEmvTagData().toString());
			}
			if (emvData.getEmvPinBlock() != null) {
				Et.subElement(emvElement, "PINBlock").text(emvData.getEmvPinBlock().toString());
			}
			if (emvData.getEmvChipCondition() != null) {
				Et.subElement(emvElement, "EMVChipCondition").text(emvData.getEmvChipCondition().getValue());
			}
			return emvElement;
		}
		return null;
	}

    public String getClientTxnId(HpsTransactionDetails details) {
        if(details != null)
            return details.getClientTransactionId();
        return null;
    }

}
