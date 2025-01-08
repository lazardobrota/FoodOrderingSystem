package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.ingredient.CreateIngredientDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import com.usermanagment.backend.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {


    public Ingredient toIngredient(CreateIngredientDto createIngredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(createIngredientDto.getName());
        return ingredient;
    }

    public Ingredient toIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDto.getId());
        ingredient.setName(ingredientDto.getName());
        return ingredient;
    }

    public IngredientDto toIngredientDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getName());
    }

    public Ingredient updateIngredient(Ingredient ingredient, IngredientDto ingredientDto) {
        ingredient.setName(ingredientDto.getName());
        return ingredient;
    }
}
