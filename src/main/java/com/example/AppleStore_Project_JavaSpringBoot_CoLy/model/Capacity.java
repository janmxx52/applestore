package com.example.AppleStore_Project_JavaSpringBoot_CoLy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "capacities")
public class Capacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long capacity_id;

    @Column(nullable = false, unique = true, length = 50)
    private String label; // ví dụ: "256GB"

    private Integer gb; // giá trị số tương ứng

    // Constructors
    public Capacity() {}

    public Capacity(String label, Integer gb) {
        this.label = label;
        this.gb = gb;
    }

    // Getters & Setters
    public Long getId() { return capacity_id; }
    public void setId(Long id) { this.capacity_id = id; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Integer getGb() { return gb; }
    public void setGb(Integer gb) { this.gb = gb; }
}
