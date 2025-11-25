package com.example.AppleStore_Project_JavaSpringBoot_CoLy.repository;

import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.ProductImage;
import com.example.AppleStore_Project_JavaSpringBoot_CoLy.model.Products;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    //Lấy sản phẩm mới nhất

    @Query("SELECT p FROM Products p ORDER BY p.sku DESC")
    List<Products> findLatestProducts(Pageable pageable);

    @Query("SELECT p FROM Products p WHERE p.discount > 0 ORDER BY p.discount DESC")
    List<Products> findSaleProduct(Pageable pageable);



    //Chỉ lấy sản phẩm Iphone

    @Query("SELECT p FROM Products p WHERE p.category.name =:categoryName")
    List<Products> findByCategoryName(@Param("categoryName") String categoryName);







}
