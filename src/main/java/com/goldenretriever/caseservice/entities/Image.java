package com.goldenretriever.caseservice.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Image location URL is NOT currently a field of the Image class. This is because the location URL is built from
 * the class fields, specifically _itemId.toString and _imageId.toString().
 * This implementation may change as the production storage solution is introduced.
 */
@Document(collection = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId _imageId;
    private String _itemId;
    private byte[] binaryImage;

    public Image(String _itemId, byte[] binaryImage) {
        this._itemId = _itemId;
        this.binaryImage = binaryImage;
    }
}
