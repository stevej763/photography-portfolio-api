package com.stevejonesphotos.photographyportfolioapi.repository;

import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
