package com.blogapp.blogApp.repositories;

import com.blogapp.blogApp.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagNameIgnoreCase(String name);
}
