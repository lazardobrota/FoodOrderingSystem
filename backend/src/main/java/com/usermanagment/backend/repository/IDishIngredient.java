package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.DishIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDishIngredient extends JpaRepository<DishIngredient, Long>, JpaSpecificationExecutor<DishIngredient> {
    Optional<DishIngredient> findById(long id);

    @Query("select di from DishIngredient di where di.dish.id = ?1")
    List<DishIngredient> findAllIngredientsOfDish(long id);

    @Query("select di from DishIngredient di where di.ingredient.id = ?1")
    List<DishIngredient> findAllDishesOfIngredient(long id);
}
