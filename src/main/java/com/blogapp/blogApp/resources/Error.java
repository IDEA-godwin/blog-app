package com.blogapp.blogApp.resources;

import com.blogapp.blogApp.dto.ResponseDTO;
import com.blogapp.blogApp.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/posts")
public class Error {

    @GetMapping("/error")
    public ResponseEntity searchByAuthor(HttpServletResponse res) {
        return new ResponseEntity(new ResponseDTO("something some wrong", null), HttpStatus.NOT_FOUND);
    }
}
