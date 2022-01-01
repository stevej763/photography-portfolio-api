package com.stevejonesphotos.photographyportfolioapi.api;

import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.stevejonesphotos.photographyportfolioapi.api.CategoryResource.*;

@RestController
@RequestMapping("/photos")
public class PhotoResource {
    private final String thumbnailUrl = "https://stevejonesphotos.co.uk/wp-content/uploads/2021/11/6A8DB73C-295C-471A-82D3-216DFD3AEF8B-scaled.jpeg";
    private final List<Photo> photos = List.of(
            new Photo(UUID.randomUUID().toString(),
                    "Photo 1",
                    "photo-1",
                    "a stub photo",
                    "Description of photo 1",
                    List.of(CAT_ID_1, CAT_ID_2, CAT_ID_3),
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl),
            new Photo(UUID.randomUUID().toString(),
                    "Photo 2",
                    "photo-2",
                    "a stub photo",
                    "Description of photo 2",
                    List.of(CAT_ID_2, CAT_ID_3, CAT_ID_4),
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl),
            new Photo(UUID.randomUUID().toString(),
                    "Photo 3",
                    "photo-3",
                    "a stub photo",
                    "Description of photo 3",
                    List.of(CAT_ID_3, CAT_ID_4, CAT_ID_5),
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl),
            new Photo(UUID.randomUUID().toString(),
                    "Photo 4",
                    "photo-4",
                    "a stub photo",
                    "Description of photo 4",
                    List.of(CAT_ID_5, CAT_ID_6, CAT_ID_7),
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl),
            new Photo(UUID.randomUUID().toString(),
                    "Photo 5",
                    "photo-5",
                    "a stub photo",
                    "Description of photo 5",
                    List.of(CAT_ID_7, CAT_ID_8, CAT_ID_1),
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl),
            new Photo(UUID.randomUUID().toString(),
                    "Photo 6",
                    "photo-6",
                    "a stub photo",
                    "Description of photo 6",
                    List.of(CAT_ID_8, CAT_ID_1, CAT_ID_2),
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl,
                    thumbnailUrl)
    );


    @GetMapping("/find-all")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Photo> findAll() {
        return photos;
    }

    @GetMapping("/find-by-category/{categoryId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Photo> findByCategory(@PathVariable String categoryId) {
        return photos.stream().filter((photo) -> photo.getCategories().contains(categoryId)).collect(Collectors.toList());
    }
}
