package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.services.ImageService;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/presave/{_itemId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> presaveImage(
            @PathVariable(name = "_itemId") String _itemId,
            @RequestBody MultipartFile image) {
        try {
            return imageService.presaveImage(image, _itemId);
        } catch (FileSizeLimitExceededException fileSizeExc) {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("There was a problem saving this image...");
        }
    }
}
