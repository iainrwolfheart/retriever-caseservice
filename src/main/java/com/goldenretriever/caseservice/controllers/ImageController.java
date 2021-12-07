package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.services.LocalImageStorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {

    private LocalImageStorageService localImageStorageService;

    public ImageController(LocalImageStorageService localImageStorageService) {
        this.localImageStorageService = localImageStorageService;
    }

    @PostMapping(value = "/presave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> presaveImage(@ModelAttribute ImageDto imageDto) {
        return localImageStorageService.presaveImage(imageDto);
    }
}
