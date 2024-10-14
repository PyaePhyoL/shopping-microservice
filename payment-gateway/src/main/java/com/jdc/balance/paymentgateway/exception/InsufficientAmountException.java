package com.jdc.balance.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InsufficientAmountException extends ResponseStatusException {

    public InsufficientAmountException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
