package com.hps.integrator.services;

import PosGateway.Exchange.Hps.*;
import com.hps.integrator.abstractions.IHpsServicesConfig;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.*;
import org.ksoap2.SoapFault;

import java.math.BigDecimal;
import java.net.MalformedURLException;

public abstract class HpsSoapGatewayService {
    private boolean enableLogging = false;
    protected PosRequestVer10Transaction transaction = null;
    protected Long clientTransactionId = (long) 0;
    protected IHpsServicesConfig servicesConfig;

    protected HpsSoapGatewayService() throws HpsException {
        this.servicesConfig = new HpsConfiguration();
    }

    protected HpsSoapGatewayService(IHpsServicesConfig config) throws HpsException {
        this.servicesConfig = (config == null) ? new HpsConfiguration() : config;
    }

    protected HpsSoapGatewayService(IHpsServicesConfig config, boolean enableLogging) throws HpsException {
        this.servicesConfig = (config == null) ? new HpsConfiguration() : config;
        this.enableLogging = enableLogging;
    }

    protected PosResponse doTransaction() throws HpsException {
        if (isConfigInvalid()) {
            throw new HpsInvalidRequestException(HpsExceptionCodes.InvalidConfiguration, "Invalid SDK configuration.");
        } else if (transaction == null) {
            throw new IllegalArgumentException("V10 transaction not initialized.");
        }

        PosRequest req = new PosRequest();
        PosRequestVer10 item = new PosRequestVer10();
        PosRequestVer10Header header = new PosRequestVer10Header();

        if(servicesConfig.getSecretAPIKey() == null || servicesConfig.getSecretAPIKey().length() == 0) {
            header.UserName = servicesConfig.getUserName();
            header.Password = servicesConfig.getPassword();
        } else {
            header.SecretAPIKey = servicesConfig.getSecretAPIKey();
        }

        if(!"".equals(servicesConfig.getVersionNumber())) {
            header.VersionNbr = servicesConfig.getVersionNumber();
        }

        if(!"".equals(servicesConfig.getDeveloperId())) {
            header.DeveloperID = servicesConfig.getDeveloperId();
        }

        if(!"".equals(servicesConfig.getSiteTrace())) {
            header.SiteTrace = servicesConfig.getSiteTrace();
        }

        if (this.clientTransactionId != 0) {
            header.ClientTxnId = this.clientTransactionId;
        }

        if(servicesConfig.getLicenseId() != 0) { header.LicenseId = servicesConfig.getLicenseId(); }
        if(servicesConfig.getSiteId() != 0) { header.SiteId = servicesConfig.getSiteId(); }
        if(servicesConfig.getDeviceId() != 0) { header.DeviceId = servicesConfig.getDeviceId(); }

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
            client.enableLogging = this.enableLogging;
            return client.DoTransaction(req);
        } catch (MalformedURLException e) {
            throw new HpsException("Malformed URL", e);
        } catch (SoapFault e) {
            throw new HpsException("Unhandled SOAP error", e);
        } catch (Exception e) {
            throw new HpsException("Unknown error occurred", e);
        }
    }

    protected HpsTransactionHeader hydrateTransactionHeader(PosResponseVer10Header header) {
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
