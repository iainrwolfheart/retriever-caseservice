package com.goldenretriever.caseservice.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ImageDto {
    private MultipartFile image;
    private String _itemId;
}
