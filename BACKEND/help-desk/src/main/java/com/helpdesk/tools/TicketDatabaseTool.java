package com.helpdesk.tools;

import com.helpdesk.entity.Ticket;
import com.helpdesk.enums.Priority;
import com.helpdesk.enums.Status;
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
    public Ticket createTicketTool(@ToolParam(description = "Short summary") String summary,
                                   @ToolParam(description = "Detailed description") String description,
                                   @ToolParam(description = "User email") String email,
                                   @ToolParam(description = "Category") String category,
                                   @ToolParam(description = "Priority (LOW, MEDIUM, HIGH, URGENT)") String priority) {
        Ticket ticket = new Ticket();
        ticket.setSummary(summary);
        ticket.setDescription(description);
        ticket.setEmail(email);
        ticket.setCategory(category);
        ticket.setPriority(Priority.valueOf(priority.toUpperCase()));
        ticket.setStatus(Status.OPEN);
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
