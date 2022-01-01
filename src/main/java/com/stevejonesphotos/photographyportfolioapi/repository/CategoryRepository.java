package com.stevejonesphotos.photographyportfolioapi.repository;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
