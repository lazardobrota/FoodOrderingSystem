package com.usermanagment.backend.controller;

import com.usermanagment.backend.dto.ingredient.CreateIngredientDto;
import com.usermanagment.backend.dto.ingredient.IngredientDto;
import com.usermanagment.backend.service.impl.IngredientService;
import com.usermanagment.backend.utils.ExceptionUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(ingredientService.getAll()));
    }

    @PostMapping
    public ResponseEntity<IngredientDto> create(@RequestBody CreateIngredientDto ingredientDto) {
        return ExceptionUtils.handleResponse(() -> new ResponseEntity<>(ingredientService.createIngredient(ingredientDto), HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<IngredientDto> update(@RequestBody IngredientDto ingredientDto) {
        return ExceptionUtils.handleResponse(() -> ResponseEntity.ok(ingredientService.updateIngredient(ingredientDto)));
    }
}
