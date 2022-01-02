package com.stevejonesphotos.photographyportfolioapi.service;

import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import com.stevejonesphotos.photographyportfolioapi.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PhotoService implements PersistedDataService<Photo, String> {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public String add(Photo photo) {
        photoRepository.insert(photo);
        return "";
    }

    @Override
    public Optional<Photo> findById(String id) {
        return photoRepository.findById(id);
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public List<Photo> findAllForCategory(String categoryId) {
        return photoRepository.findByCategories(categoryId);
    }
}
