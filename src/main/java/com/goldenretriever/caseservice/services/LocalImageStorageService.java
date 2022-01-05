package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class LocalImageStorageService implements ImageStorageService {

    /**
     * Handles the actual writing of images to local storage for dev purposes.
     * Uses _itemId to create folder to store images in
     *
     * Need to mkdir based on _caseId also but needs more code! CBA for now
     *
     * Each image is named using it's _imageId String
     * @param itemImagesToStoreToDB List of images.
     * @param _itemId to which all images in the list are bound
     * @throws IOException
     */
    @Override
    public void saveItemImagesToStorage(List<Image> itemImagesToStoreToDB, String _caseId, String _itemId) throws IOException {
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

    @Override
    public void deleteAll(String _itemId) throws IOException {
        String path = "/Users/goz/Desktop/image_store/" + _itemId + "/";
        FileUtils.deleteDirectory(new File(path));
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

