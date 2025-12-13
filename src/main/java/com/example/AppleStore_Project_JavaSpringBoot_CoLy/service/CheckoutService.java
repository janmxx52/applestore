// CheckoutService.java
package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.*;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckoutService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductVariantRepository variantRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order checkout(User user,
                          String fullName,
                          String phone,
                          String address,
                          String note,
                          String paymentType) {

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giỏ hàng"));

        List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Giỏ hàng trống");
        }

        BigDecimal total = cartItems.stream()
                .map(ci -> ci.getUnitPrice().multiply(BigDecimal.valueOf(ci.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = new Order();
        order.setUser(user);
        order.setFullName(fullName);
        order.setPhone(phone);
        order.setAddress(address);
        order.setNote(note);
        order.setTotal(total);
        order.setPaymentType(paymentType.equalsIgnoreCase("bank") ? "BANK" : "CASH");
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        order.setItems(new ArrayList<>());

        orderRepository.save(order);


        for (CartItem ci : cartItems) {

            OrderItem oi = new OrderItem();
            oi.setOrder(order);
            oi.setProduct(ci.getProducts());
            oi.setVariant(ci.getVariant());
            oi.setQuantity(ci.getQuantity());
            oi.setUnitPrice(ci.getUnitPrice());
            oi.calcSubtotal();

            order.getItems().add(oi);

            ProductVariant variant = ci.getVariant();

            if (variant != null) {

                int oldStock = variant.getStock();
                int newStock = oldStock - ci.getQuantity();
                if (newStock < 0) newStock = 0;

//                variant.setStock(newStock);
//                variantRepository.save(variant);
//
//                System.out.println("STOCK UPDATED");
//                System.out.println("Variant: " + variant.getId());
//                System.out.println("Old: " + oldStock);
//                System.out.println("Qty: " + ci.getQuantity());
//                System.out.println("New: " + newStock);

                Products p = variant.getProducts();
                boolean allZero = p.getVariants().stream()
                        .allMatch(v -> v.getStock() == null || v.getStock() <= 0);

                if (allZero) {
                    p.setActive(false);
                    productRepository.save(p);
                }
            }
        }

        orderRepository.save(order);

        cartItemRepository.deleteAll(cartItems);

        return order;
    }
}