package com.usermanagment.backend.dto.dish;

import com.usermanagment.backend.dto.ingredient.IngredientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

    private Long id;

    private String name;

    private String description;

    private int price;

    private List<IngredientDto> ingredients;

    public DishDto(Long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        ingredients = new ArrayList<>();
    }
}
