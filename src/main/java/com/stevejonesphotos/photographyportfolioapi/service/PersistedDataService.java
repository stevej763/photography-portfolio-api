package com.stevejonesphotos.photographyportfolioapi.service;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;

import java.util.List;
import java.util.Optional;

public interface PersistedDataService<T, E> {

    E add(T object);

    Optional<T> findById(String id);

    List<T> findAll();

}
