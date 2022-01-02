package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Image;

import java.io.IOException;
import java.util.List;

public interface ImageStorageService {

//    void init();

    void saveItemImagesToStorage(List<Image> itemImagesToStoreToDB, String _caseId, String _itemId)
             throws IOException;
//
//    Stream<Path> loadAll(String _itemId);
//
//    Path load(String filename);
//
//    Resource loadAsResource(String filename);
//
//    void deleteAll(String _itemId);

}
