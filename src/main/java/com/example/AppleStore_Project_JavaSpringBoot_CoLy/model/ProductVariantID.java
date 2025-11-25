package com.example.AppleStore_Project_JavaSpringBoot_CoLy.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductVariantID implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long product_id;
    private Long color_id;
    private Long capacity_id;

    public ProductVariantID() {
    }

    public ProductVariantID(Long product_id, Long color_id, Long capacity_id) {
        this.product_id = product_id;
        this.color_id = color_id;
        this.capacity_id = capacity_id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public Long getColor_id() {
        return color_id;
    }

    public void setColor_id(Long color_id) {
        this.color_id = color_id;
    }

    public Long getCapacity_id() {
        return capacity_id;
    }

    public void setCapacity_id(Long capacity_id) {
        this.capacity_id = capacity_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVariantID that = (ProductVariantID) o;
        return product_id == that.product_id && color_id == that.color_id && capacity_id == that.capacity_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, color_id, capacity_id);
    }
}
