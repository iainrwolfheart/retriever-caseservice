package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;
import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class LocalImageStorageService implements ImageStorageService {

    private ImageRepository imageRepository;

    @Autowired
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

    @Override
    public ResponseEntity<String> saveItemImagesToStorage(String _caseId, String _itemId) {
        List<Image> itemImagesToStoreToDB = imageRepository.findBy(_itemId);

        try {
            storeItemImagesToLocalStorage(itemImagesToStoreToDB);
        } catch (IOException ioe){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Unable to store item images to long term storage. Please try again.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Appropriate response message here, please.");
    }

    public void storeItemImagesToLocalStorage(List<Image> itemImagesToStoreToDB) throws IOException {
        for (Image image : itemImagesToStoreToDB) {
            ByteArrayInputStream imageStream = new ByteArrayInputStream(image.getBinaryImage());
            BufferedImage imageBuffered = ImageIO.read(imageStream);

//            UPDATE THIS TO DYNAMICALLY CREATE/FIND FOLDER BY ITEMID
            ImageIO.write(imageBuffered, "jpg", new File(String.format(
                    "/Users/goz/Desktop/image_store/%s_%s.jpg", image.get_itemId(), image.get_imageId())
            ));
        }
    }
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

