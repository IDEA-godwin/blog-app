package com.blogapp.blogApp.sevices;

import com.blogapp.blogApp.Functions;
import com.blogapp.blogApp.domains.Roles;
import com.blogapp.blogApp.domains.User;
import com.blogapp.blogApp.dto.ResponseDTO;
import com.blogapp.blogApp.dto.UserRegisterDTO;
import com.blogapp.blogApp.dto.UserResponseDTO;
import com.blogapp.blogApp.exception.CustomException;
import com.blogapp.blogApp.repositories.UserRepository;
import com.blogapp.blogApp.securities.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    public ResponseEntity registerUser(UserRegisterDTO user, String role) {
        User createUser = new User(user.getFullName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
        createUser.setRoles(new HashSet<Roles>(Collections.singleton(convertRole(role))));
        userRepository.save(createUser);
        return new ResponseEntity<>(new ResponseDTO("user created", createUser), HttpStatus.OK);
    }

    public ResponseEntity loginUser(String email, String password) {
        if(isUserLoggedIn(email)) {
            return new ResponseEntity(
                    "User is logged in",
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = jwtTokenProvider
                            .createToken(email, userRepository.findByEmailIgnoreCase(email).getRoles());
            User loggedInUser = userRepository.findByEmailIgnoreCase(email);
            loggedInUser.setToken(token);
            userRepository.save(loggedInUser);
            return new ResponseEntity(new ResponseDTO("login successful)", loggedInUser), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity logoutUser(HttpServletRequest req) {
        if(isUserLoggedIn(req.getUserPrincipal().getName())) {
            User user = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
            user.setToken("");
            userRepository.save(user);
            return new ResponseEntity("User has been logged out successfully", HttpStatus.OK);
        }
        return new ResponseEntity("User already logged out", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity updateUserDetails(String email, User user, String role) {
        User update = userRepository.findByEmailIgnoreCase(email);
        update.setEmail(new Functions().update(update.getEmail(), user.getEmail()));
        update.setFullName(new Functions().update(update.getFullName(), user.getFullName()));
        update.setRoles(new HashSet<Roles>(Collections.singleton(convertRole(role))));
        return ResponseEntity.ok(new ResponseDTO("user Detail Updated", userRepository.save(update)));
    }

    public ResponseEntity viewLoggedInUser(HttpServletRequest req) {
        if(isUserLoggedIn(req.getUserPrincipal().getName())) {
            User loggedInUser = userRepository
                    .findByEmailIgnoreCase(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
            return new ResponseEntity(loggedInUser, HttpStatus.OK);
        }
        return new ResponseEntity("User is not logged in", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    boolean isUserLoggedIn(String email) {
        User user = userRepository.findByEmailIgnoreCase(email);
        return user.getToken() != null && isValidToken(user.getToken());
    }

    boolean isValidToken(String token) {
        try {
            if(jwtTokenProvider.validateToken(token))
                return true;
        } catch (CustomException ex) {
            return false;
        }
        return false;
    }

    Roles convertRole(String role) {
        Roles userRole = Roles.ROLES_USER;
        if(role != null) {
            userRole = Roles.valueOf("ROLES_" + role.toUpperCase(Locale.ROOT));
            return userRole;
        }
        return userRole;
    }
}
