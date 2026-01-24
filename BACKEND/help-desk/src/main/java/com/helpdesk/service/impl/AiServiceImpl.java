package com.helpdesk.service.impl;

import com.helpdesk.service.AiService;
import com.helpdesk.tools.TicketDatabaseTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AiServiceImpl implements AiService {

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPromptResource;

    private final ChatClient chatClient;
    private final TicketDatabaseTool ticketDatabaseTool;

    public AiServiceImpl(ChatClient chatClient, TicketDatabaseTool ticketDatabaseTool) {
        this.chatClient = chatClient;
        this.ticketDatabaseTool = ticketDatabaseTool;
    }

    @Override
    public String getResponseFromAssistant(String query, String conversationId) {
        return this.chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .tools(ticketDatabaseTool)
                .system(systemPromptResource)
                .user(query)
                .call()
                .content();
    }

    @Override
    public Flux<String> streamResponseFromAssistant(String query, String conversationId) {
        return this.chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, conversationId))
                .tools(ticketDatabaseTool)
                .system(systemPromptResource)
                .user(query)
                .stream()
                .content();
    }
}
