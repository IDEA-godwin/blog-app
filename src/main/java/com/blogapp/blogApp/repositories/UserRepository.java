package com.blogapp.blogApp.repositories;

import com.blogapp.blogApp.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIgnoreCase(String email);
    Optional<User> findByToken(String token);
}
