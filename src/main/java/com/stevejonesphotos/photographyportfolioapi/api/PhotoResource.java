package com.stevejonesphotos.photographyportfolioapi.api;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import com.stevejonesphotos.photographyportfolioapi.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/photos")
@CrossOrigin(origins = "http://localhost:3000")
public class PhotoResource {

    @Autowired
    PhotoService photoService;

    @GetMapping("/find-all")
    public List<Photo> findAll() {
        return photoService.findAll();
    }

    @GetMapping("/find-by-category/{categoryId}")
    public List<Photo> findByCategory(@PathVariable String categoryId) {
        return photoService.findAllForCategory(categoryId);
    }

    @PostMapping("/add-photo")
    public ResponseEntity<String> addPhoto(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String alt,
            @RequestParam List<String> categories,
            @RequestParam MultipartFile photo) {
        String id = photoService.addNewPhoto(title, description, alt, categories, photo);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
