package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.TicketType;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.TicketTypeRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.TicketTypeService;
import com.intuit.tms.dto.TicketTypeDTO;
import com.intuit.tms.dto.TicketTypeUpdateDTO;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

	@Autowired
	TicketTypeRepository ticketTypeRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AccountTeamMapRepository accountTeamMapRepository;

	@Override
	public TicketType getTicketTypeByTicketType(String strTicketType) {
		Optional<TicketType> ticketType = ticketTypeRepository.findByTicketType(strTicketType);
		if (ticketType.isPresent()) {
			return ticketType.get();
		}
		return null;
	}

	@Override
	public TicketType saveTicketType(TicketTypeDTO ticketTypeDTO) {
		Long createdBy = customUserDetailsService.getCurrentLoggedInUserId();
		TicketType ticketType = new TicketType();
		ticketType.setTicketType(ticketTypeDTO.getTicketType());
		ticketType.setDescription(ticketTypeDTO.getDescription());
		ticketType.setCreatedBy(createdBy);
		return ticketTypeRepository.save(ticketType);
	}

	@Override
	public List<TicketType> getAllTicketTypes() {
		return ticketTypeRepository.findAll();
	}

	@Override
	public void deleteTicketType(String ticketType) {
		ticketTypeRepository.deleteByTicketType(ticketType);
	}

	@Override
	public TicketType updateTicketType(TicketTypeUpdateDTO ticketTypeUpdateDTO, String strTicketType) {
		Long updatedBy = customUserDetailsService.getCurrentLoggedInUserId();
		TicketType ticketType = new TicketType();
		ticketType.setTicketType(strTicketType);
		ticketType.setDescription(ticketTypeUpdateDTO.getDescription());
		ticketType.setUpdatedBy(updatedBy);
		ticketType.setUpdatedOn(LocalDateTime.now());
		return ticketTypeRepository.save(ticketType);
	}
}
