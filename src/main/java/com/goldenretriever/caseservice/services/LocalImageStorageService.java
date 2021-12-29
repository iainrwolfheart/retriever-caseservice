package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;
import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        String path = "/Users/goz/Desktop/image_store/";

        int tally = 0;

        System.out.println("There are " + itemImagesToStoreToDB.size() + " images to save.");

        for (Image image : itemImagesToStoreToDB) {
            System.out.println("ImageID: " + image.get_imageId());
            tally += storeImage(image);
        }

        System.out.println(tally + " images should have been successfully saved...");
    }

    public int storeImage(Image image) throws IOException {
        String path = "/Users/goz/Desktop/image_store/";
        String dir = image.get_itemId() + "/";

        if (!Files.exists(Paths.get(path.concat(dir)))) {
            File dirAsFile = new File(path.concat(dir));
            dirAsFile.mkdir();
        }

        try (FileOutputStream stream = new FileOutputStream(path + dir + image.get_imageId())) {
            stream.write(image.getBinaryImage());
        }

        return 1;
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

