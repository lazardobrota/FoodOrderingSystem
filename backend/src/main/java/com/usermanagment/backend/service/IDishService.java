package com.usermanagment.backend.service;

import com.usermanagment.backend.dto.dish.CreateDishDto;
import com.usermanagment.backend.dto.dish.DishDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDishService {

    Page<DishDto> getAll(Pageable pageable);
    DishDto getDishById(int id);
    DishDto createDish(CreateDishDto createDishDto);
}
