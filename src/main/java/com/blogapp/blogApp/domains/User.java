package com.blogapp.blogApp.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "token", unique = true)
    private String token;

    @ElementCollection(targetClass = Roles.class, fetch = FetchType.EAGER)
    private Set<Roles> roles;

    public User(String fullName, String email, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
}
