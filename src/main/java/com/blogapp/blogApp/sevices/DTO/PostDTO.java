package com.blogapp.blogApp.sevices.DTO.requests;

import lombok.Data;

import java.util.Set;

@Data
public class PostRequestDTO {

    private String title;
    private String postBody;
    private String category;
    private Set<String> tags;

}
