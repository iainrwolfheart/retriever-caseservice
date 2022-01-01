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
                    .body("There was a problem saving this image... " +
                            "Either it already exists or there was another issue saving it.");
        }
    }

    /**
     * Stores image, including it's binary, to MongoDB table. This is done as the user is uploading images for an item.
     *      Once the user confirms the item, the item images are all saved in their original format to the desired
     *      solution (see saveItemImagesToStorage).
     *
     * Currently no obvious way to check if image already exists in the table.
     *      Searching by binaryImage of the imageToSave returns an empty list...
     *
     * @param image data transfer object, comprises Multipart File and related _itemId
     * @return stringified _imageId, auto-created as it's saved in MongoDB
     * @throws IOException - May be inappropriate
     */
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
            storeItemImagesToLocalStorage(itemImagesToStoreToDB, _itemId);
        } catch (IOException ioe){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("Unable to store item images to long term storage. Please try again.");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Appropriate response message here, please.");
    }

    /**
     * Handles the actual writing of images to local storage for dev purposes.
     * Uses _itemId to create folder to store images in
     * Each image is named using it's _imageId String
     * @param itemImagesToStoreToDB List of images.
     * @param _itemId to which all images in the list are bound
     * @throws IOException
     */
    public void storeItemImagesToLocalStorage(List<Image> itemImagesToStoreToDB, String _itemId) throws IOException {
        String path = "/Users/goz/Desktop/image_store/" + _itemId + "/";
        if (!Files.exists(Paths.get(path))) {
            File dirAsFile = new File(path);
            dirAsFile.mkdir();
        }
        for (Image image : itemImagesToStoreToDB) {
            try (FileOutputStream stream = new FileOutputStream(path + image.get_imageId())) {
                stream.write(image.getBinaryImage());
            }
        }
    }

    public ResponseEntity<String> removePresavedImageFromDB(String _imageId) {
        imageRepository.deleteBy_imageId(_imageId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Image removed successfully.");
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

