package com.test.paymentservice.domain.exception;

public class InsufficientFundException extends RuntimeException {

    public InsufficientFundException(String message) {
        super(message);
    }
}
