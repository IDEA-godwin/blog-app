package com.blogapp.blogApp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "users")
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "token", unique = true)
    private String token;

    @Column
    private String role = "ADMIN";

    public Admin(String fullName, String email, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public boolean isUserActive(HttpServletRequest req) {
        return this.token != null;
    }
}
