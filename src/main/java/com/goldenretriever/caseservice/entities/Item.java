package com.goldenretriever.caseservice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _itemId;
    private String _caseId;
    private String name;
    private String description;
//    private List<String> _imageIds;

    public Item(String _caseId, String name, String description) {
        this._caseId = _caseId;
        this.name = name;
        this.description = description;
//        this._imageIds = new ArrayList<>();
    }
}
