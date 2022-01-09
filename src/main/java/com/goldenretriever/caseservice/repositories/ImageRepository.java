package com.goldenretriever.caseservice.repositories;

import com.goldenretriever.caseservice.entities.Image;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image, ObjectId> {

    List<Image> findBy_itemId(String _itemId);

    @Query(value = "{'binaryImage': '0?'}")
    List<Image> findByBinaryImage(byte[] binaryImage);

    void deleteBy_imageId(String _imageId);
// hmmmmmmm
    void deleteBy_itemId(String _itemId);
}
