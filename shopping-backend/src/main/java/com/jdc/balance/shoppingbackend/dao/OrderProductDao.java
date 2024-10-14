package com.jdc.balance.shoppingbackend.dao;

import com.jdc.balance.shoppingbackend.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {
}
