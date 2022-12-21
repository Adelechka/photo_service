package ru.itis.photoserviсe.services;

import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.photoserviсe.utils.FileUploadUtil;
import ru.itis.photoserviсe.utils.MetadataUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class MetadataServiceImpl implements MetadataService {

    @Autowired
    private MetadataUtil metadataUtil;

    @Override
    public HashMap<String, String> getMetadata(String filename) throws ImageProcessingException, IOException {
        File image = new File(filename);
        Metadata metadata = metadataUtil.getMetadata(image);
        return  metadataUtil.getTags(metadata);
    }
}
