package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.entities.dto.CaseDto;
import com.goldenretriever.caseservice.services.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{_caseId}")
    public ResponseEntity<String> deleteCase(@PathVariable String _caseId) {
        return caseService.deleteCase(_caseId);
    }
}
