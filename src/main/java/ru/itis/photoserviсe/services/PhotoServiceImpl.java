package ru.itis.photoserviсe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.photoserviсe.model.PhotoModel;
import ru.itis.photoserviсe.repositories.PhotoRepository;

@Service
public class PhotoServiceImpl implements PhotoService {

//    @Autowired
//    private PhotoRepository photoRepository;

//    @Autowired
//    public void setPhotoRepository(PhotoRepository photoRepository) {
//        this.photoRepository = photoRepository;
//    }

    @Override
    public void savePhoto(PhotoModel photoModel) {
//        photoRepository.save(photoModel);
    }
}
