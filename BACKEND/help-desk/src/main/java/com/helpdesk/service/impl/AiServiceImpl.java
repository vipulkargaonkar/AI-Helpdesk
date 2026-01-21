package com.helpdesk.service.impl;

import com.helpdesk.service.AiService;
import com.helpdesk.tools.TicketDatabaseTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;
    private final TicketDatabaseTool ticketDatabaseTool;

    public AiServiceImpl(ChatClient chatClient, TicketDatabaseTool ticketDatabaseTool) {
        this.chatClient = chatClient;
        this.ticketDatabaseTool = ticketDatabaseTool;
    }

    @Override
    public String getResponseFromAssistant(String query) {
        return this.chatClient
                .prompt()
                .tools(ticketDatabaseTool)
                .user(query)
                .call()
                .content();
    }
}
