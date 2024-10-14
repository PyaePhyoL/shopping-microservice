package com.jdc.balance.shoppingui.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class OrderDetailsDto {
    private String orderNumber;
    private Set<CartItemDto> productDtoList=
            new HashSet<>();
    private String name;
    private String address;
    private String email;
    private String phone;
}
