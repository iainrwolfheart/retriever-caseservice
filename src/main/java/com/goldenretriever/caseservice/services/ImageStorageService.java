package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.dto.ImageDto;

import java.io.IOException;

public interface ImageStorageService {

//    void init();

    /**
    @return _imageId
     */
    String saveImageDetailsToDB(ImageDto image) throws IOException;

//    boolean store(MultipartFile file);
//
//    Stream<Path> loadAll();
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
//    void deleteAll(String _itemId);

}
