package com.jdc.balance.shoppingbackend.service;

import com.jdc.balance.shoppingbackend.dao.OrderDetailsDao;
import com.jdc.balance.shoppingbackend.dao.OrderProductDao;
import com.jdc.balance.shoppingbackend.dao.ProductDao;
import com.jdc.balance.shoppingbackend.dto.OrderDetailsDto;
import com.jdc.balance.shoppingbackend.dto.ProductDto;
import com.jdc.balance.shoppingbackend.entity.OrderDetails;
import com.jdc.balance.shoppingbackend.entity.OrderProduct;
import com.jdc.balance.shoppingbackend.util.EntityUtil;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;
    private final OrderDetailsDao orderDetailsDao;
    private final OrderProductDao orderProductDao;

    public List<ProductDto> getAllProducts() {
        return productDao.findAll().stream().map(EntityUtil::toProductDto).toList();
    }

    public List<ProductDto> getProductsByCategory(int categoryId) {
        return productDao.findProductsByCategoryId(categoryId).get().stream().map(EntityUtil::toProductDto).toList();
    }

    public void saveOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setId(orderDetailsDto.getOrderNumber());
        orderDetails.setCustomerName(orderDetailsDto.getName());
        orderDetails.setCustomerEmail(orderDetailsDto.getEmail());
        orderDetails.setCustomerPhone(orderDetailsDto.getPhone());
        orderDetails.setCustomerAddress(orderDetailsDto.getAddress());

        for(var cartItem : orderDetailsDto.getProductList()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProductName(cartItem.getName());
            orderProduct.setPrice(cartItem.getPrice());
            orderProduct.setQuantity(cartItem.getQuantity());
            orderDetails.addProduct(orderProduct);
            orderProductDao.save(orderProduct);
        }

        orderDetailsDao.save(orderDetails);
    }
}
