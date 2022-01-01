package com.stevejonesphotos.photographyportfolioapi.domain;

public class Category {

    private final String id;
    private final String title;
    private final String slug;
    private final String thumbnailUrl;

    public Category(String id, String title, String slug, String thumbnailUrl) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.thumbnailUrl = thumbnailUrl;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
