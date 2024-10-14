package com.jdc.balance.shoppingbackend.service;

import com.jdc.balance.shoppingbackend.dao.ProductDao;
import com.jdc.balance.shoppingbackend.dto.ProductDto;
import com.jdc.balance.shoppingbackend.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<ProductDto> getAllProducts() {
        return productDao.findAll().stream().map(EntityUtil::toProductDto).toList();
    }

    public List<ProductDto> getProductsByCategory(int categoryId) {
        return productDao.findProductsByCategoryId(categoryId).get().stream().map(EntityUtil::toProductDto).toList();
    }

    public ProductDto getProductById(Integer id) {
        return EntityUtil.toProductDto(productDao.findById(id).get());
    }
}
