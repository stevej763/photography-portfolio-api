package com.stevejonesphotos.photographyportfolioapi.repository;

import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PhotoRepository extends MongoRepository<Photo, String> {

    List<Photo> findByCategories(String categoryId);
}
