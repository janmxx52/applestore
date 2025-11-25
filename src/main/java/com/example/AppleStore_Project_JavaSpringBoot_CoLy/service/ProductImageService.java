package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductImage;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImageRepository;
    public List<ProductImage> getImagesByProduct(Long productId) {
        return productImageRepository.findByProductId(productId);
    }
}
