package com.interview.platform.mod04_technology_management;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class TechnologyManagementController {

    private final TechnologyManagementService service;

    public TechnologyManagementController(TechnologyManagementService service) {
        this.service = service;
    }
}
