package com.helpdesk.service.impl;

import com.helpdesk.entity.Ticket;
import com.helpdesk.repository.TicketRepository;
import com.helpdesk.service.TicketService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket createTicket(Ticket ticket) {
        ticket.setId(null);
        return ticketRepository.save(ticket);
    }

    @Override
    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicket(Long ticketId) {
        return ticketRepository.findById(ticketId).orElse(null);
    }

    @Override
    public Ticket getTicketByEmailId(String username) {
        return ticketRepository.findByEmail(username).orElse(null);
    }
}
