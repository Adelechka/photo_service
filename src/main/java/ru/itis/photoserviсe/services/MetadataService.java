package ru.itis.photoserviсe.services;

import com.drew.imaging.ImageProcessingException;

import java.io.IOException;
import java.util.HashMap;

public interface MetadataService {
    HashMap<String, String> getMetadata(String filename) throws ImageProcessingException, IOException;
}
