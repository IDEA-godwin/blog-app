package com.blogapp.web.template;

import com.blogapp.sevices.CategoryService;
import com.blogapp.sevices.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
