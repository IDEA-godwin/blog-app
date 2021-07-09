package com.blogapp.blogApp.controllers.Rest;

import com.blogapp.blogApp.DTO.ResponseDTO;
import com.blogapp.blogApp.entities.Post;
import com.blogapp.blogApp.DTO.requests.PostRequestDTO;
import com.blogapp.blogApp.DTO.requests.PostsListResponseDTO;
import com.blogapp.blogApp.sevices.PostService;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<ResponseDTO> getAllPost() {
        return postService.getAllPost();
    }

    @PostMapping("/posts")
    public ResponseEntity<ResponseDTO> addNewPost(@RequestBody PostRequestDTO post) {
        return postService.addNewPost(post);
    }
//
//    @GetMapping("/posts/{postId}")
//    public ResponseEntity<ResponseDTO> getSinglePost(HttpServletRequest req, @PathVariable Long postId) {
//        return postService.getSinglePost(req, postId);
//    }
//
//    @PutMapping("/posts/{postId}")
//    public ResponseEntity<ResponseDTO> updatePost(HttpServletRequest req, @RequestBody PostRequestDTO post,
//                                     @PathVariable Long postId) {
//        return postService.updatePost(req, postId, post);
//    }
//
//    @DeleteMapping("/posts/{postId}")
//    public ResponseEntity<ResponseDTO> deleteSinglePost(HttpServletRequest req, @PathVariable Long postId) {
//        return postService.deleteSinglePost(req, postId);
//    }
//
//    @DeleteMapping("/posts")
//    public ResponseEntity<ResponseDTO> deleteAllPost(HttpServletRequest req) {
//        return postService.deleteAllPost(req);
//    }
}