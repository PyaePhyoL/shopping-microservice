package com.jdc.balance.shoppingbackend.controller;

import com.jdc.balance.shoppingbackend.dto.ProductDto;
import com.jdc.balance.shoppingbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping/backend")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public List<ProductDto> getProductsByCategory(@PathVariable("id") int id) {
        return productService.getProductsByCategory(id);
    }
}
