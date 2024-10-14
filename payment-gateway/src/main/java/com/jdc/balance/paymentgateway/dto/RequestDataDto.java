package com.jdc.balance.paymentgateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RequestDataDto(
        @JsonProperty("credit_number") String creditNumber,
        String name,
        double amount
) {
}
