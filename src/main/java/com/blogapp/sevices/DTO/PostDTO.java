package com.blogapp.sevices.DTO;

import lombok.Data;

import java.util.Set;

@Data
public class PostDTO {

    private String title;
    private String postBody;
    private String category;
    private Set<String> tags;

}
