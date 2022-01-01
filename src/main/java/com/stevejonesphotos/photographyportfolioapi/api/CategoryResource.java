package com.stevejonesphotos.photographyportfolioapi.api;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/find-all")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/find-by-id/{categoryId}")
    public Optional<Category> findById(@PathVariable String categoryId) {
        return categoryService.findById(categoryId);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/add-category")
    public void addCategory(@RequestParam String categoryName, @RequestParam MultipartFile thumbnailImage) {
        String thumbnailUrl = uploadThumbnail(thumbnailImage);
        Category newCategory = new Category(UUID.randomUUID().toString(), categoryName, createCategorySlug(categoryName), thumbnailUrl);
        categoryService.add(newCategory);
    }

    private String createCategorySlug(String categoryName) {
        return categoryName.toLowerCase().replace(" ", "-");
    }

    private String uploadThumbnail(MultipartFile thumbnailImage) {
        return "https://stevejonesphotos.co.uk/wp-content/uploads/2021/11/6A8DB73C-295C-471A-82D3-216DFD3AEF8B-scaled.jpeg";
    }

}
