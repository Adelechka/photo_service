package ru.itis.photoserviсe.utils;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FiltersUtilImpl implements FiltersUtil {

    @Autowired
    FileUploadUtil fileUploadUtil;

    @Value("${path.sharped-photo}")
    private String sharpedFolder;

    @Value("${path.edges-photo}")
    private String edgesFolder;

    @Value("${path.noise-photo}")
    private String noiseFolder;

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

    @Override
    public String noiseAnalysis(double thresh, String fileName) throws IOException {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Mat imgFirstStage = Imgcodecs.imread(fileName);
        Mat guassinSecondStage = new Mat();
        Imgproc.GaussianBlur(imgFirstStage, guassinSecondStage, new Size(3, 3), 0);

        Mat weightedThirdStage = new Mat();
        Core.addWeighted(imgFirstStage, 1.5, guassinSecondStage, -0.5, 0, weightedThirdStage);

        Mat diffFourthStage = new Mat();
        Core.absdiff(weightedThirdStage, guassinSecondStage, diffFourthStage);

        Imgproc.threshold(diffFourthStage, diffFourthStage, thresh, 255, Imgproc.THRESH_BINARY);
        return fileUploadUtil.returnFile(fileName, noiseFolder, diffFourthStage);
    }
}
