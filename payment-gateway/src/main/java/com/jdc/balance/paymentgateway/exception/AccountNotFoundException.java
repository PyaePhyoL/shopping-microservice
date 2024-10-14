package com.jdc.balance.paymentgateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AccountNotFoundException extends ResponseStatusException {
    public AccountNotFoundException(String message) {
        super(HttpStatus.BAD_REQUEST,message);
    }
}
