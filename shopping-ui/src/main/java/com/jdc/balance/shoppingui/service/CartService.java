package com.jdc.balance.shoppingui.service;

import com.jdc.balance.shoppingui.dto.CartItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
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
}
