package com.blogapp.blogApp.DTO.requests;

import com.blogapp.blogApp.entities.Post;
import lombok.Data;

import java.util.Set;

@Data
public class PostRequestDTO {

    private String title;
    private String postBody;
    private String category;
    private Set<String> tags;

}
