package com.hps.integrator.infrastructure;

public enum HpsGatewayExceptionCodes {
    UnknownGatewayError,
    InvalidOriginalTransaction,
    NoOpenBatch,
    InvalidCpcData,
    InvalidCardData,
    InvalidNumber,
    GatewayTimeout,
    UnexpectedGatewayResponse,
    GatewayTimeoutReversalError,
}