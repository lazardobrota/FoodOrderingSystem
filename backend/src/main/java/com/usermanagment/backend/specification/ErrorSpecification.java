package com.usermanagment.backend.specification;

import com.usermanagment.backend.model.ErrorMessage;
import com.usermanagment.backend.model.Order;
import com.usermanagment.backend.model.User;
import com.usermanagment.backend.params.SearchParams;

public class ErrorSpecification extends BaseSpecification<ErrorMessage> {
    public ErrorSpecification(SearchParams searchParams) {

        addUserEmail(searchParams.getUserEmail());
    }

    private void addUserEmail(String userEmail) {
        if (userEmail == null)
            return;

        specifications.add((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("user").get(User.email()), userEmail));
    }
}
