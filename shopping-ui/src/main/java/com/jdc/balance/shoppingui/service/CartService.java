package com.jdc.balance.shoppingui.service;

import com.jdc.balance.shoppingui.dto.CartItem;
import com.jdc.balance.shoppingui.dto.CartItemDto;
import com.jdc.balance.shoppingui.dto.OrderDetailsDto;
import com.jdc.balance.shoppingui.dto.UserInfoDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {
    private final ProductService productService;
    private final RestClient backendRestClient;

    public CartService(ProductService productService) {
        this.productService = productService;
        backendRestClient = RestClient.builder().baseUrl("http://localhost:8090/shopping/backend").build();
    }

    @Getter
    Set<CartItem> cartItems = new HashSet<>();

    public CartItem getCartItemByProductId(int productId) {
        var product = productService.getAllProducts()
                .stream()
                .filter(p -> p.getId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new CartItem(product.getId(), product.getName(), 1, product.getPrice());
    }

    public void addToCart(int productId) {
        if(cartItems.stream().anyMatch(item -> item.getProductId() == productId)) {
            increaseQuantity(productId);
        } else {
            var cartItem = getCartItemByProductId(productId);
            cartItems.add(cartItem);
        }
    }

    public void increaseQuantity(int productId) {
        cartItems.stream().filter(item -> item.getProductId() == productId)
                .findFirst()
                .ifPresent(CartItem::increaseQuantity);
    }

    public void decreaseQuantity(int productId) {
        cartItems.stream().filter(item -> item.getProductId() == productId)
                .findFirst()
                .ifPresent(CartItem::decreaseQuantity);
    }

    public double calculateTotalPrice() {
        return cartItems.stream()
                .map(item -> item.getPrice() * item.getQuantity())
                .reduce(0.0, Double::sum);
    }

    public int getCartSize() {
        return cartItems.size();
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void saveOrderDetails(UserInfoDto userInfo) {
        var orderDetailsDto = getOrderDetailsDto(userInfo);

        backendRestClient.post().uri("/save-order-details")
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderDetailsDto)
                .retrieve()
                .body(String.class);
    }

    public OrderDetailsDto getOrderDetailsDto(UserInfoDto userInfo) {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setName(userInfo.getName());
        orderDetailsDto.setEmail(userInfo.getEmail());
        orderDetailsDto.setPhone(userInfo.getPhoneNumber());
        orderDetailsDto.setAddress(userInfo.getAddress());

        getCartItems().forEach(item -> {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setName(item.getName());
            cartItemDto.setQuantity(item.getQuantity());
            cartItemDto.setPrice(item.getPrice());
            orderDetailsDto.addItem(cartItemDto);
        });
        return orderDetailsDto;
    }
}
