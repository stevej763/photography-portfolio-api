package com.stevejonesphotos.photographyportfolioapi.domain;

import java.util.List;

public class Photo {

    private final String id;
    private final String title;
    private final String slug;
    private final String alt;
    private final String description;
    private final List<String> categories;
    private final String thumbnailUrl;
    private final String smallImageUrl;
    private final String mediumImageUrl;
    private final String largeImageUrl;
    private final String originalImageUrl;

    public Photo(String id,
                 String title,
                 String slug,
                 String alt,
                 String description,
                 List<String> categories,
                 String thumbnailUrl,
                 String smallImageUrl,
                 String mediumImageUrl,
                 String largeImageUrl,
                 String originalImageUrl) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.alt = alt;
        this.description = description;
        this.categories = categories;
        this.thumbnailUrl = thumbnailUrl;
        this.smallImageUrl = smallImageUrl;
        this.mediumImageUrl = mediumImageUrl;
        this.largeImageUrl = largeImageUrl;
        this.originalImageUrl = originalImageUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getAlt() {
        return alt;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public String getLargeImageUrl() {
        return largeImageUrl;
    }

    public String getOriginalImageUrl() {
        return originalImageUrl;
    }
}
