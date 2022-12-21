package ru.itis.photoserviсe.utils;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadUtilImpl implements FileUploadUtil {
    @Override
    public void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path uploadPath = Paths.get(uploadDir);
            Files.createDirectories(uploadPath);
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not save image file: " + fileName, ioe);
        }
    }

    @Override
    public String returnFile(String fileName, String folder, Mat dest) throws IOException {
        MatOfByte matOfByte = new MatOfByte();

        Imgcodecs.imencode(".jpg", dest, matOfByte);
        byte[] byteArray = matOfByte.toArray();

        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(in);

        String shortName = fileName.split("/")[fileName.split("/").length - 1];

        File outputFile = new File(folder + shortName);
        System.out.println(outputFile.getPath());
        ImageIO.write(bufImage, "jpg", outputFile);
        in.close();

        return outputFile.toString().replace("\\", "/");
    }
}
