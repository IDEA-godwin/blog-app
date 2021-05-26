package com.blogapp.blogApp.repositories;

import com.blogapp.blogApp.domains.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryNameIgnoreCase(String name);
}
