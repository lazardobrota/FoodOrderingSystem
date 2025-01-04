package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.Ingredient;
import com.usermanagment.backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrder extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Optional<Order> findById(long id);

    @Query("select o from Order o where o.createdBy.id = ?1")
    List<Order> findAllOfUser(long id);

    @Query("select o from Order o where o.active = true and o.createdDate < ?1")
    List<Order> findAllActiveBeforeDate(LocalDateTime date);
}
