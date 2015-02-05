package com.hps.integrator.services;

import com.hps.integrator.abstractions.IHpsServicesConfig;

public class HpsServicesConfig implements IHpsServicesConfig {
	
	private int licenseId;
	private int siteId;
	private int deviceId;
	private String versionNumber;
	private String username;
	private String password;
	private String developerId;
	private String siteTrace;
	private String serviceUri;
	private String secretAPIKey;
	
	public int getLicenseId() {
		return licenseId;
	}
	
	public void setLicenseId(int licenseId) {
		this.licenseId = licenseId;
	}
	
	public int getSiteId() {
		return siteId;
	}
	
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	
	public int getDeviceId() {
		return deviceId;
	}
	
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getVersionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}
	
	public String getUserName() {
		return username;
	}
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDeveloperId() {
		return developerId;
	}
	
	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}
	
	public String getSiteTrace() {
		return siteTrace;
	}
	
	public void setSiteTrace(String siteTrace) {
		this.siteTrace = siteTrace;
	}
	
	public String getServiceUri() {
		return serviceUri;
	}
	
	public void setServiceUri(String serviceUri) {
		this.serviceUri = serviceUri;
	}
	
	public String getSecretAPIKey() {
		return secretAPIKey;
	}
	
	public void setSecretAPIKey(String secretAPIKey) {
		this.secretAPIKey = secretAPIKey;
	}
}
