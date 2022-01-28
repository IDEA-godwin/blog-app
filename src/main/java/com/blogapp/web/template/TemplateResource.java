package com.blogapp.blogApp.web.template;

import com.blogapp.blogApp.entities.Post;
import com.blogapp.blogApp.sevices.CategoryService;
import com.blogapp.blogApp.sevices.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TemplateResource {

    private final PostService postService;
    private final CategoryService categoryService;

    public TemplateResource(PostService postService, CategoryService categoryService) {
        this.postService = postService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("posts", postService.getAllPost());
        return "index";
    }

    @GetMapping("posts/{postId}")
    public String postView(@PathVariable long postId, Model model) {
        model.addAttribute(postService.getSinglePost(postId));
        return "post-view";
    }

    @GetMapping("/admin")
    public String dashboard(Model model) {
        model.addAttribute("posts", postService.getAllPost());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin-dashboard";
    }


}
