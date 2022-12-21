package ru.itis.photoserviсe.utils;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class MetadataUtilImpl implements MetadataUtil {
    @Override
    public Metadata getMetadata(File image) throws IOException, ImageProcessingException {
        InputStream is = null;
        try {
            is = new FileInputStream(image);
            Metadata metadata = ImageMetadataReader.readMetadata(image);
            return metadata;
        } catch (FileNotFoundException e1) {
            throw new FileNotFoundException(e1.getMessage());
        } catch (ImageProcessingException e) {
            throw new ImageProcessingException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void getTagsWithGroups(Metadata metadata, HashMap<String, ArrayList<String>> tags) {
        Iterable<Directory> directories = metadata.getDirectories();
        for (Directory directory : directories) {
            if (directory != null) {
                tags.put(directory.getName(), new ArrayList<>());
                for (Tag tag : directory.getTags()) {
                    ArrayList<String> tmp = tags.get(directory.getName());
                    tmp.add(tag.getTagName());
                    tags.put(directory.getName(), tmp);
                }
            } else {
                System.out.println(directory + " is empty");
            }
        }
    }

    @Override
    public HashMap<String, String> getTags(Metadata metadata) {
        HashMap<String, String> tags = new HashMap<>();
        Iterable<Directory> directories = metadata.getDirectories();
        for (Directory directory : directories) {
            if (directory != null) {
                for (Tag tag : directory.getTags()) {
                    tags.put(tag.getTagName(), tag.getDescription());
                }
            } else {
                System.out.println(directory + " is empty");
            }
        }
        return tags;
    }
}
