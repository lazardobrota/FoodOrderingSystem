package com.usermanagment.backend.dto.dish;

import com.usermanagment.backend.dto.ingredient.IngredientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishDto {

    private int id;

    private String name;

    private int amount;

    private List<IngredientDto> ingredients;
}
