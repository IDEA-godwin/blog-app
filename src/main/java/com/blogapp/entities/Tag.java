package com.blogapp.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long tagId;

    @Column(name = "name", unique = true, nullable = false)
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(getClass() != obj.getClass()) return false;
        return tagId != null && tagId.equals(((Tag) obj).tagId);
    }

    @Override
    public int hashCode() {
        return 2021;
    }
}
