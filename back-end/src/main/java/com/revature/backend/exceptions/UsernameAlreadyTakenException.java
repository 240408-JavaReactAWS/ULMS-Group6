package com.revature.backend.exceptions;

public class UsernameAlreadyTakenException extends Exception{
    public UsernameAlreadyTakenException(String message){
        super(message);
    }
}
