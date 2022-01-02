package com.goldenretriever.caseservice.services;

import org.springframework.stereotype.Service;

@Service
public class StorageServiceFactory implements AbstractFactory<ImageStorageService> {

    @Override
    public ImageStorageService create(String storageService) {
        if (storageService.equalsIgnoreCase("Local")) {
            return new LocalImageStorageService();
        }
        return null;
    }

}
