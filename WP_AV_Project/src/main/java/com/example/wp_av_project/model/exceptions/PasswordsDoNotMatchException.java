package com.example.wp_av_project.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException(){
        super("Passwords Do Not Match Exception");
    }
}
