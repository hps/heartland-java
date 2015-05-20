package com.hps.integrator.fluent;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.*;
import com.hps.integrator.infrastructure.*;
import com.hps.integrator.infrastructure.Element;
import sun.misc.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class SoapTransactionBuilder<TBuilder, TExecutionResult> extends TransactionBuilder<TExecutionResult> {
    protected PosRequestVer10Transaction transaction;
    private boolean logging = true;
    private String url = "";

    protected ElementTree Et;

    protected SoapTransactionBuilder(IHpsServicesConfig config) {
        super(config);

        this.Et = new ElementTree();

        String secretApiKey = config.getSecretAPIKey();
        if(!"".equals(secretApiKey)){
            if(secretApiKey.contains("_uat_"))
                this.url = "https://posgateway.uat.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            else if (secretApiKey.contains("_cert_"))
                this.url = "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            else this.url = "https://posgateway.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
        }
    }

    protected SoapTransactionBuilder(IHpsServicesConfig config, boolean enableLogging) {
        super(config);

        this.Et = new ElementTree();
        this.logging = enableLogging;
        String secretApiKey = config.getSecretAPIKey();
        if(!"".equals(secretApiKey)){
            if(secretApiKey.contains("_uat_"))
                this.url = "https://posgateway.uat.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            else if (secretApiKey.contains("_cert_"))
                this.url = "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
            else this.url = "https://posgateway.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx?wsdl";
        }
    }

    protected abstract TBuilder getBuilder();

    protected ElementTree doTransaction(Element transaction) throws HpsException {
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

        // Transaction
        Element trans = Et.subElement(version1, "Transaction");
        trans.append(transaction);

        String xml = Et.toString(envelope);
        if(this.logging)
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
            conn.addRequestProperty("Host", "posgateway.cert.secureexchange.net");

            DataOutputStream requestStream = new DataOutputStream(conn.getOutputStream());
            requestStream.write(data);
            requestStream.flush();
            requestStream.close();

            InputStream responseStream = conn.getInputStream();
            rawResponse += new String(IOUtils.readFully(responseStream, conn.getContentLength(), true));
            responseStream.close();
            if(this.logging)
                System.out.println("Response: " + rawResponse);

            return ElementTree.parse(rawResponse);
        } catch (IOException e) {
            throw new HpsGatewayException(HpsGatewayExceptionCodes.UnknownGatewayError, e.getMessage());
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
}
