package com.blogapp.blogApp.resources;

import com.blogapp.blogApp.domains.User;
import com.blogapp.blogApp.dto.UserLoginDTO;
import com.blogapp.blogApp.dto.UserRegisterDTO;
import com.blogapp.blogApp.sevices.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/users")
@Api(tags = "users")
public class UserResource {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    @ApiOperation("Register User")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 422, message = "email already exists")})
    public ResponseEntity registerUser(@ApiParam(value = "register user") @RequestBody UserRegisterDTO user,
                                       @ApiParam(value = "User Role") @RequestParam(required = false) String role) {
        return userService.registerUser(user, role);
    }

    @PostMapping("/login")
    @ApiOperation("User Login")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 422, message = "email or password not correct")})
    public ResponseEntity loginUser(@ApiParam("login user") @RequestBody UserLoginDTO user) {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }

    @PutMapping("/{userEmail}")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity updateUserDetails(@PathVariable String userEmail, @RequestParam String role,
                                            @RequestBody User user) {
        return userService.updateUserDetails(userEmail, user, role);
    }

    @ApiOperation(value = "Log out user", authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 422, message = "User not logged in")})
    @PostMapping("/logout")
    public ResponseEntity logoutUser(HttpServletRequest req) {
        return userService.logoutUser(req);
    }

    @ApiOperation(value = "view logged in user", response = User.class,
            authorizations = {@Authorization(value = "apiKey")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "something went wrong"),
            @ApiResponse(code = 403, message = "access denied; check token"),
            @ApiResponse(code = 500, message = "Expired or invalid token")})
    @GetMapping("/me")
    public ResponseEntity viewLoggedInUser(HttpServletRequest req) {
        return userService.viewLoggedInUser(req);
    }
}
