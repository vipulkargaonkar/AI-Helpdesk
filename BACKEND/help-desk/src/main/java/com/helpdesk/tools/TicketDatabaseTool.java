package com.helpdesk.tools;

import com.helpdesk.entity.Ticket;
import com.helpdesk.service.TicketService;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class TicketDatabaseTool {

    private final TicketService ticketService;

    public TicketDatabaseTool(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Tool(description = "This tool helps to create new ticket in database.")
    public Ticket createTicketTool(@ToolParam(description = "Ticket fields required to create new ticket") Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @Tool(description = "This tool helps to get ticket by username.")
    public Ticket getTicketByUserName(@ToolParam(description = "Email id of the user is required") String emailId) {
        return ticketService.getTicketByEmailId(emailId);
    }

    @Tool(description = "This tool helps to update ticket.")
    public Ticket updateTicket(@ToolParam(description = "New ticket fields required to update ticket with ticket id.") Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    @Tool(description = "This tool helps to get current system time.")
    public String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis());
    }
}
