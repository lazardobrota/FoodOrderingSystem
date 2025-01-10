package com.usermanagment.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ingredient")
@NoArgsConstructor
public class Ingredient {
    //Todo change name to Accompaniment 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }
}
