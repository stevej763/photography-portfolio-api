package com.stevejonesphotos.photographyportfolioapi.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class CategoryStorageService implements AwsStorageService {

    private final String FILE_PREFIX = "category/thumbnail/";

    @Autowired
    private AmazonS3 s3Client;

    @Autowired
    private Environment environment;

    @Override
    public String uploadFile(InputStream inputStream, ObjectMetadata metadata, String fileName) {
            String bucketName = environment.getProperty("application.bucket.name");
            System.out.println("attempting to upload file");
            String fileKey = FILE_PREFIX+fileName+".jpeg";
            s3Client.putObject(new PutObjectRequest(bucketName, fileKey, inputStream, metadata));
            return s3Client.getUrl(bucketName, fileKey).toString();
    }

}
