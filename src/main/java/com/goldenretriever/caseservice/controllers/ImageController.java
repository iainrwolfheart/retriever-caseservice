package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.services.ImageService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/presave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> presaveImage(@ModelAttribute ImageDto image) {
        try {
            return imageService.presaveImage(image);
        } catch (FileSizeLimitExceededException fileSizeExc) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("There was a problem saving this image...");
        }
    }
}
