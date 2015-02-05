package com.hps.integrator.abstractions;

public interface IHpsServicesConfig {
	
	String getDeveloperId();
	int getDeviceId();
	int getLicenseId();
	String getPassword();
	String getServiceUri();
	int getSiteId();
	String getSiteTrace();
	String getUserName();
	String getVersionNumber();
	String getSecretAPIKey();
}
