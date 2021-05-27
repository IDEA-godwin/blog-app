package com.blogapp.blogApp.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class HomeResource {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
