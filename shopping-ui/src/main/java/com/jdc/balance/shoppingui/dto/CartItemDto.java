package com.jdc.balance.shoppingui.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private String name;
    private double price;
    private int quantity;
}
