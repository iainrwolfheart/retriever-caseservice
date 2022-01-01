package com.goldenretriever.caseservice.controllers;

import com.goldenretriever.caseservice.entities.dto.ImageDto;
import com.goldenretriever.caseservice.services.LocalImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
public class ImageController {

    private LocalImageStorageService localImageStorageService;

    @Autowired
    public ImageController(LocalImageStorageService localImageStorageService) {
        this.localImageStorageService = localImageStorageService;
    }

    @PostMapping(value = "/presave", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> presaveImage(@RequestParam("image") MultipartFile image,
                                               @RequestParam("_itemId") String _itemId) {
        ImageDto imageDto = new ImageDto(image, _itemId);
        return localImageStorageService.presaveImage(imageDto);
    }

    @PostMapping(value = "/saveitemimages/{_caseId}/{_itemId}")
    public ResponseEntity<String> saveItem(@PathVariable(name = "_caseId") String _caseId,
                                           @PathVariable(name = "_itemId") String _itemId) {
        return localImageStorageService.saveItemImagesToStorage(_caseId, _itemId);
    }

//    deselect image - remove individual image from presave table
    @DeleteMapping(value = "/removepresavedimage/{_imageId}")
    public ResponseEntity<String> removePresavedImage(@PathVariable(name = "_imageId") String _imageId) {
        return localImageStorageService.removePresavedImageFromDB(_imageId);
    }
//    delete item and remove all images
}
