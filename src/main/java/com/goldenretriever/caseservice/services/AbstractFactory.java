package com.goldenretriever.caseservice.services;

public interface AbstractFactory<T> {

    T create(String storageService);
}
