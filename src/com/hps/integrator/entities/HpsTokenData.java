package com.hps.integrator.entities;

public class HpsTokenData {
	
	private int mTokenRspCode;
	private String mTokenRspMsg;
	private String mTokenValue;
	
	public HpsTokenData()
	{
		
	}
	
	public HpsTokenData(String responseMessage)
	{
		mTokenRspMsg = responseMessage;
	}
	
	public int getTokenRspCode() {
		return mTokenRspCode;
	}

	public void setTokenRspCode(int rspCode)
	{
		mTokenRspCode = rspCode;
	}
	
	public String getTokenRspMsg() {
		return mTokenRspMsg;
	}

	public void setTokenRspMsg(String rspMsg)
	{
		mTokenRspMsg = rspMsg;
	}
	
	public String getTokenValue() {
		return mTokenValue;
	}
	
	public void setTokenValue(String tokenValue)
	{
		mTokenValue = tokenValue;
	}
}
