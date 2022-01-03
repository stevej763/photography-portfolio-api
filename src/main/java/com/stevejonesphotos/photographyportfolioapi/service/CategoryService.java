package com.stevejonesphotos.photographyportfolioapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.stevejonesphotos.photographyportfolioapi.domain.Category;
import com.stevejonesphotos.photographyportfolioapi.domain.ImageDetail;
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

import static com.stevejonesphotos.photographyportfolioapi.domain.ImageDetail.THUMBNAIL;
import static javax.imageio.ImageIO.read;

@Component
public class CategoryService implements PersistedDataService<Category, String> {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryStorageService storageService;

    @Autowired
    ImageResizeService imageResizeService;

    public String createNewCategory(String categoryName, MultipartFile thumbnailImage) {
        String id = UUID.randomUUID().toString();
        String categoryThumbnailUrl = uploadThumbnail(thumbnailImage, id, THUMBNAIL);
        Category category = new Category(id, categoryName, createCategorySlug(categoryName), categoryThumbnailUrl);
        System.out.println(categoryThumbnailUrl);
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

    private String uploadThumbnail(MultipartFile thumbnailImage, String categoryId, ImageDetail imageDetail) {
        try {
            BufferedImage resizedImage = getResizedImage(thumbnailImage, categoryId, imageDetail);
            byte[] byteArray = convertImageToByteArray(resizedImage);
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(byteArray.length);
            metadata.setContentType("image/jpeg");
            System.out.println("Image ID: " + categoryId);
            return storageService.uploadFile(inputStream, metadata, categoryId, imageDetail);
        } catch (Exception e) {
            return "error uploading image" + e;
        }
    }

    private BufferedImage getResizedImage(MultipartFile thumbnailImage, String categoryId, ImageDetail imageDetail) throws InterruptedException, IOException {
        File convertedFile = FileConverter.convertMultiPartFileToFile(thumbnailImage, categoryId);
        BufferedImage image = imageResizeService.resizeImageWithAspectRatio(read(convertedFile), imageDetail.getWidth());
        convertedFile.delete();
        return image;
    }

    private byte[] convertImageToByteArray(BufferedImage resizedImage) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream);
        byte[] buffer = outputStream.toByteArray();
        return buffer;
    }
}
