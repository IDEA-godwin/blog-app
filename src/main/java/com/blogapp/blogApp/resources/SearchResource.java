package com.blogapp.blogApp.resources;

import com.blogapp.blogApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class SearchResource {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/search")
    public ResponseEntity searchByAuthor(@RequestParam String author) {
        return new ResponseEntity(author, HttpStatus.OK);
    }
}
