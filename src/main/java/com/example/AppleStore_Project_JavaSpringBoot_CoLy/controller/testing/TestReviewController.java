package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller.testing;


import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Review;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class TestReviewController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{productId}")
    public List<Review> getReview(@PathVariable Long productId) {
        List<Review> reviews = productService.getReviewByProduct(productId);
        System.out.println("✅ Found " + reviews.size() + " reviews for product " + productId);
        return reviews;
    }
}

