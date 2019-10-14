package com.intuit.tms.services;

import com.intuit.tms.entities.Ticket;

import java.util.List;

import com.intuit.tms.dto.TicketDTO;

public interface TicketService {
	List<Ticket> serachTickets(Ticket filter);

	Ticket getTicketById(Long ticketId);

	Ticket saveTicket(TicketDTO ticketDTO);

	List<Ticket> getAllTickets();

	Ticket updateTicket(TicketDTO ticketDTO, Long ticketId);

	void deleteTicket(Long ticketId);
}