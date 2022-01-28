package com.blogapp.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false, unique = true)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_name", referencedColumnName = "name", nullable = false)
    private Category category;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_body", nullable = false)
    private String postBody;

    @ManyToMany(targetEntity = Tag.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    @OrderBy("tagName ASC")
    private Set<Tag> postTags;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String  updatedDate;

    public Post(String title, String postBody) {
        this.postBody = postBody;
        this.title = title;
    }
}
