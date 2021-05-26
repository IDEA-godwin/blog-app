package com.blogapp.blogApp.resources;

import com.blogapp.blogApp.domains.Category;
import com.blogapp.blogApp.repositories.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class CategoryResource {

    private final CategoryRepository categoryRepository;

    public CategoryResource(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    @PreAuthorize("hasAuthority('ROLES_ADMIN') or hasAuthority('ROLES_USER')")
    public ResponseEntity getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping("/categories")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity addNewTag(@RequestBody Category category) {
        return ResponseEntity.ok().body(categoryRepository.save(category));
    }

    @PutMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity updateTag(@RequestBody Category category, @PathVariable Long id) {
        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCategory.setCategoryName(category.getCategoryName());
        return ResponseEntity.ok()
                .body(categoryRepository.save(updateCategory));
    }

    @DeleteMapping("/categories/{id}")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
