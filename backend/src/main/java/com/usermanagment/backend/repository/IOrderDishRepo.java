package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDishRepo extends JpaRepository<OrderDish, Long>, JpaSpecificationExecutor<OrderDish> {

    Optional<OrderDish> findById(long id);

    @Query("select od from OrderDish od where od.dish.id = ?1")
    List<OrderDish> findAllOrdersOfDish(long id);

    @Query("select od from OrderDish od where od.order.id = ?1")
    List<OrderDish> findAllDishesOfOrder(long id);
}
