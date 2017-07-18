package com.hps.integrator.infrastructure;

public enum HpsExceptionCodes {
    AuthenticationError,
    InvalidConfiguration,
    InvalidAmount,
    MissingCurrency,
    InvalidCurrency,
    InvalidDate,
    MissingCheckName,
    IssuerTimeoutReversal,

    GatewayTimeoutReversalError,
    UnknownGatewayError,
    InvalidOriginalTransaction,
    NoOpenBatch,
    InvalidCpcData,
    InvalidCardData,
    InvalidNumber,
    GatewayTimeout,
    UnexpectedGatewayResponse,
    InvalidArgument,
    InvalidZipcode,
    InvalidPhonenumber,
    InvalidEmail,
    InvalidCardHolderDetail,
}