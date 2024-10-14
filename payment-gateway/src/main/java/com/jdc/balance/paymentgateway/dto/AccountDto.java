package com.jdc.balance.paymentgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer id;
    private String cardNumber;
    private String name;
    private double amount;
}
