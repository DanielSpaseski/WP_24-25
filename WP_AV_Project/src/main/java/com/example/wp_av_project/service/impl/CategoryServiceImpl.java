package com.example.wp_av_project.service.impl;

import com.example.wp_av_project.model.Category;
import com.example.wp_av_project.repository.InMemoryCategoryRepository;
import com.example.wp_av_project.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryCategoryRepository categoryRepository;
    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> categories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return categoryRepository.save(category);
    }

    @Override
    public Category update(String name, String description) {
        if (name == null || name.isEmpty() || description == null || description.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);

        return categoryRepository.save(category);
    }

    @Override
    public void delete(String name) {
        categoryRepository.delete(name);

    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
