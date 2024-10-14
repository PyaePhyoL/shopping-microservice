package com.jdc.balance.paymentgateway.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jdc.balance.paymentgateway.dto.AccountDto;
import com.jdc.balance.paymentgateway.service.PaymentService;
import com.jdc.balance.paymentgateway.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class PaymentController {
    private final PaymentService paymentService;

    record RequestDataDto (
            @JsonProperty("credit_number") String creditNumber,
            String name,
            double amount
    ) {}

    @PostMapping("/create")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return EntityUtil.toAccountDto(paymentService.createAccount(accountDto.getName(), accountDto.getAmount()));
    }

}
