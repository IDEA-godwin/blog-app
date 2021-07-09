package com.blogapp.blogApp.sevices;

import com.blogapp.blogApp.entities.Admin;
import com.blogapp.blogApp.DTO.ResponseDTO;
import com.blogapp.blogApp.DTO.requests.UserRegisterDTO;
import com.blogapp.blogApp.repositories.UserRepository;
import com.blogapp.blogApp.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<ResponseDTO> registerUser(UserRegisterDTO user) {
        Admin createAdmin = new Admin(user.getFullName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
        userRepository.save(createAdmin);
        return new ResponseEntity<>(new ResponseDTO("success", createAdmin), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> loginUser(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            String token = jwtTokenProvider.createToken(email);
            Admin loggedInAdmin = userRepository.findByEmailIgnoreCase(email);
            loggedInAdmin.setToken(token);
            userRepository.save(loggedInAdmin);
            return new ResponseEntity<>(new ResponseDTO("success", loggedInAdmin), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<ResponseDTO> logoutUser(HttpServletRequest req) {
        Admin admin = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        admin.setToken("");
        userRepository.save(admin);
        return new ResponseEntity<>(new ResponseDTO("User has been logged out successfully"), HttpStatus.OK);
    }

//    public ResponseEntity updateUserDetails(String email, Admin admin, String role) {
//        Admin update = userRepository.findByEmailIgnoreCase(email);
//        update.setEmail(new Functions().update(update.getEmail(), admin.getEmail()));
//        update.setFullName(new Functions().update(update.getFullName(), admin.getFullName()));
//        return ResponseEntity.ok(new ResponseDTO("user Detail Updated", userRepository.save(update)));
//    }

    public ResponseEntity<ResponseDTO> viewLoggedInUser(HttpServletRequest req) {
        Admin loggedInAdmin = userRepository
                .findByEmailIgnoreCase(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        return new ResponseEntity<>(new ResponseDTO("success", loggedInAdmin), HttpStatus.OK);
    }
}
