package com.ingsw.restservice.controller;

import com.ingsw.restservice.model.DTO.ImageUploadResponse;
import com.ingsw.restservice.storage.StorageFileNotFoundException;
import com.ingsw.restservice.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/accommodation/image/")
    public ResponseEntity<ImageUploadResponse> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                        RedirectAttributes redirectAttributes) {

        String fileUrl=storageService.store(file);

        return new ResponseEntity<ImageUploadResponse>(new ImageUploadResponse(
                "You successfully uploaded " + file.getOriginalFilename() + "!",
                fileUrl),HttpStatus.OK);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
