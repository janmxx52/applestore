package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller.testing;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductVariant;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/variants")
public class TestProductDetails {
    @Autowired
    private ProductVariantRepository productVariantRepository;

    @GetMapping("/{productId}")
    public List<ProductVariant> getVariantByProductId(@PathVariable("productId") Long product_id){
        return productVariantRepository.findByProductWithDetail(product_id);
    }
}
