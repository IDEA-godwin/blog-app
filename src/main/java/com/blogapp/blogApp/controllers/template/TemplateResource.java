package com.blogapp.blogApp.controllers.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateResource {

//    @GetMapping("/**")
//    public String template() {
//        return "index";
//    }

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping("/posts-list")
    public String posts() {
        return "index";
    }

    @GetMapping("/About")
    public String about() { return "index"; }

    @GetMapping("/admin")
    public String admin() {
        return "index";
    }

    @GetMapping("/create-post")
    public String createPost() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "index";
    }
}
