package com.jfantou.demo.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(String message) {
        super("Le cours recherche n'existe pas:" + message);
    }
}
