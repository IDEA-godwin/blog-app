package com.blogapp.sevices;

import com.blogapp.sevices.DTO.ResponseDTO;
import com.blogapp.sevices.DTO.PostDTO;
import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.Tag;
import com.blogapp.repositories.CategoryRepository;
import com.blogapp.repositories.PostRepository;
import com.blogapp.repositories.TagRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepo;

    public PostService(TagRepository tagRepository, CategoryRepository categoryRepo, PostRepository postRepository) {
        this.tagRepository = tagRepository;
        this.categoryRepo = categoryRepo;
        this.postRepository = postRepository;
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public ResponseEntity<ResponseDTO> addNewPost(PostDTO postReq) {
        try{
            Post post = new Post(postReq.getTitle(), postReq.getPostBody());
            post.setCategory(validCategory(postReq.getCategory()));
            post.setPostTags(addTags(postReq.getTags()));
            return new ResponseEntity<>(
                    new ResponseDTO("success", postRepository.save(post)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Post getSinglePost(Long id) {
        return postRepository.findById(id)
                .orElse(null);
    }

    public ResponseEntity<ResponseDTO> updatePost(Long id, PostDTO postReq) {
        if(postRepository.findById(id).isPresent()) {
            Post post = postRepository.findById(id).get();
            post.setTitle(postReq.getTitle());
            post.setPostBody(postReq.getPostBody());
            post.setCategory(validCategory(postReq.getCategory()));
            post.setPostTags(addTags(postReq.getTags()));
            return new ResponseEntity<>(
                    new ResponseDTO("post updated", postRepository.save(post)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("post not found"), HttpStatus.NOT_FOUND);
    }

 /*   public ResponseEntity<ResponseDTO> deleteSinglePost(HttpServletRequest req, Long id) {
        Admin admin = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        postRepository.delete(postRepository.findByPostIdAndAuthor(id, admin).get());
        return new ResponseEntity<>(new ResponseDTO("Post Deleted at id: " + id), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> deleteAllPost(HttpServletRequest req) {
        Admin admin = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        postRepository.deleteInBatch(postRepository.findAllByAuthor(admin));
        return new ResponseEntity<>(new ResponseDTO("All Post Deleted"), HttpStatus.OK);
    }*/

    //return category if valid else throw category invalid
    Category validCategory(String category) {
        if(categoryRepo.findByNameIgnoreCase(category).isPresent())
            return categoryRepo.findByNameIgnoreCase(category).get();
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "category invalid");
    }

    //returns set of tags from list of string for post
    Set<Tag> addTags(Set<String> tags) {
        if(tags.isEmpty())
            return new HashSet<>();
        return tags.stream().map(this::getTag).collect(Collectors.toSet());
    }

    //check if tag exist returning saved tag if tag does not exist
    Tag getTag(String tagName) {
        if(tagRepository.findByTagNameIgnoreCase(tagName).isPresent())
            return tagRepository.findByTagNameIgnoreCase(tagName).get();
        return tagRepository.save(new Tag(tagName));
    }
}
