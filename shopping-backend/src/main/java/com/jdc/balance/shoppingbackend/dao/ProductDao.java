package com.jdc.balance.shoppingbackend.dao;

import com.jdc.balance.shoppingbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query("""
    select p from Product p where p.category.id = :id
""")
    Optional<List<Product>> findProductsByCategoryId(@Param("id") int id);
}
