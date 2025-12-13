package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Cart;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.CartItem;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.CustomUserDetails;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    // 🧺 Hiển thị giỏ hàng
    @GetMapping("/view")
    public String viewCart(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }

        User user = userDetails.getUser();
        List<CartItem> items = productService.getCartItems(user);
        BigDecimal total = productService.getTotal(user);

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(items);

        model.addAttribute("cart", cart);
        model.addAttribute("cartItems", items);
        model.addAttribute("total", total);

        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId,
                            @RequestParam(required = false) Long variantId,
                            @RequestParam(defaultValue = "1") int quantity,
                            @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        productService.addToCart(userDetails.getUser(), productId, variantId, quantity);
        return "redirect:/cart/view";
    }

    // Cập nhật số lượng
    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long itemId, @RequestParam int quantity) {
        productService.updateQuantity(itemId, quantity);
        return "redirect:/cart/view";
    }

    // Xóa sản phẩm
    @PostMapping("/remove")
    public String removeItem(@RequestParam Long itemId) {
        productService.removeItem(itemId);
        return "redirect:/cart/view";
    }
}
