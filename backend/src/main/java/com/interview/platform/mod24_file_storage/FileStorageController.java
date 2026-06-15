package com.interview.platform.mod24_file_storage;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class FileStorageController {

    private final FileStorageService service;

    public FileStorageController(FileStorageService service) {
        this.service = service;
    }
}
