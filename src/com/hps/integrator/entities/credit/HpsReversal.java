package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.infrastructure.Element;
import com.hps.integrator.infrastructure.ElementTree;

public class HpsReversal extends HpsTransaction {

	private String mAvsResultCode;
	private String mCvvResultCode;
	private String mAvsResultText;
	private String mCvvResultText;
	private String mCpcIndicator;
	
	public String getAvsResultCode() {
		return mAvsResultCode;
	}
	
	public void setAvsResultCode(String avsResultCode) {
		this.mAvsResultCode = avsResultCode;
	}
	
	public String getCvvResultCode() {
		return mCvvResultCode;
	}
	
	public void setCvvResultCode(String cvvResultCode) {
		this.mCvvResultCode = cvvResultCode;
	}
	
	public String getAvsResultText() {
		return mAvsResultText;
	}
	
	public void setAvsResultText(String avsResultText) {
		this.mAvsResultText = avsResultText;
	}
	
	public String getCvvResultText() {
		return mCvvResultText;
	}
	
	public void setCvvResultText(String cvvResultText) {
		this.mCvvResultText = cvvResultText;
	}
	
	public String getCpcIndicator() {
		return mCpcIndicator;
	}
	
	public void setCpcIndicator(String cpcIndicator) {
		this.mCpcIndicator = cpcIndicator;
	}

    public HpsReversal fromElementTree(ElementTree rsp) {
        Element reverseResponse = rsp.get("Transaction").firstChild();

        super.fromElementTree(rsp);
        this.setAvsResultCode(reverseResponse.getString("AVSRsltCode"));
        this.setAvsResultText(reverseResponse.getString("AVSRsltText"));
        this.setCpcIndicator(reverseResponse.getString("CPCInd"));
        this.setCvvResultCode(reverseResponse.getString("CVVRsltCode"));
        this.setCvvResultText(reverseResponse.getString("CVVRsltText"));

        return this;
    }
}
