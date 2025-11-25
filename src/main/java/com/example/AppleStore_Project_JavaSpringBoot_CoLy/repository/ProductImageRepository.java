package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    List<ProductImage> findByProductId(Long productId);

}
