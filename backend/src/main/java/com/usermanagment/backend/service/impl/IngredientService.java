package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.ingredient.CreateIngredientDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import com.usermanagment.backend.exception.FoodException;
import com.usermanagment.backend.mapper.IngredientMapper;
import com.usermanagment.backend.mapper.UserMapper;
import com.usermanagment.backend.model.Ingredient;
import com.usermanagment.backend.repository.IIngredientRepo;
import com.usermanagment.backend.service.IIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientService implements IIngredientService {

    private final IIngredientRepo ingredientRepo;
    private final IngredientMapper ingredientMapper;

    @Override
    public List<IngredientDto> getAll() {
        return ingredientRepo.findAll().stream().map(ingredientMapper::toIngredientDto).toList();
    }

    public IngredientDto createIngredient(CreateIngredientDto createIngredientDto) {
        return ingredientMapper.toIngredientDto(ingredientRepo.save(ingredientMapper.toIngredient(createIngredientDto)));
    }

    @Override
    public IngredientDto updateIngredient(IngredientDto ingredientDto) {
        Ingredient ingredient = ingredientRepo.findById(ingredientDto.getId()).orElseThrow(() -> new FoodException("Ingredient not found with given id", HttpStatus.BAD_REQUEST));
        ingredient = ingredientMapper.updateIngredient(ingredient, ingredientDto);
        return ingredientMapper.toIngredientDto(ingredientRepo.save(ingredient));
    }

}
