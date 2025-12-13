// CheckoutController.java
package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.CartItem;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Order;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.CustomUserDetails;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class CheckoutController {

    private final ProductService productService;
    private final CheckoutService checkoutService;
    private final QrCodeService qrCodeService;
    private final EmailService emailService;

    @Value("${bank.account.number}")
    private String bankAccountNumber;

    @Value("${bank.account.name}")
    private String bankAccountName;

    @Value("${bank.name}")
    private String bankName;

    public CheckoutController(ProductService productService,
                              CheckoutService checkoutService,
                              QrCodeService qrCodeService,
                              EmailService emailService) {
        this.productService = productService;
        this.checkoutService = checkoutService;
        this.qrCodeService = qrCodeService;
        this.emailService = emailService;
    }

    // ====================== HIỂN THỊ TRANG CHECKOUT ======================
    @GetMapping("/checkout")
    public String showCheckout(@AuthenticationPrincipal CustomUserDetails userDetails,
                               Model model) {

        User user = userDetails.getUser();

        List<CartItem> items = productService.getCartItems(user);
        BigDecimal total = productService.getTotal(user);

        model.addAttribute("items", items);
        model.addAttribute("total", total);

        return "checkout";
    }

    // ====================== HANDLE CHECKOUT ======================
    @PostMapping("/checkout")
    public String handleCheckout(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam String fullname,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam(required = false) String note,
            @RequestParam String paymentType,
            Model model
    ) {

        User user = userDetails.getUser();

        // Tạo đơn hàng
        Order order = checkoutService.checkout(
                user, fullname, phone, address, note, paymentType
        );

        // Gửi email xác nhận
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            emailService.sendOrderConfirmation(user.getEmail(), order);
        }

        // QR PAYMENT
        String qrDataUrl = null;
        if (paymentType.equalsIgnoreCase("bank")) {

            qrDataUrl = qrCodeService.generateBankQR(
                    bankAccountNumber,
                    bankAccountName,
                    bankName,
                    order.getTotal(),
                    "ORDER_" + order.getId()
            );
        }

        // ======== TRUYỀN DỮ LIỆU RA MÀN HÌNH ========
        model.addAttribute("order", order);
        model.addAttribute("items", order.getItems());
        model.addAttribute("qrDataUrl", qrDataUrl);

        model.addAttribute("bankAccountNumber", bankAccountNumber);
        model.addAttribute("bankAccountName", bankAccountName);
        model.addAttribute("bankName", bankName);

        return "order_success";
    }
}
