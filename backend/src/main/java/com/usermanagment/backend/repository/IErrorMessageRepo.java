package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.ErrorMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IErrorMessageRepo extends JpaRepository<ErrorMessage, Long>, JpaSpecificationExecutor<ErrorMessage> {

    @Query("select e from ErrorMessage e where e.order.id = ?1")
    Set<ErrorMessage> findErrorsByOrderId(Long id);
}
