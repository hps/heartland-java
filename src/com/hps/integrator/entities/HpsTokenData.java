package com.hps.integrator.entities;

public class HpsTokenData {
	
	private int mTokenRspCode;
	private String mTokenRspMsg;
	private String mTokenValue;
	private String mCvv;
	private Integer mExpMonth;
	private Integer mExpYear;
	private boolean mCardPresent;
	private boolean mReaderPresent;
	
	public HpsTokenData()
	{
		this.mCardPresent = false;
		this.mReaderPresent = false;
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

	public String getCvv() {
		return mCvv;
	}

	public void setCvv(String cvv)
	{
		mCvv = cvv;
	}

	public Integer getExpMonth() {
		return mExpMonth;
	}

	public void setExpMonth(Integer expMonth)
	{
		mExpMonth = expMonth;
	}

	public Integer getExpYear() {
		return mExpYear;
	}

	public void setExpYear(Integer expYear)
	{
		mExpYear = expYear;
	}

	public boolean getCardPresent() {
		return mCardPresent;
	}

	public void setCardPresent(boolean cardPresent)
	{
		mCardPresent = cardPresent;
	}

	public boolean getReaderPresent() {
		return mReaderPresent;
	}

	public void setReaderPresent(boolean readerPresent)
	{
		mReaderPresent = readerPresent;
	}
}
