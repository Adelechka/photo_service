package ru.itis.photoserviсe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.photoserviсe.model.PhotoModel;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoModel, Long> {
}
