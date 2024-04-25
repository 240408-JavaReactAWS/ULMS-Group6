package com.revature.backend.exceptions;

public class NoSuchUserException extends Exception{
    public NoSuchUserException(String message){
        super(message);
    }
}
