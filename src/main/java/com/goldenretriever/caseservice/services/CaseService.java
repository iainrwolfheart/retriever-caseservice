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

    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public ResponseEntity<String> createCase(CaseDto createCaseDto) {
        Case newCase = new Case(
                createCaseDto.get_userId(),
                createCaseDto.getPoliceCaseNumber()
        );
        caseRepository.save(newCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCase.get_caseId().toString());
    }
}
