package com.usermanagment.backend.service.impl;

import com.usermanagment.backend.dto.dish.CreateDishDto;
import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import com.usermanagment.backend.exception.FoodException;
import com.usermanagment.backend.mapper.DishMapper;
import com.usermanagment.backend.mapper.IngredientMapper;
import com.usermanagment.backend.model.Dish;
import com.usermanagment.backend.model.DishIngredient;
import com.usermanagment.backend.repository.IDishIngredientRepo;
import com.usermanagment.backend.repository.IDishRepo;
import com.usermanagment.backend.service.IDishService;
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
public class DishService implements IDishService {

    private final IDishRepo dishRepo;
    private final IDishIngredientRepo dishIngredientRepo;
    private final DishMapper dishMapper;
    private final IngredientMapper ingredientMapper;

    @Override
    public Page<DishDto> getAll(Pageable pageable) {
        return dishRepo.findAll(pageable).map(dish ->  dishMapper.toDishDto(dish, getIngredients(dish)));
    }

    @Override
    public DishDto getDishById(int id) {
        return dishRepo.findById(id)
                .map(dish -> dishMapper.toDishDto(dish, getIngredients(dish)))
                .orElseThrow(() -> new FoodException("Dish not found with given id", HttpStatus.BAD_REQUEST));
    }

    @Override
    public DishDto createDish(CreateDishDto createDishDto) {
        Dish dish = dishRepo.save(dishMapper.toDish(createDishDto));
        List<DishIngredient> dishIngredients = createDishDto.getIngredients().stream()
                .map(ingredientMapper::toIngredient)
                .map(ingredient -> dishMapper.toDishIngredient(dish, ingredient))
                .toList();

        dishIngredients = dishIngredientRepo.saveAll(dishIngredients);
        return dishMapper.toDishDto(dish, getIngredients(dishIngredients));
    }

    private List<IngredientDto> getIngredients(Dish dish) {
        return dishIngredientRepo.findAllIngredientsOfDish(dish.getId()).stream()
                .map(DishIngredient::getIngredient)
                .map(ingredientMapper::toIngredientDto)
                .toList();
    }

    private List<IngredientDto> getIngredients(List<DishIngredient> dishIngredient) {
        return dishIngredient.stream()
                .map(DishIngredient::getIngredient)
                .map(ingredientMapper::toIngredientDto)
                .toList();
    }
}
