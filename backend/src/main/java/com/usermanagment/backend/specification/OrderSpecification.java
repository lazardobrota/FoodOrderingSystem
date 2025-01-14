package com.usermanagment.backend.specification;

import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.params.SearchParams;
import com.usermanagment.backend.status.OrderStatus;
import jakarta.persistence.criteria.Predicate;

import java.time.LocalDateTime;
import java.util.List;

public class OrderSpecification extends BaseSpecification<Order> {

    public OrderSpecification(SearchParams searchParams) {
        addUserEmail(searchParams.getUserEmail());
        addStartDate(searchParams.getStartDate());
        addEndDate(searchParams.getEndDate());
        addOrderStatuses(searchParams.getStatus());
    }

    private void addUserEmail(String userEmail) {
        if (userEmail == null)
            return;

        specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.join(Order.createdBy()).get(User.email()), "%" + userEmail + "%"));
    }

    private void addStartDate(LocalDateTime startDate) {
        if (startDate == null)
            return;

        specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get(Order.scheduleDate()), startDate));
    }

    private void addEndDate(LocalDateTime endDate) {
        if (endDate == null)
            return;

        specifications.add((root, query, criteriaBuilder) ->
                (criteriaBuilder.lessThanOrEqualTo(root.get(Order.scheduleDate()), endDate)));
    }

    private void addOrderStatuses(Integer orderStatuses) {
        if (orderStatuses == null)
            return;

        List<OrderStatus> orderStatusList = OrderStatus.toList(orderStatuses);

        if (orderStatusList.isEmpty())
            return;

        specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.or(orderStatusList.stream()
                        .map(orderStatus -> criteriaBuilder.equal(root.get(Order.status()), orderStatus.getValue()))
                        .toArray(Predicate[]::new)
                ));
    }



}
