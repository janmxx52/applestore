package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.config.TestSecurityConfig;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductImage;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Products;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductImageService;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
class ProductControllerTest_ViewDetails {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ReviewService reviewService;

    @MockBean
    private ProductImageService productImageService;

    @Test
    @DisplayName("Xem chi tiết sản phẩm với id hợp lệ")
    void viewProductDetail_ExistingId_ShouldReturnProductDetailPage() throws Exception {

        Products product = new Products();
        product.setId(1L);
        product.setName("iPhone 16 Pro");
        product.setPrice(new BigDecimal("3099999"));

        given(productService.getVariantByProductId(1L)).willReturn(List.of());

        given(reviewService.findByProductId(1L)).willReturn(List.of());

        ProductImage img = new ProductImage();
        img.setImageUrl("/images/demo.jpg");

        given(productImageService.getImagesByProduct(1L))
                .willReturn(List.of(img));

        given(productService.getProductById(1L)).willReturn(product);

        mockMvc.perform(get("/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("ProductDetails"))
                .andExpect(model().attributeExists("product"))
                .andExpect(model().attributeExists("images"));
    }


    @Test
    @DisplayName("ID không tồn tại → redirect về trang chủ")
    void viewProductDetail_NotFound_ShouldRedirect() throws Exception {

        given(productService.getProductById(999L)).willReturn(null);

        mockMvc.perform(get("/products/{id}", 999L))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
