package com.blogapp.blogApp.DTO.requests;

import com.blogapp.blogApp.entities.Post;
//import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PostsListResponseDTO {

    private String author;

//    @ApiModelProperty(position
    private List<Post> posts;

//    @ApiModelProperty(position = 1)
    private Integer numberOfPosts;

    public PostsListResponseDTO(String author, List<Post> posts, Integer numberOfPosts) {
        this.author = author;
        this.posts = posts;
        this.numberOfPosts = numberOfPosts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(Integer numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }
}
