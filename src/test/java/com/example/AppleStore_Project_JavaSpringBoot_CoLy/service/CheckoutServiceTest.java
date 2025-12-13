package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.*;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckoutServiceTest {

    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    CartRepository cartRepository;
    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    ProductVariantRepository variantRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CheckoutService checkoutService;

    // ==============================
    // TC01: Checkout thành công
    // ==============================
    @Test
    void checkout_success() {

        User user = new User();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);

        Products product = new Products();
        product.setActive(true);

        ProductVariant variant = new ProductVariant();
        variant.setStock(10);
        variant.setProducts(product);

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(2);
        cartItem.setUnitPrice(BigDecimal.valueOf(100));
        cartItem.setVariant(variant);
        cartItem.setProducts(product);

        when(cartRepository.findByUserId(1L))
                .thenReturn(Optional.of(cart));

        when(cartItemRepository.findByCartId(1L))
                .thenReturn(List.of(cartItem));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(i -> i.getArgument(0));

        Order order = checkoutService.checkout(
                user,
                "Hau Dev",
                "0123456789",
                "HCM",
                "Test checkout",
                "bank"
        );

        assertNotNull(order);
        assertEquals(BigDecimal.valueOf(200), order.getTotal());
        assertEquals(OrderStatus.PENDING, order.getStatus());
        assertEquals("BANK", order.getPaymentType());

        verify(orderRepository, times(2)).save(any(Order.class));
        verify(cartItemRepository).deleteAll(anyList());
    }

    // ==============================
    // TC02: Giỏ hàng trống
    // ==============================
    @Test
    void checkout_fail_whenCartEmpty() {

        User user = new User();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);

        when(cartRepository.findByUserId(1L))
                .thenReturn(Optional.of(cart));

        when(cartItemRepository.findByCartId(1L))
                .thenReturn(List.of());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                checkoutService.checkout(
                        user,
                        "Hau Dev",
                        "0123",
                        "HCM",
                        "",
                        "cash"
                )
        );

        assertEquals("Giỏ hàng trống", ex.getMessage());

        verify(orderRepository, never()).save(any());
    }

    // ==============================
    // TC03: Tất cả variant hết hàng → product inactive
    // ==============================
    @Test
    void checkout_disableProduct_whenAllVariantOutOfStock() {

        User user = new User();
        user.setId(1L);

        Cart cart = new Cart();
        cart.setId(1L);

        Products product = new Products();
        product.setActive(true);

        ProductVariant variant = new ProductVariant();
        variant.setStock(0);
        variant.setProducts(product);

        product.setVariants(List.of(variant));

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setUnitPrice(BigDecimal.valueOf(50));
        cartItem.setVariant(variant);
        cartItem.setProducts(product);

        when(cartRepository.findByUserId(1L))
                .thenReturn(Optional.of(cart));

        when(cartItemRepository.findByCartId(1L))
                .thenReturn(List.of(cartItem));

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(i -> i.getArgument(0));

        checkoutService.checkout(
                user,
                "Hau Dev",
                "0123",
                "HCM",
                "",
                "cash"
        );

        assertFalse(product.getActive());
        verify(productRepository).save(product);
    }
}
