package com.intuit.tms.services;

import com.intuit.tms.entities.TicketType;

import java.util.List;

import com.intuit.tms.dto.TicketTypeDTO;

public interface TicketTypeService {
	TicketType getTicketTypeByTitle(String title);

	TicketType getTicketTypeById(Long id);

	TicketType saveTicketType(TicketTypeDTO icketTypeDTO);

	List<TicketType> getAllTicketTypes();

	void deleteTicketType(Long id);

	TicketType updateTicketType(TicketTypeDTO ticketTypeDTO, Long ticketTypeId);
}