package com.example.wp_av_project.service.impl;

import com.example.wp_av_project.model.User;
import com.example.wp_av_project.model.exceptions.InvalidArgumentException;
import com.example.wp_av_project.model.exceptions.InvalidUserCredentialsException;
import com.example.wp_av_project.model.exceptions.PasswordsDoNotMatchException;
import com.example.wp_av_project.repository.InMemoryUserRepository;
import com.example.wp_av_project.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final InMemoryUserRepository userRepository;

    public AuthServiceImpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentException();

        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if(username == null || username.isEmpty() || password == null || password.isEmpty() || repeatPassword == null || repeatPassword.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty())
            throw new InvalidArgumentException();
        if(!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();

        return userRepository.saveOrUpdate(new User(username,password,name,surname));
    }
}
