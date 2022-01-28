package com.blogapp.sevices;

import com.blogapp.entities.Category;
import com.blogapp.entities.Tag;
import com.blogapp.repositories.CategoryRepository;
import com.blogapp.repositories.TagRepository;
import com.blogapp.sevices.DTO.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public CategoryService(CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public ResponseDTO getAllCategories() {
        return new ResponseDTO("success", categoryRepository.findAll());
    }

    public ResponseDTO addCategory(Category category) {
        return new ResponseDTO("success", categoryRepository.save(category));
    }

    public Category updateCategory(Category category, Long id) {
        Category updateCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateCategory.setName(category.getName());
        return categoryRepository.save(updateCategory);
    }

    public String deleteCategory(Long id) {
        categoryRepository.deleteById(id);
        return "Deleted";
    }

    public ResponseDTO getTags() {
        List<Tag> tags = tagRepository.findAll();
        return new ResponseDTO("success", tags);
    }
}
