package com.example.AppleStore_Project_JavaSpringBoot_CoLy.controller.testing;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Products;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class TestGetCategoryMacbook {

    @Autowired
    private ProductService productService;

//test lấy sản phẩm theo tên
    @GetMapping("/macbook")
    public List<Products> getMacProducts() {
        return productService.getProductByCategory("mac");
    }


    //lấy slug trong category(slug là từ viết tắt VD: Mac-> mac)
    @GetMapping("/category/{slug}")
    public List<Products> getProductsByCategory(@PathVariable String slug) {
        return productService.getProductByCategory(slug);
    }
}
