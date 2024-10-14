package com.jdc.balance.shoppingbackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private String name;
    private double price;
    private int quantity;
}
