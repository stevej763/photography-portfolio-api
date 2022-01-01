package com.stevejonesphotos.photographyportfolioapi.api;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    public static String CAT_ID_1 = "1f4073da-b6a0-4344-a8e2-bef1a1157f61";
    public static String CAT_ID_2 = "1f4073da-b6a0-4344-a8e2-bef1a1157f62";
    public static String CAT_ID_3 = "1f4073da-b6a0-4344-a8e2-bef1a1157f63";
    public static String CAT_ID_4 = "1f4073da-b6a0-4344-a8e2-bef1a1157f64";
    public static String CAT_ID_5 = "1f4073da-b6a0-4344-a8e2-bef1a1157f65";
    public static String CAT_ID_6 = "1f4073da-b6a0-4344-a8e2-bef1a1157f66";
    public static String CAT_ID_7 = "1f4073da-b6a0-4344-a8e2-bef1a1157f67";
    public static String CAT_ID_8 = "1f4073da-b6a0-4344-a8e2-bef1a1157f68";
    private final String thumbnailUrl = "https://stevejonesphotos.co.uk/wp-content/uploads/2021/11/6A8DB73C-295C-471A-82D3-216DFD3AEF8B-scaled.jpeg";
    private final List<Category> categories = List.of(
            new Category(CAT_ID_1, "Holiday", "holiday-1", thumbnailUrl),
            new Category(CAT_ID_2, "Summer", "summer-2", thumbnailUrl),
            new Category(CAT_ID_3, "Spring", "spring-3", thumbnailUrl),
            new Category(CAT_ID_4, "Cats", "cats-4", thumbnailUrl),
            new Category(CAT_ID_5, "Dogs", "dogs-5", thumbnailUrl),
            new Category(CAT_ID_6, "Birds", "birds-6", thumbnailUrl),
            new Category(CAT_ID_7, "People", "people-7", thumbnailUrl),
            new Category(CAT_ID_8, "Dragons", "dragons-8", thumbnailUrl)
    );;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/find-all")
    public List<Category> findAll() {
        return categories;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/find-by-id/{categoryId}")
    public Optional<Category> findById(@PathVariable String categoryId) {
        return categories.stream().filter((category) -> category.getId() == (categoryId)).findFirst();
    }

}
