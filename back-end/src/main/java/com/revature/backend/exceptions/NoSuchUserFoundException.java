package com.revature.backend.exceptions;

public class NoSuchUserFoundException  extends Exception{
    public NoSuchUserFoundException(String message) {
        super(message);
    }
}
