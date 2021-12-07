package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;
import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.repositories.ImageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocalImageStorageService implements ImageStorageService {

    private ImageRepository imageRepository;

    public LocalImageStorageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<String> presaveImage(ImageDto image) {
        try {
            String _imageId = saveImageDetailsToDB(image);
            return ResponseEntity.status(HttpStatus.OK).body(_imageId);
        } catch (IOException ioe) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("There was a problem saving this image...");
        }
    }

    @Override
    public String saveImageDetailsToDB(ImageDto image) throws IOException {
        Image imageToSave = new Image(image.get_itemId(), image.getImage().getBytes());
        imageRepository.save(imageToSave);
        return imageToSave.get_imageId().toString();
    }

//    @Override
//    public boolean store(MultipartFile file) {
//        return false;
//    }
//
//    @Override
//    public Stream<Path> loadAll() {
//        return null;
//    }
//
//    @Override
//    public Path load(String filename) {
//        return null;
//    }
//
//    @Override
//    public Resource loadAsResource(String filename) {
//        return null;
//    }
//
//    @Override
//    public void deleteAll(String _itemId) {
//
//    }
}

