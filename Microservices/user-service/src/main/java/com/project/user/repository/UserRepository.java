package com.project.user.repository;

import com.project.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // all crud database methods
    User findUserByUsername(String username);

    Optional<User> findById(long id);
}
