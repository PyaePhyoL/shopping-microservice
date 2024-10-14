package com.jdc.balance.shoppingui.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class RequestDataDto {
    private @JsonProperty("credit_number") String creditNumber;
    private String name;
    private double amount;
}
