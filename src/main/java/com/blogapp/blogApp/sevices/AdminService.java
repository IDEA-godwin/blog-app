package com.blogapp.blogApp.sevices;

import com.blogapp.blogApp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
