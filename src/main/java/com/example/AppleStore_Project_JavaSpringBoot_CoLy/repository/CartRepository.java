package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);
}
