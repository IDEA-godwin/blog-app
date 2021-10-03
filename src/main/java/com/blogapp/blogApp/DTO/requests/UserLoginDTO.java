package com.blogapp.blogApp.DTO.requests;

import lombok.Data;

@Data
public class UserLoginDTO {

    private String email;
    private String password;
}
