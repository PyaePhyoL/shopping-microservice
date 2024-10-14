package com.jdc.balance.shoppingbackend.dto;

import com.jdc.balance.shoppingbackend.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private LocalDateTime lastUpdate;
    private Category category;
}
