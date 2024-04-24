package com.revature.backend.exceptions;

public class CourseAlreadyExistException extends Exception{
    public CourseAlreadyExistException(String message){
        super(message);
    }
}
