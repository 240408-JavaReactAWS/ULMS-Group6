package com.revature.backend.exceptions;

public class CourseFullException extends Exception{
    public CourseFullException(String message) {
        super(message);
    }
}
