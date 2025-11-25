package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    //lấy sản phẩm được comment
    List<Review> findByProductId(Long productId); // ✅ Chuẩn
}
