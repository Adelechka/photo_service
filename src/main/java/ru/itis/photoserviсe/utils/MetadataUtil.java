package ru.itis.photoserviсe.utils;


import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface MetadataUtil {
    Metadata getMetadata(File image) throws IOException, ImageProcessingException;
    void getTagsWithGroups(Metadata metadata, HashMap<String, ArrayList<String>> tags);
    HashMap<String, String> getTags(Metadata metadata);
}
