package com.interview.platform.mod26_ai_prompt_management;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class AiPromptManagementController {

    private final AiPromptManagementService service;

    public AiPromptManagementController(AiPromptManagementService service) {
        this.service = service;
    }
}
