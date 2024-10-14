package com.jdc.balance.shoppingbackend.util;

import com.jdc.balance.shoppingbackend.dto.OrderDetailsDto;
import com.jdc.balance.shoppingbackend.dto.ProductDto;
import com.jdc.balance.shoppingbackend.entity.OrderDetails;
import com.jdc.balance.shoppingbackend.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityUtil {

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product toProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    public static OrderDetailsDto toOrderDetailsDto(OrderDetails orderDetails) {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        BeanUtils.copyProperties(orderDetails, orderDetailsDto);
        return orderDetailsDto;
    }

    public static OrderDetails toOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetails orderDetails = new OrderDetails();
        BeanUtils.copyProperties(orderDetailsDto, orderDetails);
        return orderDetails;
    }
}
