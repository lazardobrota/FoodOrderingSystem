package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.ingredient.CreateIngredientDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IIngredientService {

    List<IngredientDto> getAll();
    IngredientDto createIngredient(CreateIngredientDto ingredientDto);
    IngredientDto updateIngredient(IngredientDto ingredientDto);
}
