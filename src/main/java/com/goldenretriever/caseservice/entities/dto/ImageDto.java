package com.goldenretriever.caseservice.entities.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageDto {
    private MultipartFile image;
    private String _itemId;
}
