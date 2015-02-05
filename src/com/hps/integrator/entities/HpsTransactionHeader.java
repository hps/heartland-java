package com.hps.integrator.entities;

import java.util.Date;

/**
 * The HPS charge header.
 */
public class HpsTransactionHeader 
{
	private int gateWayRespCode;
	private String gatewayRspMsg;
	private Date rspDt;
	private long clientTxnId;
	
	/**
	 * Constructor
	 * @param responseCode gateway RSP code
	 * @param responseMessage gateway RSP MSG
	 * @param responseDt RSP DateTime
	 * @param transactionId client TXN ID
	 */
	public HpsTransactionHeader(int responseCode, String responseMessage, Date responseDt, long transactionId)
	{
		gateWayRespCode = responseCode;
		gatewayRspMsg = responseMessage;
		rspDt = responseDt;
		clientTxnId = transactionId;
	}

	public int getGateWayRespCode() {
		return gateWayRespCode;
	}

	public String getGatewayRspMsg() {
		return gatewayRspMsg;
	}

	public Date getRspDt() {
		return rspDt;
	}

	public long getClientTxnId() {
		return clientTxnId;
	}
}
