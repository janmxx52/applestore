package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.User;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.CustomUserDetails;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
//
//    @PostMapping("/add")
//    public String addReview(@RequestParam("productId") Long productId,
//                            @RequestParam("rating") int rating,
//                            @RequestParam("content") String content,
//                            RedirectAttributes redirectAttributes) {
//        Long mockUserId = 1L;
//        productService.saveReview(mockUserId, productId, rating, content);
//        redirectAttributes.addFlashAttribute("success", "Cảm ơn bạn đã gửi đánh giá!");
//        return "redirect:/products/" + productId;
//    }

    @PostMapping("/add")
    public String addReview(@RequestParam("productId") Long productId,
                            @RequestParam("rating") int rating,
                            @RequestParam("content") String content,
                            @AuthenticationPrincipal CustomUserDetails userDetails,
                            RedirectAttributes redirectAttributes) {


        if (userDetails == null) {
            redirectAttributes.addFlashAttribute("error", "Bạn cần đăng nhập để gửi đánh giá!");
            return "redirect:/login";
        }

        User currentUser = userDetails.getUser();

        productService.saveReview(
                currentUser.getId(),
                productId,
                rating,
                content
        );

        redirectAttributes.addFlashAttribute("success", "Cảm ơn bạn đã gửi đánh giá!");
        return "redirect:/products/" + productId;
    }
}


