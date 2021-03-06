package com.blogapp.web.rest;

import com.blogapp.sevices.DTO.ResponseDTO;
import com.blogapp.sevices.DTO.PostDTO;
import com.blogapp.sevices.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public ResponseEntity<ResponseDTO> getAllPost() {
        return new ResponseEntity<>(new ResponseDTO("success", postService.getAllPost()), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<ResponseDTO> addNewPost(@RequestBody PostDTO post) {
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
