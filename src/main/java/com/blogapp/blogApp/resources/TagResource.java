package com.blogapp.blogApp.resources;

import com.blogapp.blogApp.domains.Tag;
import com.blogapp.blogApp.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class TagResource {

    @Autowired
    TagRepository tagRepository;

    @GetMapping("/tags")
    @PreAuthorize("hasAnyAuthority('ROLES_ADMIN', 'ROLES_USER')")
    public ResponseEntity getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return ResponseEntity.ok().body(tags);
    }

    @PostMapping("/tags")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity addNewTag(@RequestBody Tag tag) {
        return ResponseEntity.ok().body(tagRepository.save(tag));
    }

    @PutMapping("/tags/{id}")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity updateTag(@RequestBody Tag tag, @PathVariable Long id) {
        Tag updateTag = tagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        updateTag.setTagName(tag.getTagName());
        return ResponseEntity.ok()
                .body(tagRepository.save(updateTag));
    }

    @DeleteMapping("/tags/{id}")
    @PreAuthorize("hasAuthority('ROLES_ADMIN')")
    public ResponseEntity deleteTag(@PathVariable Long id) {
        tagRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted");
    }
}
