package com.blogapp.blogApp.web.rest;

import com.blogapp.blogApp.entities.Category;
import com.blogapp.blogApp.sevices.CategoryService;
import com.blogapp.blogApp.sevices.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/posts")
public class CategoryResource {

    private final CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<ResponseDTO> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<ResponseDTO> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.updateCategory(category, id));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.deleteCategory(id));
    }

    @GetMapping("/tags")
    public ResponseEntity<ResponseDTO> getTags() {
        return new ResponseEntity<>(categoryService.getTags(), HttpStatus.OK);
    }
}
