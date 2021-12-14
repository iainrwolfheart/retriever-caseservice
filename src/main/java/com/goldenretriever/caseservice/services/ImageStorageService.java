package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.dto.ImageDto;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface ImageStorageService {

//    void init();

    /**
    @return _imageId
     */
    String saveImageDetailsToDB(ImageDto image) throws IOException;

     ResponseEntity<String> saveItemImagesToStorage(String _caseId, String _itemId) throws IOException;
//
//    Stream<Path> loadAll(String _itemId);
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
//    void deleteAll(String _itemId);

}
