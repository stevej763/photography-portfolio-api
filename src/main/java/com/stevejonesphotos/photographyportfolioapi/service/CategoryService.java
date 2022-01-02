package com.stevejonesphotos.photographyportfolioapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.helpers.FileConverter;
import com.stevejonesphotos.photographyportfolioapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.imageio.ImageIO.read;

@Component
public class CategoryService implements PersistedDataService<Category, String> {

    private static int THUMBNAIL_WIDTH = 600;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryStorageService storageService;

    @Autowired
    ImageResizeService imageResizeService;

    public String createNewCategory(String categoryName, MultipartFile thumbnailImage) {
        String id = UUID.randomUUID().toString();
        String thumbnailUrl = uploadThumbnail(thumbnailImage, id);
        Category category = new Category(id, categoryName, createCategorySlug(categoryName), thumbnailUrl);
        System.out.println(thumbnailUrl);
        return add(category);
    }

    private String createCategorySlug(String categoryName) {
        return categoryName.toLowerCase().replace(" ", "-");
    }

    @Override
    public String add(Category category) {
        Category result = categoryRepository.insert(category);
        return result.getId();
    }

    @Override
    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

//    private String uploadThumbnail(MultipartFile thumbnailImage, String categoryId) {
//        try {
//            //Multipart file converted into buffered image
//            File convertedFile = FileConverter.convertMultiPartFileToFile(thumbnailImage, categoryId);
//            BufferedImage originalImage = ImageIO.read(convertedFile);
//            BufferedImage resizedImage = imageResizeService.resizeImageWithAspectRatio(originalImage, 600);
//
//            //writing the resized image back to the file
//            ImageIO.write(resizedImage, ".jpeg", convertedFile);
//            System.out.println(resizedImage.getWidth());
//            System.out.println(resizedImage.getHeight());
//            return storageService.uploadSimpleFile(convertedFile, categoryId);
//        } catch (Exception e) {
//            return "error uploading image" + e;
//        }
//    }

    private String uploadThumbnail(MultipartFile thumbnailImage, String categoryId) {
        try {
            BufferedImage resizedImage = getResizedImage(thumbnailImage, categoryId, THUMBNAIL_WIDTH);
            byte[] byteArray = convertImageToByteArray(resizedImage);
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(byteArray.length);
            metadata.setContentType("image/jpeg");
            System.out.println("Image ID: " + categoryId);
            return storageService.uploadFile(inputStream, metadata, categoryId);
        } catch (Exception e) {
            return "error uploading image" + e;
        }
    }

    private byte[] convertImageToByteArray(BufferedImage resizedImage) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream);
        byte[] buffer = outputStream.toByteArray();
        return buffer;
    }

    private BufferedImage getResizedImage(MultipartFile thumbnailImage, String categoryId, int targetWidth) throws InterruptedException, IOException {
        File convertedFile = FileConverter.convertMultiPartFileToFile(thumbnailImage, categoryId);
        BufferedImage image = imageResizeService.resizeImageWithAspectRatio(read(convertedFile), targetWidth);
        return image;
    }
}
