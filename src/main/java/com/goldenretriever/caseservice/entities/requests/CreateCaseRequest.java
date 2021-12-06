package com.goldenretriever.caseservice.entities.requests;

import lombok.Data;

@Data
public class CreateCaseRequest {
    private String _userId;
    private String policeCaseNumber;
}
