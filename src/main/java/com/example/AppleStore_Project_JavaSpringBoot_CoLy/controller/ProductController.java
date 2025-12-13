package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller;


import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.*;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository.*;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductImageService;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ReviewService;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ProductImageService productImageService;


//
//    @GetMapping("/shop")
//    public String shop(Model model) {
//        model.addAttribute("LatestProducts",productService.getLatestProduct(5));
//        model.addAttribute("SaleProducts",productService.getSaleProduct(10));
//        return "ShopIphone";
//    }


    @GetMapping("/shop/iphone")
    public String showIphone(Model model) {
        model.addAttribute("CategoryIphone",productService.getProductByCategory("iphone"));
        return "ShopIPhoneTest";
    }

    @GetMapping("/shop/macbook")
    public String showMacbook(Model model) {
        model.addAttribute("CategoryMacbook",productService.getProductByCategory("mac"));
        return "ShopMacBook";
    }

    @GetMapping("/shop/applewatch")
    public String showApplewatch(Model model) {
        model.addAttribute("CategoryApplewatch",productService.getProductByCategory("watch"));
        return "ShopAppleWatch";
    }



//    //Show chi tiết sản phẩm
//    @GetMapping("/products/{id}")
//    public String productDetails(@PathVariable Long id, Model model) {
//        var product = productService.getProductById(id);
//
//
//        if (product == null) {
//            return "redirect:/";
//        }
//
//        var variants = productService.getVariantByProductId(id);
//
//        BigDecimal mainPrice = BigDecimal.ZERO;
//        if (variants != null && !variants.isEmpty()) {
//            BigDecimal p = variants.get(0).getPrice();
//            mainPrice = (p == null) ? BigDecimal.ZERO : p;
//        }
//
//        List<Color> colors = variants.stream()
//                .map(ProductVariant::getColor)
//                .filter(Objects::nonNull)
//                .distinct()
//                .toList();
//
//        List<Capacity> capacities = variants.stream()
//                .map(ProductVariant::getCapacity)
//                .filter(Objects::nonNull)
//                .distinct()
//                .toList();
//
//
//        var reviews = reviewRepository.findByProductId(id);
//        List<Review> reviewList = reviews.stream()
//                .map(Review ::getProductId)
//                .filter(Objects::nonNull)
//                .distinct()
//                .toList();
//
//        model.addAttribute("product", product);
//        model.addAttribute("mainPrice", mainPrice);
//        model.addAttribute("colors", colors);
//        model.addAttribute("capacities", capacities);
//        model.addAttribute("variants", variants);
//        model.addAttribute("reviews", reviews);
//
//        return "ProductDetails";
//    }






    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable Long id, Model model) {
        var product = productService.getProductById(id);

        if (product == null) {
            return "redirect:/";
        }

        var variants = productService.getVariantByProductId(id);

        BigDecimal mainPrice = BigDecimal.ZERO;
        if (variants != null && !variants.isEmpty()) {
            BigDecimal p = variants.get(0).getPrice();
            mainPrice = (p == null) ? BigDecimal.ZERO : p;
        }

        List<Color> colors = variants.stream()
                .map(ProductVariant::getColor)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        List<Capacity> capacities = variants.stream()
                .map(ProductVariant::getCapacity)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        List<Review> reviews = reviewService.findByProductId(id);


        double avgRating = reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0.0);


        var images = productImageService.getImagesByProduct(id);

        model.addAttribute("product", product);
        model.addAttribute("mainPrice", mainPrice);
        model.addAttribute("colors", colors);
        model.addAttribute("capacities", capacities);
        model.addAttribute("variants", variants);
        model.addAttribute("reviews", reviews);
        model.addAttribute("avgRating", avgRating);
        model.addAttribute("totalReviews", reviews.size());
        model.addAttribute("images", images);

        return "ProductDetails";

    }





    //test lấu sản phẩm iphone
//    @Autowired
//    ProductService productService;
//
//    @GetMapping("/iphone")
//    public List<Products> getiphone(){
//        return productService.getProductByCategory("iphone");
//
//    }



//    @GetMapping("/shopiphone")
//    public String shopiphone(Model model) {
//        return "ShopIphone";
//    }








}
