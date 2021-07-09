package com.blogapp.blogApp.sevices;

import com.blogapp.blogApp.DTO.ResponseDTO;
import com.blogapp.blogApp.DTO.requests.PostRequestDTO;
import com.blogapp.blogApp.configs.Functions;
import com.blogapp.blogApp.entities.Category;
import com.blogapp.blogApp.entities.Post;
import com.blogapp.blogApp.entities.Tag;
import com.blogapp.blogApp.repositories.CategoryRepository;
import com.blogapp.blogApp.repositories.PostRepository;
import com.blogapp.blogApp.repositories.TagRepository;
import com.blogapp.blogApp.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepo;

    public PostService(UserRepository userRepository, TagRepository tagRepository,
                       CategoryRepository categoryRepo, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
        this.categoryRepo = categoryRepo;
        this.postRepository = postRepository;
    }

    public ResponseEntity<ResponseDTO> getAllPost() {
        return new ResponseEntity<>(new ResponseDTO("success", postRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<ResponseDTO> addNewPost(PostRequestDTO postReq) {
        try{
            Post post = new Post(postReq.getTitle(), postReq.getPostBody());
            post.setCategory(validCategory(postReq.getCategory()));
            post.setPostTags(addTags(postReq.getTags()));
            post.setCreatedDate(new Functions().dateFormat(Instant.now()));
            return new ResponseEntity<>(
                    new ResponseDTO("success", postRepository.save(post)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//
//    public ResponseEntity<ResponseDTO> getSinglePost(HttpServletRequest req, Long id) {
//        Admin author = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
//        Post post = postRepository.findByPostIdAndAuthor(id, author)
//                .orElseThrow(() -> new ErrorStatusResponse("No Such Post", HttpStatus.NOT_FOUND));
//        return new ResponseEntity<>(new ResponseDTO("Success", post), HttpStatus.OK);
//    }
//
    public ResponseEntity<ResponseDTO> updatePost(Long id, PostRequestDTO postReq) {
        if(postRepository.findById(id).isPresent()) {
            Post post = postRepository.findById(id).get();
            post.setTitle(postReq.getTitle());
            post.setPostBody(postReq.getPostBody());
            post.setCategory(validCategory(postReq.getCategory()));
            post.setPostTags(addTags(postReq.getTags()));
            post.setCreatedDate(new Functions().dateFormat(Instant.now()));
            return new ResponseEntity<>(
                    new ResponseDTO("post updated", postRepository.save(post)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO("post not found"), HttpStatus.NOT_FOUND);
    }
//
//    public ResponseEntity<ResponseDTO> deleteSinglePost(HttpServletRequest req, Long id) {
//        Admin admin = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
//        postRepository.delete(postRepository.findByPostIdAndAuthor(id, admin).get());
//        return new ResponseEntity<>(new ResponseDTO("Post Deleted at id: " + id), HttpStatus.OK);
//    }
//
//    public ResponseEntity<ResponseDTO> deleteAllPost(HttpServletRequest req) {
//        Admin admin = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
//        postRepository.deleteInBatch(postRepository.findAllByAuthor(admin));
//        return new ResponseEntity<>(new ResponseDTO("All Post Deleted"), HttpStatus.OK);
//    }

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
