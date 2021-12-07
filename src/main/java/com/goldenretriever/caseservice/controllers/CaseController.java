package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.entities.dto.CaseDto;
import com.goldenretriever.caseservice.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cases")
public class CaseController {

    @Autowired
    private CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCase(
            @RequestBody CaseDto createCaseDto) {
        return caseService.createCase(createCaseDto);
    }
//    Create case (no images)
//    Upload images
//    - Will need to create a folder with case id as name
}
