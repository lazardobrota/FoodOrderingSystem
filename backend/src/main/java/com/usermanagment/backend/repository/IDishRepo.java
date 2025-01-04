package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.Dish;
import com.usermanagment.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepo extends JpaRepository<Dish, Long>, JpaSpecificationExecutor<Dish> {
    Optional<Dish> findById(long id);
    Optional<Dish> findByName(String name);
}
