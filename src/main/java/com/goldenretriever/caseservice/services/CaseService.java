package com.goldenretriever.caseservice.services;

import com.goldenretriever.caseservice.entities.Case;
import com.goldenretriever.caseservice.entities.dto.CaseDto;
import com.goldenretriever.caseservice.repositories.CaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CaseService {

    private CaseRepository caseRepository;
    private ItemService itemService;

    public CaseService(CaseRepository caseRepository, ItemService itemService) {
        this.caseRepository = caseRepository;
        this.itemService = itemService;
    }

    public ResponseEntity<String> createCase(CaseDto createCaseDto) {
        Case newCase = new Case(
                createCaseDto.get_userId(),
                createCaseDto.getPoliceCaseNumber()
        );
        caseRepository.save(newCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCase.get_caseId().toString());
    }

    public ResponseEntity<String> deleteCase(String _caseId) {
//        Send caseId to itemService
        itemService.removeCaseItems(_caseId);
//        Get list of items by caseId
//        for each item
//              delete images from DB and Storage
//              Delete item from DB
//        Delete case from DB
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Not yet implemented!");

    }
}
