package com.hps.integrator.abstractions;

import com.hps.integrator.entities.credit.HpsCreditExceptions;
import com.hps.integrator.entities.HpsTransactionType;

import java.util.Date;

public interface IHpsReportTransaction {
	
	int getOriginalTransactionId();
	
	void setOriginalTransactionId(int originalTransactionId);
	
	String getMaskedCardNumber();
	
	void setMaskedCardNumber(String maskedCardNumber);
	
	HpsTransactionType getTransactionType();
	
	void setTransactionType(HpsTransactionType transactionType);
	
	Date getTransactionDate();
	
	void setTransactionDate(Date transactionDate);
	
	HpsCreditExceptions getExceptions();
	
	void setExceptions(HpsCreditExceptions exceptions);
}
