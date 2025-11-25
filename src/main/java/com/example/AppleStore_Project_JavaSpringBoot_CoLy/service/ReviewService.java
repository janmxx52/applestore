package com.example.AppleStore_Project_JavaSpringBoot_CoLy.service;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Products;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Review;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.ProductRepository;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.ReviewRepository;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageService productImageService;

    // Hàm này chính là cái mà Controller đang gọi
    public List<Review> findByProductId(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    // Có thể thêm luôn hàm tính rating trung bình nếu cần
    public double getAverageRating(Long productId) {
        List<Review> reviews = reviewRepository.findByProductId(productId);
        return reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
    }

    public void addReview(Long userId, Long productId, int rating, String content) {
        Review review = new Review();
        review.setUserId(userId);
        review.setProductId(productId);
        review.setRating(rating);
        review.setContent(content);
        review.setLikes(0);
        review.setCreatedAt(LocalDate.now());
        reviewRepository.save(review);
    }


}


