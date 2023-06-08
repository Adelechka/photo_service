package ru.itis.photoserviсe.utils;

import java.io.IOException;

public interface FiltersUtil {
    String sharpness(double alpha, double beta, double gamma, String fileName) throws IOException;
    String borderHighlighting(int lowThresh, String fileName) throws IOException;
    String noiseAnalysis(double thresh, String fileName) throws IOException;
}
