package com.blogapp.blogApp.repositories;

import com.blogapp.blogApp.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Admin, Long> {
    Admin findByEmailIgnoreCase(String email);
    Optional<Admin> findByToken(String token);
}
