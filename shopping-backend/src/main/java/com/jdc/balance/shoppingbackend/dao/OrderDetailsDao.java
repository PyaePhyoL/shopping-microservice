package com.jdc.balance.shoppingbackend.dao;

import com.jdc.balance.shoppingbackend.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsDao extends JpaRepository<OrderDetails, Integer> {
}
