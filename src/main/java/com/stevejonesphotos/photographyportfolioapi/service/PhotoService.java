package com.stevejonesphotos.photographyportfolioapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.stevejonesphotos.photographyportfolioapi.domain.ImageDetail;
import com.stevejonesphotos.photographyportfolioapi.domain.Photo;
import com.stevejonesphotos.photographyportfolioapi.helpers.FileConverter;
import com.stevejonesphotos.photographyportfolioapi.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.stevejonesphotos.photographyportfolioapi.domain.ImageDetail.*;
import static javax.imageio.ImageIO.read;

@Component
public class PhotoService implements PersistedDataService<Photo, String> {

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    PhotoStorageService storageService;

    @Autowired
    ImageResizeService imageResizeService;

    public String addNewPhoto(String title, String description, String alt, List<String> categories, MultipartFile imageFile) {
        String id = UUID.randomUUID().toString();
        String thumbnailUrl = uploadImage(imageFile, id, THUMBNAIL);
        String smallUrl = uploadImage(imageFile, id, SMALL);
        String mediumUrl = uploadImage(imageFile, id, MEDIUM);
        String largeUrl = uploadImage(imageFile, id, LARGE);
        String originalUrl = uploadImage(imageFile, id, ORIGINAL);
        Photo photo = new Photo(
                id,
                title,
                createPhotoSlug(title),
                alt,
                description,
                categories,
                thumbnailUrl,
                smallUrl,
                mediumUrl,
                largeUrl,
                originalUrl);
        return add(photo);
    }

    @Override
    public String add(Photo photo) {
        photoRepository.insert(photo);
        return "";
    }

    private String createPhotoSlug(String photoTitle) {
        return photoTitle.toLowerCase().replace(" ", "-");
    }

    @Override
    public Optional<Photo> findById(String id) {
        return photoRepository.findById(id);
    }

    public String uploadImage(MultipartFile photoFile, String photoId, ImageDetail imageDetail) {
        try {
            BufferedImage bufferedImage = getBufferedImage(photoFile, photoId, imageDetail);
            byte[] byteArray = convertImageToByteArray(bufferedImage);
            InputStream inputStream = new ByteArrayInputStream(byteArray);
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(byteArray.length);
            metadata.setContentType("image/jpeg");
            System.out.println("Image ID: " + photoId);
            return storageService.uploadFile(inputStream, metadata, photoId, imageDetail);
        } catch (Exception e) {
            return "error uploading image" + e;
        }
    }

    @Override
    public List<Photo> findAll() {
        return photoRepository.findAll();
    }

    public List<Photo> findAllForCategory(String categoryId) {
        return photoRepository.findByCategories(categoryId);
    }

    private byte[] convertImageToByteArray(BufferedImage resizedImage) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "jpg", outputStream);
        return outputStream.toByteArray();
    }

    private BufferedImage getBufferedImage(MultipartFile thumbnailImage, String categoryId, ImageDetail imageDetail) throws InterruptedException, IOException {
        File convertedFile = FileConverter.convertMultiPartFileToFile(thumbnailImage, categoryId);
        if (imageDetail != ORIGINAL) {
            System.out.println("Generating resized image");
            BufferedImage image = imageResizeService.resizeImageWithAspectRatio(read(convertedFile), imageDetail.getWidth());
            convertedFile.delete();
            return image;
        } else {
            System.out.println("Compressing original image");
            BufferedImage bufferedImage = read(convertedFile);
            convertedFile.delete();
            return bufferedImage;
        }

    }
}
