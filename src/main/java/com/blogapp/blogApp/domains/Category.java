package com.blogapp.blogApp.domains;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long category_id;

    @Column(name = "name", nullable = false, unique = true)
    private String categoryName;
}
