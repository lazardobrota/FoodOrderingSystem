package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.Dish;
import com.usermanagment.backend.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IIngredientRepo extends JpaRepository<Ingredient, Long>, JpaSpecificationExecutor<Ingredient> {
    Optional<Ingredient> findById(long id);
    Optional<Ingredient> findByName(String name);
}
