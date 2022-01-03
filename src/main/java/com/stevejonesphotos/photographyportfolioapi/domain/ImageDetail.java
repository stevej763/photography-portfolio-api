package com.stevejonesphotos.photographyportfolioapi.domain;

public enum ImageDetail {
    THUMBNAIL("thumbnail/", 1000),
    SMALL("small/", 1200),
    MEDIUM("medium/", 1600),
    LARGE("large/", 3000),
    ORIGINAL("original/", 0);

    private String folderName;
    private int width;

    ImageDetail(String folderName, int width) {

        this.folderName = folderName;
        this.width = width;
    }

    public String getFolderName() {
        return folderName;
    }

    public int getWidth() {
        return width;
    }
}