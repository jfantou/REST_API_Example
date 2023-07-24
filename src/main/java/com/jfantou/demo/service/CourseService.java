package com.jfantou.demo.service;

import com.jfantou.demo.exception.CourseNotFoundException;
import com.jfantou.demo.model.Course;
import com.jfantou.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll(){
        return courseRepository.findAll();
    }

    public Course saveCourse(Course course){
        Course courseSave = courseRepository.save(course);
        return courseSave;
    }

    public Course getById(int id){
        Optional<Course> course = courseRepository.findById(id);
        if(!course.isPresent())
            throw new CourseNotFoundException(String.valueOf(id));
        return course.get();
    }

    public void deleteById(int id){
        courseRepository.deleteById(id);
    }
}
