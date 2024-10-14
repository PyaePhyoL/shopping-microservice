package com.jdc.balance.shoppingui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDataDto {
    private @JsonProperty("credit_number") String creditNumber;
    private String name;
    private double amount;
}
