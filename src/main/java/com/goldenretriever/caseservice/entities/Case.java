package com.goldenretriever.caseservice.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "cases")
@NoArgsConstructor
@Data
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ObjectId _caseId;
    private String _userId;
    private String policeCaseNumber;
    private List<Item> items;

    public Case(String _userId, String policeCaseNumber) {
        this._userId = _userId;
        this.policeCaseNumber = policeCaseNumber;
        items = new ArrayList<>();
    }
}
