package com.blogapp.blogApp.repositories;

import com.blogapp.blogApp.domains.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByUserUserId(Long userId);
    Optional<Post> findByPostIdAndUserUserId(Long id, Long userId);
}
