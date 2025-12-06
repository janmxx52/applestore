package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.CartItem;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Products;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.CustomUserDetails;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc (addFilters = false)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void testViewCart_Unauthenticated() throws Exception {
        mockMvc.perform(get("/cart/view"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void testViewCart_Authenticated() throws Exception {

        // Fake user
        User fakeUser = new User();
        fakeUser.setId(1L);
        fakeUser.setUsername("hau");

        // Fake CustomUserDetails
        CustomUserDetails customUserDetails = new CustomUserDetails(fakeUser);

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        customUserDetails,
                        null,
                        customUserDetails.getAuthorities()
                );

        SecurityContextHolder.getContext().setAuthentication(auth);

        // Fake product
        Products fakeProduct = new Products();
        fakeProduct.setId(99L);
        fakeProduct.setName("Macbook Fake");
        fakeProduct.setImage("fake.png");

        // Fake cart item
        CartItem item = new CartItem();
        item.setId(10L);
        item.setProducts(fakeProduct);  // 🔥 VERY IMPORTANT

        Mockito.when(productService.getCartItems(any(User.class)))
                .thenReturn(List.of(item));

        Mockito.when(productService.getTotal(any(User.class)))
                .thenReturn(new BigDecimal("12990000"));

        mockMvc.perform(get("/cart/view"))
                .andExpect(status().isOk())
                .andExpect(view().name("cart"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attributeExists("cartItems"))
                .andExpect(model().attributeExists("total"));
    }
}