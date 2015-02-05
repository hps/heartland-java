package com.hps.integrator.infrastructure;

public enum HpsIssuerExceptionCodes {
    IncorrectNumber,
    ExpiredCard,
    InvalidPin,
    PinRetriesExceeded,
    InvalidExpiry,
    InvalidAmount,
    PinVerification,
    IssuerTimeout,
    IncorrectCvc,
    CardDeclined,
    ProcessingError,
    IssuerTimeoutReversalError,
    UnknownCreditError
}