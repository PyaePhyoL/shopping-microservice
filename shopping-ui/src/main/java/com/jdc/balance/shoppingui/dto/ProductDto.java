package com.jdc.balance.shoppingui.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime lastUpdate;
    private CategoryDto category;
}

