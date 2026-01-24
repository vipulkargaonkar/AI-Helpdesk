package com.helpdesk.controller;

import com.helpdesk.service.AiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/ai")
public class AiController {

    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody String query, @RequestHeader String conversationId) {
        return ResponseEntity.ok(aiService.getResponseFromAssistant(query, conversationId));
    }

    @PostMapping(value = "/stream")
    public Flux<String> streamResponse(@RequestBody String query, @RequestHeader("ConversationId") String conversationId) {
        return this.aiService.streamResponseFromAssistant(query, conversationId);
    }
}
