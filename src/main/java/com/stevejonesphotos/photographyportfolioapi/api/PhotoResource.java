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
import java.util.UUID;

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
        String thumbnailUrl = uploadThumbnail(photo);
        Photo newPhoto = new Photo(
                UUID.randomUUID().toString(),
                title,
                createPhotoSlug(title),
                alt,
                description,
                categories,
                thumbnailUrl,
                thumbnailUrl,
                thumbnailUrl,
                thumbnailUrl,
                thumbnailUrl);
        String id = photoService.add(newPhoto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    private String createPhotoSlug(String title) {
        return title.toLowerCase().replace(" ", "-");
    }

    private String uploadThumbnail(MultipartFile thumbnailImage) {
        return "https://stevejonesphotos.co.uk/wp-content/uploads/2021/11/6A8DB73C-295C-471A-82D3-216DFD3AEF8B-scaled.jpeg";
    }

}
