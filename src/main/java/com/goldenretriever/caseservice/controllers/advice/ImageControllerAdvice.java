package com.goldenretriever.caseservice.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@ControllerAdvice
public class ImageControllerAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException maxUploadSizeExceededException) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                .body("File must not exceed 5MB");
    }

//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<String> handleIOExceptionWhenStoringImagesToDB(IOException ioException) {
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
//                .body("Unable to store item images to long term storage. Please try again.");
//    }

}
