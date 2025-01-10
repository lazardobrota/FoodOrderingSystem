package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.dish.CreateDishDto;
import com.usermanagment.backend.dto.dish.DishDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import com.usermanagment.backend.service.impl.DishService;
import com.usermanagment.backend.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @GetMapping
    public ResponseEntity<Page<DishDto>> findAll(Pageable pageable) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(dishService.getAll(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDto> findById(@PathVariable("id") Long id) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(dishService.getDishById(id)));
    }

    @PostMapping
    public ResponseEntity<DishDto> create(@RequestBody CreateDishDto createDishDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(dishService.createDish(createDishDto), HttpStatus.CREATED));
    }

//    @PutMapping
//    public ResponseEntity<DishDto> update(@RequestBody DishDto dishDto) {
//        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(dishService.updateDish(dishDto)));
//    }
}
