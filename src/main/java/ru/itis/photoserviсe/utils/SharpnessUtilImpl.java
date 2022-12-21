package ru.itis.photoserviсe.utils;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class SharpnessUtilImpl implements SharpnessUtil {

    @Autowired
    FileUploadUtil fileUploadUtil;

    @Value("${path.sharped-photo}")
    private String sharpedFolder;

    @Value("${path.edges-photo}")
    private String edgesFolder;

    @Override
    public String sharpness(double alpha, double beta, double gamma, String fileName) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread(fileName);

        Mat dest = new Mat(src.rows(), src.cols(), src.type());
        Imgproc.GaussianBlur(src, dest, new Size(0,0), 10);
        Core.addWeighted(src, alpha, dest, beta, gamma, dest);



        return fileUploadUtil.returnFile(fileName, sharpedFolder, dest);
    }

    @Override
    public String borderHighlighting(int lowThresh, String fileName) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat src = Imgcodecs.imread(fileName);
        Mat srcBlur = new Mat(src.rows(), src.cols(), src.type());
        Mat detectedEdges = new Mat(src.rows(), src.cols(), src.type());

        Imgproc.blur(src, srcBlur, new Size(3,3));
        Imgproc.Canny(srcBlur, detectedEdges, lowThresh, lowThresh * 3, 3, false);
        Mat dest = new Mat(src.size(), CvType.CV_8UC3, Scalar.all(0));
        src.copyTo(dest, detectedEdges);

        return fileUploadUtil.returnFile(fileName, edgesFolder, dest);
    }




}
