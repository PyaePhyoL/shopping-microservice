package com.jdc.balance.shoppingbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDetailsDto {
    private int orderNumber;
    private Set<CartItemDto> productList=
            new HashSet<>();
    private String name;
    private String address;
    private String email;
    private String phone;
}
