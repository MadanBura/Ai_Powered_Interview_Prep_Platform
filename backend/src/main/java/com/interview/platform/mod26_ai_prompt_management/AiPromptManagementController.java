package com.interview.platform.mod26_ai_prompt_management;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class AiPromptManagementController {

    private final AiPromptManagementService service;

    public AiPromptManagementController(AiPromptManagementService service) {
        this.service = service;
    }

    @GetMapping("/placeholder")
    public ResponseEntity<?> placeholder() {
        int status = service.handle();
        return ResponseEntity.status(status).build();
    }

    @GetMapping("/admin/ai/prompts")
    public ResponseEntity<?> listPrompts() {
        int status = service.handle();
        if (status != 200 && status != 201 && status != 202) {
            return ResponseEntity.status(status).build();
        }
        Map<String, Object> serviceRes = service.listAiPrompts(new HashMap<>());
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("success", true);
        wrapper.put("data", serviceRes);
        wrapper.put("error", null);
        return ResponseEntity.ok(wrapper);
    }

    @PostMapping("/admin/ai/prompts")
    public ResponseEntity<?> createPrompt(@RequestBody Map<String, Object> req) {
        int status = service.handle();
        if (status != 200 && status != 201 && status != 202) {
            return ResponseEntity.status(status).build();
        }
        Map<String, Object> serviceRes = service.createAiPrompt(req);
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("success", true);
        wrapper.put("data", serviceRes.get("data"));
        wrapper.put("error", null);
        return ResponseEntity.ok(wrapper);
    }

    @PutMapping("/admin/ai/prompts/{id}")
    public ResponseEntity<?> updatePrompt(@PathVariable("id") String id, @RequestBody Map<String, Object> req) {
        int status = service.handle();
        if (status != 200 && status != 201 && status != 202) {
            return ResponseEntity.status(status).build();
        }
        Map<String, Object> serviceRes = service.updateAiPrompt(req, id);
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("success", true);
        wrapper.put("data", serviceRes);
        wrapper.put("error", null);
        return ResponseEntity.ok(wrapper);
    }

    @DeleteMapping("/admin/ai/prompts/{id}")
    public ResponseEntity<?> deletePrompt(@PathVariable("id") String id) {
        int status = service.handle();
        if (status != 200 && status != 201 && status != 202) {
            return ResponseEntity.status(status).build();
        }
        Map<String, Object> serviceRes = service.deleteAiPrompt(id);
        Map<String, Object> wrapper = new HashMap<>();
        wrapper.put("success", true);
        wrapper.put("data", serviceRes);
        wrapper.put("error", null);
        return ResponseEntity.ok(wrapper);
    }
}
