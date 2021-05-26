package com.blogapp.blogApp.dto;

import com.blogapp.blogApp.domains.Category;
import com.blogapp.blogApp.domains.Tag;

import java.util.Set;

public class PostResponseDTO {
    private String author;
    private String title;
    private String postBody;
    private Category category;
    private Set<Tag> tags;

    public PostResponseDTO(String author, String title, String postBody, Category category, Set<Tag> tags) {
        this.author = author;
        this.title = title;
        this.postBody = postBody;
        this.category = category;
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set <Tag> tags) {
        this.tags = tags;
    }
}
