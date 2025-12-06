package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.config.TestSecurityConfig;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.CustomUserDetails;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Import(TestSecurityConfig.class)

@WebMvcTest(CartController.class)
class CartControllerTest_Add_Update_Remove {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductService productService;


    // 1) TEST /cart/add

    @Test
    void testAddToCart_Unauthenticated() throws Exception {
        mockMvc.perform(post("/cart/add")
                        .param("productId", "1")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }


    @Test
    void testAddToCart_Authenticated() throws Exception {
        User u = new User();
        u.setId(1L);
        u.setUsername("hau");

        CustomUserDetails details = new CustomUserDetails(u);
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(post("/cart/add")
                        .param("productId", "1")
                        .param("quantity", "2")
                        .with(csrf()))                // FIX
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));

        Mockito.verify(productService)
                .addToCart(any(User.class), eq(1L), isNull(), eq(2));
    }



    // 2) TEST /cart/update

    @Test
    void testUpdateCart() throws Exception {
        mockMvc.perform(post("/cart/update")
                        .param("itemId", "10")
                        .param("quantity", "5")
                        .with(csrf()))         // FIX
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));

        Mockito.verify(productService).updateQuantity(10L, 5);
    }



    // 3) TEST /cart/remove

    @Test
    void testRemoveItem() throws Exception {
        mockMvc.perform(post("/cart/remove")
                        .param("itemId", "10")
                        .with(csrf()))         // FIX
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cart/view"));

        Mockito.verify(productService).removeItem(10L);
    }
}
