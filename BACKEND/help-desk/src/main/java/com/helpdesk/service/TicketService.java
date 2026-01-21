package com.helpdesk.service;

import com.helpdesk.entity.Ticket;

public interface TicketService {

    Ticket createTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    Ticket getTicket(Long ticketId);

    Ticket getTicketByEmailId(String username);
}
