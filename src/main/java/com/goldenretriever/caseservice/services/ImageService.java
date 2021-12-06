package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;
import com.goldenretriever.caseservice.repositories.ImageRepository;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<String> presaveImage(MultipartFile image, String _itemId) throws FileSizeLimitExceededException {

//        Also need to handle exception thrown if file too big, maybs

        try {
            Image imageToSave = new Image(_itemId, image.getBytes());
            imageRepository.save(imageToSave);
            return ResponseEntity.status(HttpStatus.OK).body(imageToSave.get_imageId().toString());
        } catch (IOException ioe) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("There was a problem saving this image...");
        }
    }
}

