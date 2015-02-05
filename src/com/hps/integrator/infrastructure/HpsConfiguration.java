package com.hps.integrator.infrastructure;

import com.hps.integrator.abstractions.IHpsServicesConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HpsConfiguration implements IHpsServicesConfig {
	
	private String mUserName, mPassword, mDeveloperId, mVersionNumber, mSiteTrace, mServiceUri, mSecretAPIKey;	
	private int mLicenseId = -1, mDeviceId = -1, mSiteId = -1;
	private Properties mConfigProperties;
	
	public HpsConfiguration() throws HpsException
	{
		loadConfigurationProperties("conf/config.properties");
	}

	public HpsConfiguration(String configFilePath) throws HpsException
	{
		loadConfigurationProperties(configFilePath);
	}

	private void loadConfigurationProperties(String configFilePath) throws HpsException
	{
		try {
			File configFile = new File(configFilePath);
			FileInputStream fileStream;
			fileStream = new FileInputStream(configFile);
			mConfigProperties = new Properties();
			mConfigProperties.load(fileStream);
			fileStream.close();

		} catch (IOException e) {
			throw new HpsException("Unable to load configuration.", e);
		}
	}

	private int getIntegerProperty(String key)
	{
		String value = mConfigProperties.getProperty(key, "0");

		return Integer.parseInt(value);
	}

	public int getLicenseId()
	{
		if(mLicenseId == -1)
		{
			mLicenseId = getIntegerProperty("HpsLicenseId");
		}
		
		return mLicenseId;
	}
	
	public void setLicenseId(int licenseId)
	{
		mLicenseId = licenseId;
	}

	public int getSiteId()
	{
		if(mSiteId == -1)
		{
			mSiteId = getIntegerProperty("HpsSiteId");
		}
		
		return mSiteId;
	}

	public void setSiteId(int siteId)
	{
		mSiteId = siteId;
	}

	public int getDeviceId()
	{
		if(mDeviceId == -1)
		{
			mDeviceId = getIntegerProperty("HpsDeviceId");
		}
		
		return mDeviceId;
	}

	public void setDeviceId(int deviceId)
	{
		mDeviceId = deviceId;
	}
	
	public String getVersionNumber()
	{
		if(mVersionNumber == null)
		{
			mVersionNumber = mConfigProperties.getProperty("HpsVersionNumber", "");
		}
		
		return mVersionNumber;
	}

	public void setVersionNumber(String versionNumber)
	{
		mVersionNumber = versionNumber;
	}

	public String getUserName()
	{
		if(mUserName == null)
		{
			mUserName = mConfigProperties.getProperty("HpsUserName", "");
		}
		
		return mUserName;
	}

	public void setUserName(String userName)
	{
		mUserName = userName;
	}

	public String getPassword()
	{
		if(mPassword == null)
		{
			mPassword = mConfigProperties.getProperty("HpsPassword", "");
		}
		return mPassword;
	}

	public void setPassword(String password)
	{
		mPassword = password;
	}
	
	public String getDeveloperId()
	{
		if(mDeveloperId == null)
		{
			mDeveloperId = mConfigProperties.getProperty("HpsDeveloperId", "");
		}
		
		return mDeveloperId;
	}

	public void setDeveloperId(String developerId)
	{
		mDeveloperId = developerId;
	}

	public String getSiteTrace()
	{
		if(mSiteTrace == null)
		{
			mSiteTrace = mConfigProperties.getProperty("HpsSiteTrace", "");
		}
		
		return mSiteTrace;
	}

	public void setSiteTrace(String siteTrace)
	{
		mSiteTrace = siteTrace;
	}

	public String getServiceUri()
	{
		if(mServiceUri == null)
		{
			mServiceUri = mConfigProperties.getProperty("HpsServiceUri", "https://posgateway.cert.secureexchange.net/Hps.Exchange.PosGateway/PosGatewayService.asmx");
		}
		
		return mServiceUri;
	}

	public void setServiceUri(String serviceUri)
	{
		mServiceUri = serviceUri;
	}
	
	public String getSecretAPIKey()
	{
		if(mSecretAPIKey == null)
		{
			mSecretAPIKey = mConfigProperties.getProperty("HpsSecretAPIKey", "");
		}
		
		return mSecretAPIKey;
	}
	
	public void setSecretAPIKey(String secretAPIKey)
	{
		mSecretAPIKey = secretAPIKey;
	}
}
