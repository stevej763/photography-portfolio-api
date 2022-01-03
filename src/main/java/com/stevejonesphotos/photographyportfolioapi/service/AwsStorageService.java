package com.stevejonesphotos.photographyportfolioapi.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.stevejonesphotos.photographyportfolioapi.domain.ImageDetail;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public interface AwsStorageService {

    public String uploadFile(InputStream inputStream, ObjectMetadata metadata, String fileName, ImageDetail imageDetail);

}
