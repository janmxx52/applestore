package com.example.AppleStore_Project_JavaSpringBoot_CoLy.model;

import jakarta.persistence.*;

@Entity
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long color_id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    // Constructors
    public Color() {}

    public Color(String name) {
        this.name = name;
    }

    // Getters & Setters
    public Long getId() { return color_id; }
    public void setId(Long id) { this.color_id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}