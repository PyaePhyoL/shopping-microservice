package com.jdc.balance.shoppingbackend.dao;

import com.jdc.balance.shoppingbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
}
