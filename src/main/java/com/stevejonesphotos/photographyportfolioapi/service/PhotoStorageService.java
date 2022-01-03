package com.stevejonesphotos.photographyportfolioapi.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.stevejonesphotos.photographyportfolioapi.domain.ImageDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class PhotoStorageService implements AwsStorageService {

    private final String FILE_PREFIX = "photo/";

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private Environment environment;

    @Override
    public String uploadFile(InputStream inputStream, ObjectMetadata metadata, String fileName, ImageDetail imageDetail) {
        String bucketName = environment.getProperty("application.bucket.name");
        String fileKey = FILE_PREFIX + imageDetail.getFolderName() + fileName + ".jpeg";
        s3Client.putObject(new PutObjectRequest(bucketName, fileKey, inputStream, metadata));
        System.out.println("the " + imageDetail.getFolderName() + " file has been uploaded.");
        return s3Client.getUrl(bucketName, fileKey).toString();
    }
}
