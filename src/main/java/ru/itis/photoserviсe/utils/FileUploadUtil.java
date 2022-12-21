package ru.itis.photoserviсe.utils;

import org.opencv.core.Mat;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadUtil {
    void saveFile(String uploadDir, String fileName, MultipartFile multipartFile);
    String returnFile(String fileName, String folder, Mat dest) throws IOException;
}