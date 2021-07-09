package com.blogapp.blogApp.controllers.Rest;

import com.blogapp.blogApp.DTO.ResponseDTO;
import com.blogapp.blogApp.entities.Admin;
import com.blogapp.blogApp.DTO.requests.UserLoginDTO;
import com.blogapp.blogApp.DTO.requests.UserRegisterDTO;
import com.blogapp.blogApp.sevices.AdminService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class AdminResource {

    private final AdminService adminService;

    public AdminResource(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserRegisterDTO user) {
        return adminService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody UserLoginDTO user) {
        return adminService.loginUser(user.getEmail(), user.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseDTO> logoutUser(HttpServletRequest req) {
        return adminService.logoutUser(req);
    }

    @GetMapping("users/me")
    public ResponseEntity<ResponseDTO> viewLoggedInUser(HttpServletRequest req) {
        return adminService.viewLoggedInUser(req);
    }

}
