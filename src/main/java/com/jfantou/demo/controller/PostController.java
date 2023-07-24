package com.jfantou.demo.controller;

import com.jfantou.demo.model.Post;
import com.jfantou.demo.model.User;
import com.jfantou.demo.repository.PostRepository;
import com.jfantou.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    public List<Post> getAll(){
        return postRepository.findAll();
    }

    @PostMapping("/posts")
    public Post saveNewCourse(@Valid @RequestBody Post post){
        Post postSave = postRepository.save(post);
        return postSave;
    }
}
