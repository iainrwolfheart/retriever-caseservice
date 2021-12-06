package com.goldenretriever.caseservice.entities;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "images")
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId _imageId;
    private String _itemId;
    private byte[] binaryImage;

    public Image(String _itemId, byte[] imageBytes) {
        this._itemId = _itemId;
        this.binaryImage = imageBytes;
    }
}
