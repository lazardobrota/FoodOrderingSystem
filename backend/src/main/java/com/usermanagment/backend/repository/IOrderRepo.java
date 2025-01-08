package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.status.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepo extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Optional<Order> findById(long id);

    @Query("select o from CustomerOrder o where o.createdBy.id = ?1")
    List<Order> findAllOfUser(long id);

    @Query("select o from CustomerOrder o where o.active = true and o.createdDate < ?1")
    List<Order> findAllActiveBeforeDate(LocalDateTime date);

    @Query("SELECT o FROM CustomerOrder o " +
            "WHERE   (o.active = true) " +
            "   AND ((o.status = :orderedStatus AND o.createdDate <= :orderedThreshold) " +
            "   OR   (o.status = :preparingStatus AND o.createdDate <= :preparingThreshold) " +
            "   OR   (o.status = :deliveryStatus AND o.createdDate <= :deliveryThreshold))")
    List<Order> findOrdersReadyForStatusUpdate(
            @Param("orderedStatus") int orderedStatus,
            @Param("preparingStatus") int preparingStatus,
            @Param("deliveryStatus") int deliveryStatus,
            @Param("orderedThreshold") LocalDateTime orderedThreshold,
            @Param("preparingThreshold") LocalDateTime preparingThreshold,
            @Param("deliveryThreshold") LocalDateTime deliveryThreshold
    );

    default List<Order> findOrdersReadyForStatusUpdate() {
        return findOrdersReadyForStatusUpdate(
                OrderStatus.ORDERED.getValue(),
                OrderStatus.PREPARING.getValue(),
                OrderStatus.IN_DELIVERY.getValue(),
                LocalDateTime.now().minusSeconds(10),
                LocalDateTime.now().minusSeconds(15),
                LocalDateTime.now().minusSeconds(20)
        );
    }
}
