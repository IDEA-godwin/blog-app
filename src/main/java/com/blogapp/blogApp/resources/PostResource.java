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
@RequestMapping(value = "/posts")
@Api(tags = "posts")
public class PostResource {

    @Autowired
    PostService postService;


    @PostMapping
    @ApiOperation(value = "Create Post", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 422, message = "incorrect field input check fields")})
    public ResponseEntity addNewPost(HttpServletRequest req,
                                     @ApiParam("Add new Post") @RequestBody PostRequestDTO post) {
        return postService.addNewPost(req, post);
    }

    @GetMapping
    @ApiOperation(value = "view all Post", response = PostsListResponseDTO.class,
                    authorizations = @Authorization(value = "apiKey"))
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 403, message = "Access denied confirm token"),
            @ApiResponse(code = 500, message = "Expired or invalid token")})
    public ResponseEntity getAllPost(HttpServletRequest req) {
        return postService.getAllPost(req);
    }

    @GetMapping("/{postId}")
    @ApiOperation(value = "view Post", response = Post.class,
            authorizations = @Authorization(value = "apiKey"))
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 403, message = "Access denied confirm token"),
            @ApiResponse(code = 500, message = "Expired or invalid token")})
    public ResponseEntity getSinglePost(HttpServletRequest req, @PathVariable Long postId) {
        return postService.getSinglePost(req, postId);
    }

    @PutMapping("/{postId}")
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

    @DeleteMapping("/{postId}")
    @ApiOperation(value = "Delete Post", authorizations = @Authorization(value = "apiKey"))
    public ResponseEntity deleteSinglePost(HttpServletRequest req, @ApiParam("Post id") @PathVariable Long postId) {
        return postService.deleteSinglePost(req, postId);
    }

    @DeleteMapping
    @ApiOperation(value = "Delete All Post", authorizations = @Authorization(value = "apiKey"))
    public ResponseEntity deleteAllPost(HttpServletRequest req) {
        return postService.deleteAllPost(req);
    }
}
