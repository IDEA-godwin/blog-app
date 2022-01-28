package com.blogapp.blogApp.controllers.template;

import com.blogapp.blogApp.entities.Post;
import com.blogapp.blogApp.repositories.PostRepository;
import com.blogapp.blogApp.sevices.PostService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TemplateResource {

    private final PostService postService;

    public TemplateResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public String index(Model model) {
        List<Post> posts = postService.getAllPost();
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping("posts/{postId}")
    public String postView(@PathVariable long postId, Model model) {
        Post post = postService.getSinglePost(postId);
        model.addAttribute(post);
        return "post-view";
    }


}
