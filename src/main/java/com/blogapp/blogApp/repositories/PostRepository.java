package com.blogapp.blogApp.repositories;

import com.blogapp.blogApp.entities.Admin;
import com.blogapp.blogApp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
