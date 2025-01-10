package com.usermanagment.backend.dto.dish;

import com.usermanagment.backend.dto.ingredient.IngredientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishDto {

    private String name;

    private String description;

    private int price;

    private List<IngredientDto> ingredients;

}
