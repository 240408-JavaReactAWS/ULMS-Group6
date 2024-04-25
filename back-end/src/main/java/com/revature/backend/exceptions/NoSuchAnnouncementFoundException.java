package com.revature.backend.exceptions;

public class NoSuchAnnouncementFoundException extends Exception{
    public NoSuchAnnouncementFoundException(String message) {
        super(message);
    }
}
