package com.usermanagment.backend.repository;

import com.usermanagment.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findById(int id);
    Optional<User> findByEmail(String email);
}
