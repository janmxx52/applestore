package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //trừ stock
    @Query("""
    SELECT ci FROM CartItem ci
    LEFT JOIN FETCH ci.products p
    LEFT JOIN FETCH ci.variant v
    LEFT JOIN FETCH v.color
    LEFT JOIN FETCH v.capacity
    WHERE ci.cart.id = :cartId
""")
    List<CartItem> findByCartId(@Param("cartId") Long cartId);


}
