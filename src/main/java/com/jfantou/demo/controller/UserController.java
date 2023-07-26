package com.jfantou.demo.controller;

import com.jfantou.demo.model.Post;
import com.jfantou.demo.model.User;
import com.jfantou.demo.repository.PostRepository;
import com.jfantou.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserService userService;

    private PostRepository postRepository;

    public UserController(UserService userService, PostRepository postRepository){
        this.userService = userService;
        this.postRepository = postRepository;
    }
    @GetMapping("/users")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getById(@PathVariable(name="id") int id){
        User user = userService.getById(id);
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAll());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePostByUser(@PathVariable(name="id") int id){
        User user = userService.getById(id);
        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable(name="id") int id, @Valid @RequestBody Post post){
        User user = userService.getById(id);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable(name="id") int id){
        userService.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createCourse(@Valid @RequestBody User user){
        User userSaved = userService.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
