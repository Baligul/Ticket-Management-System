package com.intuit.tms.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.intuit.tms.entities.TicketType;
import com.intuit.tms.repositories.AccountTeamMapRepository;
import com.intuit.tms.repositories.TicketTypeRepository;
import com.intuit.tms.security.CustomUserDetailsService;
import com.intuit.tms.services.TicketTypeService;
import com.intuit.tms.dto.TicketTypeDTO;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

	@Autowired
	TicketTypeRepository ticketTypeRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AccountTeamMapRepository accountTeamMapRepository;

	@Override
	public TicketType getTicketTypeByTitle(String title) {
		Optional<TicketType> ticketType = ticketTypeRepository.findByTitle(title);
		if (ticketType.isPresent()) {
			return ticketType.get();
		}
		return null;
	}

	@Override
	public TicketType getTicketTypeById(Long ticketTypeId) {
		Optional<TicketType> ticketType = ticketTypeRepository.findById(ticketTypeId);
		if (ticketType.isPresent()) {
			return ticketType.get();
		}
		return null;
	}

	@Override
	public TicketType saveTicketType(TicketTypeDTO ticketTypeDTO) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long createdBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		TicketType ticketType = new TicketType();
		ticketType.setTitle(ticketTypeDTO.getTitle());
		ticketType.setDescription(ticketTypeDTO.getDescription());
		ticketType.setCreatedBy(createdBy);
		return ticketTypeRepository.save(ticketType);
	}

	@Override
	public List<TicketType> getAllTicketTypes() {
		return ticketTypeRepository.findAll();
	}

	@Override
	public void deleteTicketType(Long ticketTypeId) {
		ticketTypeRepository.deleteById(ticketTypeId);
	}

	@Override
	public TicketType updateTicketType(TicketTypeDTO ticketTypeDTO, Long ticketTypeId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long updatedBy = customUserDetailsService.getAccountIdByUserName(auth.getName());
		TicketType ticketType = new TicketType();
		ticketType.setId(ticketTypeId);
		ticketType.setTitle(ticketTypeDTO.getTitle());
		ticketType.setDescription(ticketTypeDTO.getDescription());
		ticketType.setUpdatedBy(updatedBy);
		ticketType.setUpdatedOn(LocalDateTime.now());
		return ticketTypeRepository.save(ticketType);
	}
}
