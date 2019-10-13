package com.intuit.tms.services;

import com.intuit.tms.entities.TicketType;

import java.util.List;

import com.intuit.tms.dto.TicketTypeDTO;
import com.intuit.tms.dto.TicketTypeUpdateDTO;

public interface TicketTypeService {
	TicketType getTicketTypeByTicketType(String ticketType);

	TicketType saveTicketType(TicketTypeDTO ticketTypeDTO);

	List<TicketType> getAllTicketTypes();

	TicketType updateTicketType(TicketTypeUpdateDTO ticketTypeUpdateDTO, String ticketType);

	void deleteTicketType(String ticketType);
}