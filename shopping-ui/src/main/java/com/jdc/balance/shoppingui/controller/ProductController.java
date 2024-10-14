package com.jdc.balance.shoppingui.controller;

import com.jdc.balance.shoppingui.dto.ProductDto;
import com.jdc.balance.shoppingui.dto.UserInfoDto;
import com.jdc.balance.shoppingui.service.CartService;
import com.jdc.balance.shoppingui.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shopping/ui")
public class ProductController {
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @GetMapping("/view-product")
    public String getProductsByCategoryId(Model model, @RequestParam("id") int id) {
        model.addAttribute("products", productService.getProductByCategoryId(id));
        return "products";
    }

    @GetMapping("/view-cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "cartView";
    }

//    /shopping/ui/add-to-cart/' + ${product.id}
    @GetMapping("/add-to-cart/{productId}")
    public String addToCart(@PathVariable("productId") int productId, Model model) {
        cartService.addToCart(productId);
        return "redirect:/shopping/ui/view-cart";
    }
    // /shopping/ui/increase-qty/
    @GetMapping("/increase-qty/{productId}")
    public String increaseQty(@PathVariable("productId") int productId, Model model) {
        cartService.increaseQuantity(productId);
        return "redirect:/shopping/ui/view-cart";
    }

    @GetMapping("/decrease-qty/{productId}")
    public String decreaseQty(@PathVariable("productId") int productId, Model model) {
        cartService.decreaseQuantity(productId);
        return "redirect:/shopping/ui/view-cart";
    }

    @GetMapping("/clear-cart")
    public String clearCart(Model model) {
        cartService.clearCart();
        return "redirect:/shopping/ui/view-cart";
    }

    @ModelAttribute("totalPrice")
    public double getTotalPrice() {
        return cartService.calculateTotalPrice();
    }

    @ModelAttribute("cartSize")
    public int getCartSize() {
        return cartService.getCartSize();
    }

    @GetMapping("/checkout")
    public String viewCheckOut(Model model) {
        model.addAttribute("userInfo", new UserInfoDto());
        return "checkoutView";
    }
}
