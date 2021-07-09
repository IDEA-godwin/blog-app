package com.blogapp.blogApp.security;

import com.blogapp.blogApp.entities.Admin;
import com.blogapp.blogApp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Admin admin = userRepository.findByEmailIgnoreCase(email);

        if (admin == null) {
            throw new UsernameNotFoundException("User with email '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(admin.getPassword())//
                .authorities(new ArrayList<>())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
