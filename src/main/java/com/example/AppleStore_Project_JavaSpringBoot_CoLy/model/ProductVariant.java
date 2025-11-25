package com.example.AppleStore_Project_JavaSpringBoot_CoLy.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "product_variants",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"product_id", "color_id", "capacity_id"})
        }
)
public class ProductVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Mối quan hệ với bảng products
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Products products;

    // 🔗 Mối quan hệ với bảng colors (màu sắc)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    // 🔗 Mối quan hệ với bảng capacities (dung lượng)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capacity_id")
    private Capacity capacity;

    // 💰 Giá bán thực tế của biến thể
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    // 📦 Số lượng tồn kho
    @Column(nullable = false)
    private Integer stock = 0;

    public ProductVariant() {}

    public ProductVariant(Products products, Color color, Capacity capacity, BigDecimal price, Integer stock) {
        this.products = products;
        this.color = color;
        this.capacity = capacity;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}