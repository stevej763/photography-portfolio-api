package com.stevejonesphotos.photographyportfolioapi.service;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

@Component
public class ImageResizeService {

    public BufferedImage resizeImageWithAspectRatio(BufferedImage originalImage, int targetWidth) throws InterruptedException {
        int targetHeight = (int) calculateHeightFromWidth(originalImage.getWidth(), originalImage.getHeight(), targetWidth);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        drawImage(originalImage, targetWidth, targetHeight, resizedImage);
        System.out.println(resizedImage.getHeight());
        System.out.println(resizedImage.getWidth());
        return resizedImage;
    }

    private double calculateHeightFromWidth(double originalImageWidth, double originalImageHeight, double targetWidth) {
        double aspectRatio = originalImageWidth/originalImageHeight;
        return targetWidth/aspectRatio;
    }

    private void drawImage(BufferedImage originalImage, int targetWidth, int targetHeight, BufferedImage resizedImage) {
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
    }
}
