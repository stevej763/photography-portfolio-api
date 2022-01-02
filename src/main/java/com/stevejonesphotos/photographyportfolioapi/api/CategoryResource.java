package com.stevejonesphotos.photographyportfolioapi.api;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.service.AwsStorageService;
import com.stevejonesphotos.photographyportfolioapi.service.CategoryService;
import com.stevejonesphotos.photographyportfolioapi.service.CategoryStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryResource {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryStorageService storageService;


    @GetMapping("/find-all")
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/find-by-id/{categoryId}")
    public Optional<Category> findById(@PathVariable String categoryId) {
        return categoryService.findById(categoryId);
    }

    @PostMapping("/add-category")
    public ResponseEntity<String> addCategory(@RequestParam String categoryName, @RequestParam MultipartFile thumbnailImage) {
        String id = categoryService.createNewCategory(categoryName, thumbnailImage);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
