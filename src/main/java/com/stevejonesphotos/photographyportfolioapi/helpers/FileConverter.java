package com.stevejonesphotos.photographyportfolioapi.helpers;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileConverter {

    public static File convertMultiPartFileToFile(MultipartFile fileToConvert, String fileName) {
        File convertedFile = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(convertedFile)) {
            fileOutputStream.write(fileToConvert.getBytes());
        } catch (IOException e) {
            System.out.println("Error converting multipartFile to file: " + e);
        }
        return convertedFile;
    }
}
