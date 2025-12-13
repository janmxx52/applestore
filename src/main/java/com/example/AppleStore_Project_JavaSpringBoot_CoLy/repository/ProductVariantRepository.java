package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.CartItem;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductVariant;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductVariantID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {

    // lấy chi tiết sản phẩm
    @Query("""
       SELECT v FROM ProductVariant v
       JOIN FETCH v.products p
       LEFT JOIN FETCH v.color c
       LEFT JOIN FETCH v.capacity cap
       WHERE p.id = :products_id
       """)
    List<ProductVariant> findByProductWithDetail(@Param("products_id") Long products_id);







}
