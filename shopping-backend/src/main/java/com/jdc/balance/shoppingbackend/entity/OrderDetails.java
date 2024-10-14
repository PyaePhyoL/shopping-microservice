package com.jdc.balance.shoppingbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private String customerAddress;
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<OrderProduct> products = new HashSet<>();

    public void addProduct(OrderProduct product) {
        this.products.add(product);
    }

}

