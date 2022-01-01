package com.stevejonesphotos.photographyportfolioapi.service;

import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import com.stevejonesphotos.photographyportfolioapi.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PhotoService implements StorageService<Photo> {

    @Autowired
    PhotoRepository photoRepository;

    @Override
    public void add(Photo photo) {
        photoRepository.insert(photo);
    }

    @Override
    public Optional<Photo> findById(String id) {
        return null;
    }

    @Override
    public List<Photo> findAll() {
        return null;
    }
}
