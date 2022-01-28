package com.blogapp.blogApp.controllers.rest;

import com.blogapp.blogApp.DTO.ResponseDTO;
import com.blogapp.blogApp.entities.Category;
import com.blogapp.blogApp.entities.Tag;
import com.blogapp.blogApp.repositories.CategoryRepository;
import com.blogapp.blogApp.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class CategoryResource {

    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public CategoryResource(CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return new ResponseEntity<>(new ResponseDTO("success", categories), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(new ResponseDTO("success",
                                        categoryRepository.save(category)), HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity updateTag(@RequestBody Category category, @PathVariable Long id) {
        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCategory.setName(category.getName());
        return ResponseEntity.ok()
                .body(categoryRepository.save(updateCategory));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted");
    }

    @GetMapping("/tags")
    public ResponseEntity<ResponseDTO> getTags() {
        List<Tag> tags = tagRepository.findAll();
        return new ResponseEntity<>(new ResponseDTO("success", tags), HttpStatus.OK);
    }
}
