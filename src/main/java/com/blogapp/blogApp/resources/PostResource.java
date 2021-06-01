package com.blogapp.blogApp.resources;

import com.blogapp.blogApp.domains.Post;
import com.blogapp.blogApp.dto.PostRequestDTO;
import com.blogapp.blogApp.dto.PostsListResponseDTO;
import com.blogapp.blogApp.sevices.PostService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "posts")
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    @ApiOperation(value = "Create Post", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 422, message = "incorrect field input check fields")})
    public ResponseEntity addNewPost(HttpServletRequest req,
                                     @ApiParam("Add new Post") @RequestBody PostRequestDTO post) {
        return postService.addNewPost(req, post);
    }

    @GetMapping("/posts")
    public ResponseEntity getAllPost() {
        return postService.getAllPost();
    }

    @GetMapping("/me/posts")
    @ApiOperation(value = "view all Post", response = PostsListResponseDTO.class,
                    authorizations = @Authorization(value = "apiKey"))
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 403, message = "Access denied confirm token"),
            @ApiResponse(code = 500, message = "Expired or invalid token")})
    public ResponseEntity getAlUserPost(HttpServletRequest req) {
        return postService.getAllUserPost(req);
    }

    @GetMapping("/me/posts/{postId}")
    @ApiOperation(value = "view Post", response = Post.class,
            authorizations = @Authorization(value = "apiKey"))
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 403, message = "Access denied confirm token"),
            @ApiResponse(code = 500, message = "Expired or invalid token")})
    public ResponseEntity getSingleUserPost(HttpServletRequest req, @PathVariable Long postId) {
        return postService.getSingleUserPost(req, postId);
    }

    @PutMapping("/me/posts/{postId}")
    @ApiOperation(value = "Update Post", response = Post.class,
            authorizations = @Authorization(value = "apiKey"))
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 422, message = "Incorrect input for fields"),
            @ApiResponse(code = 403, message = "Access denied confirm token"),
            @ApiResponse(code = 500, message = "Expired or invalid token")})
    public ResponseEntity updatePost(HttpServletRequest req,
                                     @ApiParam("Update post") @RequestBody PostRequestDTO post,
                                     @ApiParam("post id") @PathVariable Long postId) {
        return postService.updatePost(req, postId, post);
    }

    @DeleteMapping("/me/posts/{postId}")
    @ApiOperation(value = "Delete Post", authorizations = @Authorization(value = "apiKey"))
    public ResponseEntity deleteSingleUserPost(HttpServletRequest req, @ApiParam("Post id") @PathVariable Long postId) {
        return postService.deleteSingleUserPost(req, postId);
    }

    @DeleteMapping("/me/posts")
    @ApiOperation(value = "Delete All Post", authorizations = @Authorization(value = "apiKey"))
    public ResponseEntity deleteAllUserPost(HttpServletRequest req) {
        return postService.deleteAllUserPost(req);
    }
}
