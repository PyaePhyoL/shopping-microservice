package com.jdc.balance.shoppingbackend.controller;

import com.jdc.balance.shoppingbackend.dto.OrderDetailsDto;
import com.jdc.balance.shoppingbackend.dto.ProductDto;
import com.jdc.balance.shoppingbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/save-order-details")
    public ResponseEntity<String> saveOrderDetails(@RequestBody OrderDetailsDto orderDetailsDto) {
        productService.saveOrderDetails(orderDetailsDto);
        return ResponseEntity.ok("Order details saved");
    }
}
