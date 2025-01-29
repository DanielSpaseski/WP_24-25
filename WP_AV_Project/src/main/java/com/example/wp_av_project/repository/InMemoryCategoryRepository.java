package com.example.wp_av_project.repository;

import com.example.wp_av_project.bootstrap.DataHolder;
import com.example.wp_av_project.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCategoryRepository {
    public Category save(Category category){
        DataHolder.categories.removeIf(c -> c.getName().equals(category.getName()));
        DataHolder.categories.add(category);
        return category;
    }

    public List<Category> findAll(){
        return DataHolder.categories;
    }

    public Optional<Category> findByName(String name){
        return DataHolder.categories.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst();
    }
    public List<Category> search(String text){
        return DataHolder.categories.stream()
                .filter(c -> c.getName().contains(text) || c.getDescription().contains(text))
                .toList();
    }

    public void delete(String name){
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }

}
