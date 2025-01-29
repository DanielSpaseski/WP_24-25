package com.example.wp_av_project.service;

import com.example.wp_av_project.model.User;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname);
}
