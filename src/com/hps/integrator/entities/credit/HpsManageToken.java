package com.hps.integrator.entities.credit;

import com.hps.integrator.entities.HpsTransaction;
import com.hps.integrator.entities.HpsTransactionHeader;
import com.hps.integrator.entities.credit.HpsCharge;
import com.hps.integrator.infrastructure.ElementTree;
import java.util.Date;

/**
 * A response from the ManageToken "transaction".
 * This is essentially an HpsTransaction without the transaction - only a header.
 * @author mclaborn
 */
public class HpsManageToken extends HpsTransaction {

    public HpsManageToken() {
    }

    public HpsManageToken(HpsTransactionHeader header) {
        super(header);
    }


    @Override
    public HpsManageToken fromElementTree(ElementTree rsp) {
        super.fromElementTree(rsp);
        return this;
    }

    /**
     * get the gateway response code from the header. 0 indicates success.
     * @return
     */
    public int getGateWayRespCode() {
        return getHeader().getGateWayRespCode();
    }

    /**
     * get the gateway response message from the header.
     * @return
     */
    public String getGatewayRspMsg() {
        return getHeader().getGatewayRspMsg();
    }

    /**
     * get the response date from the header
     * @return
     */
    public Date getRspDt() {
        return getHeader().getRspDt();
    }

    /**
     * Return true if the response code indicates that the supplied token value was not found.
     * @return
     */
    public boolean isTokenNotFound() {
        return 27 == getGateWayRespCode();
    }

}
