package com.blogapp.blogApp.securities;

import com.blogapp.blogApp.domains.Roles;
import com.blogapp.blogApp.domains.User;
import com.blogapp.blogApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;


@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userRepository.findByEmailIgnoreCase(email);

        if (user == null) {
            throw new UsernameNotFoundException("User with email '" + email + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(email)//
                .password(user.getPassword())//
                .authorities(user.getRoles())//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }

}
