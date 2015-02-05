package com.hps.integrator.entities;

public enum HpsTransactionType 
{
	Authorize,
	Capture,
	Charge,
	Refund,
	Reverse,
	Verify,
	List,
	Get,
	Void,
	SecurityError,
	BatchClose
}
