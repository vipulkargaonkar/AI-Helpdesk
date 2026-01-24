package com.helpdesk.service;

import reactor.core.publisher.Flux;

public interface AiService {

    String getResponseFromAssistant(String query, String conversationId);

    Flux<String> streamResponseFromAssistant(String query, String conversationId);
}
