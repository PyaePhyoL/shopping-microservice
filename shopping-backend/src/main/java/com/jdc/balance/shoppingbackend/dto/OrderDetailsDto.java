package com.jdc.balance.shoppingbackend.dto;

import com.jdc.balance.shoppingbackend.entity.Product;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDetailsDto {
    private Integer id;
    private String customerName;
    private Set<Product> products = new HashSet<>();

}
