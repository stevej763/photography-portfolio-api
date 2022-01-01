package com.stevejonesphotos.photographyportfolioapi.service;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService implements StorageService<Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void add(Category category) {
        categoryRepository.insert(category);
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
