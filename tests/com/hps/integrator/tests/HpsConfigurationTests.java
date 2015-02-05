package com.hps.integrator.tests;

import com.hps.integrator.infrastructure.HpsConfiguration;
import com.hps.integrator.infrastructure.HpsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HpsConfigurationTests {
	
	private HpsConfiguration config;
	
	public HpsConfigurationTests() throws HpsException
	{
		config = new HpsConfiguration("conf/config.test.properties");
	}
	
	@Test
	public void LicenseId_Test()
	{
		assertEquals(00001, config.getLicenseId());
		config.setLicenseId(00011);
		assertEquals(00011, config.getLicenseId());
	}
	
	@Test
	public void SiteId_Test()
	{
		assertEquals(00002, config.getSiteId());
		config.setSiteId(00022);
		assertEquals(00022, config.getSiteId());
	}
	
	@Test
	public void DeviceId_Test()
	{
		assertEquals(00003, config.getDeviceId());
		config.setDeviceId(00033);
		assertEquals(00033, config.getDeviceId());
	}

	@Test
	public void VersionNumber_Test()
	{
		assertEquals("VersionNumber", config.getVersionNumber());
		config.setVersionNumber("VersionNumber1");
		assertEquals("VersionNumber1", config.getVersionNumber());
	}
	
	@Test
	public void UserName_Test()
	{
		assertEquals("UserName", config.getUserName());
		config.setUserName("UserName1");
		assertEquals("UserName1", config.getUserName());
	}
	
	@Test
	public void Password_Test()
	{
		assertEquals("Password", config.getPassword());
		config.setPassword("Password1");
		assertEquals("Password1", config.getPassword());
	}
	
	@Test
	public void DeveloperId_Test()
	{
		assertEquals("DeveloperId", config.getDeveloperId());
		config.setDeveloperId("DeveloperId1");
		assertEquals("DeveloperId1", config.getDeveloperId());
	}
	
	@Test
	public void SiteTrace_Test()
	{
		assertEquals("SiteTrace", config.getSiteTrace());
		config.setSiteTrace("SiteTrace1");
		assertEquals("SiteTrace1", config.getSiteTrace());
	}
	
	@Test
	public void ServiceUri_Test()
	{
		assertEquals("ServiceUri", config.getServiceUri());
		config.setServiceUri("ServiceUri1");
		assertEquals("ServiceUri1", config.getServiceUri());
	}
	
	@Test
	public void SecretAPIKey_Test() 
	{
		assertEquals("SecretApiKey", config.getSecretAPIKey());
		config.setSecretAPIKey("SecretApiKey1");
		assertEquals("SecretApiKey1", config.getSecretAPIKey());		
	}
}
