package com.example.wp_av_project.service;

import com.example.wp_av_project.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> categories();
    Category create(String name, String description);
    Category update(String name, String description);
    void delete(String name);
    List<Category> searchCategories(String text);
}
