package com.hps.integrator.infrastructure;

public enum Codes {
	
	unknown,
	sdk_exception,
    issuer_timeout,
    gateway_timeout,
    no_open_batch,
    card_declined,
    processing_error,
    invalid_amount,
    invalid_number,
    incorrect_number,
    expired_card,
    invalid_pin,
    pin_retries_exceeded,
    invalid_expiry,
    pin_verification,
    incorrect_cvc,
    unexpected_response,
    unknown_card_exception,
    invalid_original_transaction,
    invalid_cpc_data,
    invalid_card_data,
    invalid_transaction_id,
    invalid_date,
    invalid_currency,
    missing_currency
}

