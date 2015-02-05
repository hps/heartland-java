package com.hps.integrator.entities.batch;

import java.math.BigDecimal;

public class HpsBatch {
	
	private int mId;
	private int mTransactionCount;
	private BigDecimal mTotalAmount;
	private int mSequenceNumber;
	
	public int getId() {
		return mId;
	}
	public void setId(int id) {
		this.mId = id;
	}
	public int getTransactionCount() {
		return mTransactionCount;
	}
	public void setTransactionCount(int transactionCount) {
		this.mTransactionCount = transactionCount;
	}
	public BigDecimal getTotalAmount() {
		return mTotalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.mTotalAmount = totalAmount;
	}
	public int getSequenceNumber() {
		return mSequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.mSequenceNumber = sequenceNumber;
	}
}
