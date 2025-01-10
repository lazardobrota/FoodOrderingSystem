package com.usermanagment.backend.mapper;

import com.usermanagment.backend.dto.dish.CreateDishDto;
import com.usermanagment.backend.dto.dish.DishAmountDto;
import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import com.usermanagment.backend.model.Dish;
import com.usermanagment.backend.model.DishIngredient;
import com.usermanagment.backend.model.Ingredient;
import com.usermanagment.backend.model.OrderDish;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishMapper {

    public Dish toDish(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setId(dishDto.getId());
        dish.setName(dishDto.getName());
        dish.setDescription(dishDto.getDescription());
        dish.setPrice(dishDto.getPrice());

        return dish;
    }

    public Dish toDish(CreateDishDto createDishDto) {
        Dish dish = new Dish();
        dish.setName(createDishDto.getName());
        dish.setDescription(createDishDto.getDescription());
        dish.setPrice(createDishDto.getPrice());

        return dish;
    }

    public DishDto toDishDto(Dish dish, List<IngredientDto> ingredients) {
        return new DishDto(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice(),
                ingredients
        );
    }

    public DishDto toDishDto(Dish dish) {
        return new DishDto(
                dish.getId(),
                dish.getName(),
                dish.getDescription(),
                dish.getPrice()
        );
    }

    public DishIngredient toDishIngredient(Dish dish, Ingredient ingredient) {
        DishIngredient dishIngredient = new DishIngredient();
        dishIngredient.setDish(dish);
        dishIngredient.setIngredient(ingredient);
        return dishIngredient;
    }

}
