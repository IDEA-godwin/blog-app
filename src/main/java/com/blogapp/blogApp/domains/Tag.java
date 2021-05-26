package com.blogapp.blogApp.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tagId;

    @Column(name = "name", unique = true, nullable = false)
    private String tagName;

    @ManyToMany(targetEntity = Post.class, mappedBy = "postTags")
    @JsonIgnore
    Set<Post> posts;

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}
