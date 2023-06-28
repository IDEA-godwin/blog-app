package com.blogapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CreatePostDto {

    private String title;
    private String content;
    private String category;
    private List<String> tags = new ArrayList<>();
}
