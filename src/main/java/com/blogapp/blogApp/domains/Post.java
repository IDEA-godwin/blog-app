package com.blogapp.blogApp.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false, unique = true)
    private Long postId;

    @Column(name = "post_title", nullable = false)
    private String title;

    @Column(name = "post_body", nullable = false)
    private String postBody;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String  updatedDate;

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> postTags;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_name", referencedColumnName = "name")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    public Post(String title, String postBody) {
        this.title = title;
    }
}
