package com.blogapp.blogApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO {

    private String message;
    private Object body;

    public ResponseDTO(String message) {
        this.message = message;
    }
}
