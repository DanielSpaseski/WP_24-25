package com.example.wp_av_project.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("Invalid User Credentials Exception");
    }
}
