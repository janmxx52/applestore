package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long CartId);
}
