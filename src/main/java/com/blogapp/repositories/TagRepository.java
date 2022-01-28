package com.blogapp.repositories;

import com.blogapp.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByTagNameIgnoreCase(String name);
}
