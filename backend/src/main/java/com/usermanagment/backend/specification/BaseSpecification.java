package com.usermanagment.backend.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BaseSpecification<Type> {
    protected final List<Specification<Type>> specifications = new ArrayList<>();

    protected BaseSpecification() {
    }

    public Specification<Type> filter() {
        if (specifications.isEmpty())
            return null;

        return specifications.stream().skip(1).reduce(Specification.where(specifications.getFirst()), Specification::and);
    }
}
