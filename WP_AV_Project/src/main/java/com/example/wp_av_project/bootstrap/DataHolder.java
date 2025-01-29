package com.example.wp_av_project.bootstrap;

import com.example.wp_av_project.model.Category;
import com.example.wp_av_project.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories;
    public static List<User> users;

    @PostConstruct
    public void init(){
        categories = new ArrayList<>();
        categories.add(new Category("Movies", "Movie description"));
        categories.add(new Category("Books", "Book description"));

        users = new ArrayList<>();
        users.add(new User("daniel.spaseski", "DS", "Daniel", "Spaseski"));
        users.add(new User("petar_petrov", "PP", "Petar", "Petrov"));
        users.add(new User("nikolaNikolov", "NN", "Nikola", "Nikolov"));
    }
}
