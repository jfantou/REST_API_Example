package com.jfantou.demo.controller;

import com.jfantou.demo.model.Course;
import com.jfantou.demo.service.CourseService;
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
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }
    @GetMapping("/courses")
    public List<Course> getAll(){
        return courseService.getAll();
    }

    @GetMapping("/courses/{id}")
    public EntityModel<Course> getById(@PathVariable(name="id") int id){
        Course course = courseService.getById(id);
        EntityModel<Course> entityModel = EntityModel.of(course);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAll());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @DeleteMapping("/courses/{id}")
    public void deleteById(@PathVariable(name="id") int id){
        courseService.deleteById(id);
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> saveNewCourse(@Valid @RequestBody Course course){
        Course savedCourse = courseService.saveCourse(course);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCourse.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
