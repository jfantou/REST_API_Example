package com.jfantou.demo.service;

import com.jfantou.demo.exception.CourseNotFoundException;
import com.jfantou.demo.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private List<Course> listCourse = new ArrayList<>();
    private static int courseCount = 0;

    public List<Course> getAll(){
        return listCourse;
    }

    public Course saveCourse(Course course){
        course.setId(courseCount++);
        listCourse.add(course);
        return course;
    }

    public Course getById(int id){
        Optional<Course> course = listCourse.stream().filter( cours -> cours.getId().equals(id)).findFirst();
        if(!course.isPresent())
            throw new CourseNotFoundException(String.valueOf(id));
        return course.get();
    }

    public void deleteById(int id){
        listCourse.removeIf(cours -> cours.getId().equals(id));
    }
}
