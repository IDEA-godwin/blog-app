package com.blogapp.blogApp.sevices;

import com.blogapp.blogApp.Functions;
import com.blogapp.blogApp.domains.Post;
import com.blogapp.blogApp.domains.Tag;
import com.blogapp.blogApp.domains.User;
import com.blogapp.blogApp.dto.PostRequestDTO;
import com.blogapp.blogApp.dto.PostResponseDTO;
import com.blogapp.blogApp.dto.PostsListResponseDTO;
import com.blogapp.blogApp.exception.CustomException;
import com.blogapp.blogApp.repositories.CategoryRepository;
import com.blogapp.blogApp.repositories.PostRepository;
import com.blogapp.blogApp.repositories.TagRepository;
import com.blogapp.blogApp.repositories.UserRepository;
import com.blogapp.blogApp.securities.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    CategoryRepository categoryRepo;

    @Autowired
    PostRepository postRepository;

    public ResponseEntity addNewPost(HttpServletRequest req, PostRequestDTO post) {
        List<String> tags = post.getTags();
        if(!isUserActive(req))
            return new ResponseEntity<>("Try logging in or Expired Token", HttpStatus.UNPROCESSABLE_ENTITY);
        if(!isValidCategory(post.getCategory()))
            return new ResponseEntity("Not a valid category", HttpStatus.BAD_REQUEST);
        Post newPost = new Post(post.getTitle(), post.getPostBody());
        newPost.setUser(userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName()));
        newPost.setCategory(categoryRepo.findByCategoryNameIgnoreCase(post.getCategory()).get());
        newPost.setPostTags(addTags(newPost, post.getTags()));
        newPost.setCreatedDate(new Functions().dateFormat(Date.from(Instant.now())));
        newPost.setUpdatedDate(new Functions().dateFormat(Date.from(Instant.now())));
        return new ResponseEntity<>(postRepository.save(newPost), HttpStatus.OK);
    }

    public ResponseEntity getAllPost(HttpServletRequest req) {
        if(!isUserActive(req))
            return new ResponseEntity<>("Try logging in or Expired Token", HttpStatus.UNPROCESSABLE_ENTITY);
        User user = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        List<Post> posts = postRepository.findAllByUserUserId(user.getUserId());
        PostsListResponseDTO postsList = new PostsListResponseDTO(user.getFullName(), posts, posts.size());
        return new ResponseEntity<>(postsList, HttpStatus.OK);
    }

    public ResponseEntity getSinglePost(HttpServletRequest req, Long id) {
        if(!isUserActive(req))
            return new ResponseEntity<>("Try logging in or Expired Token", HttpStatus.UNPROCESSABLE_ENTITY);
        User user = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        Post getPost = postRepository.findByPostIdAndUserUserId(id, user.getUserId())
                .orElseThrow(() -> new CustomException("No Such Post", HttpStatus.NOT_FOUND));
        PostResponseDTO postResponse = new PostResponseDTO(user.getFullName(),getPost.getTitle(), getPost.getPostBody(),
                                        getPost.getCategory(), getPost.getPostTags());
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    public ResponseEntity updatePost(HttpServletRequest req, Long id, PostRequestDTO post) {
        if(!isUserActive(req))
            return new ResponseEntity<>("Try logging in or Expired Token", HttpStatus.UNPROCESSABLE_ENTITY);
        if(!isValidCategory(post.getCategory()))
            return new ResponseEntity("Not a valid category", HttpStatus.BAD_REQUEST);
        User user = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        Post updatePost = postRepository.findByPostIdAndUserUserId(id, user.getUserId())
                .orElseThrow(() -> new CustomException("No Such Post", HttpStatus.UNPROCESSABLE_ENTITY));
        updatePost.setTitle(new Functions().update(updatePost.getTitle(), post.getTitle()));
        updatePost.setPostBody(new Functions().update(updatePost.getPostBody(), post.getPostBody()));
        updatePost.setCategory(categoryRepo.findByCategoryNameIgnoreCase(post.getCategory()).get());
        updatePost.setPostTags(addTags(updatePost, post.getTags()));
        updatePost.setUpdatedDate(new Functions().dateFormat(Date.from(Instant.now())));
        return new ResponseEntity<>(postRepository.save(updatePost), HttpStatus.OK);
    }

    public ResponseEntity deleteSinglePost(HttpServletRequest req, Long id) {
        if(!isUserActive(req))
            return new ResponseEntity<>("Try logging in or Expired Token", HttpStatus.UNPROCESSABLE_ENTITY);
        User user = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        postRepository.delete(postRepository.findByPostIdAndUserUserId(id, user.getUserId()).get());
        return new ResponseEntity("Post Deleted at id: " + id, HttpStatus.OK);
    }

    public ResponseEntity deleteAllPost(HttpServletRequest req) {
        if(!isUserActive(req))
            return new ResponseEntity<>("Try logging in or Expired Token", HttpStatus.UNPROCESSABLE_ENTITY);
        User user = userRepository.findByEmailIgnoreCase(req.getUserPrincipal().getName());
        postRepository.deleteInBatch(postRepository.findAllByUserUserId(user.getUserId()));
        return new ResponseEntity("All Post Deleted", HttpStatus.OK);
    }

    boolean isUserActive(HttpServletRequest req) {
        String email = req.getUserPrincipal().getName();
        User user = userRepository.findByEmailIgnoreCase(email);
        return user.getToken() != null && user.getToken().equals(new JwtTokenProvider().resolveToken(req));
    }

    //checks category validity
    boolean isValidCategory(String category) {
        return categoryRepo.findByCategoryNameIgnoreCase(category).isPresent();
    }

    //returns set of tags from list of string for post
    Set<Tag> addTags(Post post, List<String> tags) {
        if(tags.isEmpty())
            return new HashSet<>();
        return tags.stream().map( s -> {
            Tag tag = getTag(s);
            tag.setPosts(new HashSet<>(Collections.singletonList(post)));
            return tagRepository.save(tag);
        }).collect(Collectors.toSet());
    }

    //check if tag exist returning saved tag if tag does not exist
    Tag getTag(String tagName) {
        if(tagRepository.findByTagNameIgnoreCase(tagName).isPresent())
            return tagRepository.findByTagNameIgnoreCase(tagName).get();
        return tagRepository.save(new Tag(tagName));
    }
}
