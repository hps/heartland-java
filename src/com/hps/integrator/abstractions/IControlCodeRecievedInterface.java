package com.hps.integrator.abstractions;

import com.hps.integrator.infrastructure.emums.ControlCodes;

public interface IControlCodeRecievedInterface {
    void codeReceived(ControlCodes code);
}
