package com.jdc.balance.paymentgateway.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jdc.balance.paymentgateway.dto.AccountDto;
import com.jdc.balance.paymentgateway.dto.RequestDataDto;
import com.jdc.balance.paymentgateway.exception.AccountNotFoundException;
import com.jdc.balance.paymentgateway.service.PaymentService;
import com.jdc.balance.paymentgateway.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return EntityUtil.toAccountDto(paymentService.createAccount(accountDto.getName(), accountDto.getAmount()));
    }

    @PostMapping(value = "/buy-transaction" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String>  makeBuyTransaction(@RequestBody RequestDataDto requestDataDto) {
        if (paymentService.existsAccount(requestDataDto.name(), requestDataDto.creditNumber())) {
            paymentService.buyItemsTransaction(
                    requestDataDto.name(),
                    requestDataDto.amount(),
                    requestDataDto.creditNumber(),
                    "John Doe",
                    "3539433"
            );
        } else {
            throw new AccountNotFoundException("Account not found");
        }
        return ResponseEntity.ok("Buy Successful");
    }

    @PostMapping("/deposit")
    public ResponseEntity<String>  makeDeposit(@RequestBody RequestDataDto requestDataDto) {
        if (paymentService.existsAccount(requestDataDto.name(), requestDataDto.creditNumber())) {
            paymentService.deposit(requestDataDto.name(), requestDataDto.amount(), requestDataDto.creditNumber());
        } else {
            throw new AccountNotFoundException("Account not found");
        }
        return new ResponseEntity<>("Success Deposit Transaction", HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String>  makeWithdraw(@RequestBody RequestDataDto requestDataDto) {
        if (paymentService.existsAccount(requestDataDto.name(), requestDataDto.creditNumber())) {
            paymentService.withdraw(requestDataDto.name(), requestDataDto.amount(), requestDataDto.creditNumber());
        } else {
            throw new AccountNotFoundException("Account not found");
        }
        return new ResponseEntity<>("Success Withdraw Transaction", HttpStatus.OK);
    }
}
