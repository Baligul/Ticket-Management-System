package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.TicketTypeDTO;
import com.intuit.tms.entities.TicketType;
import com.intuit.tms.services.TicketTypeService;

@RestController
public class TicketTypeManagementRestController {

	@Autowired
	private TicketTypeService ticketTypeService;

	@ModelAttribute("ticketType")
	public TicketTypeDTO ticketTypeDTO() {
		return new TicketTypeDTO();
	}

	@PostMapping("/admin/api/v1/ticketType")
	public ResponseEntity<Object> saveTicketType(@ModelAttribute("ticketType") @Valid TicketTypeDTO ticketTypeDTO,
			BindingResult result) {

		TicketType existing = ticketTypeService.getTicketTypeByTitle(ticketTypeDTO.getTitle());
		if (existing != null) {
			return new ResponseEntity<Object>(
					"There is already exist a ticketType with title as " + ticketTypeDTO.getTitle(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		ticketTypeService.saveTicketType(ticketTypeDTO);

		return new ResponseEntity<Object>("You have successfully added a ticketType", HttpStatus.OK);

	}

	@GetMapping("/admin/api/v1/ticketTypes")
	public ResponseEntity<Object> getTicketTypes() {

		try {
			List<TicketType> ticketTypes = ticketTypeService.getAllTicketTypes();
			return new ResponseEntity<Object>(ticketTypes, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/admin/api/v1/ticketType/{ticketTypeId}")
	public ResponseEntity<Object> updateTicketType(@PathVariable(name = "ticketTypeId") Long ticketTypeId,
			@ModelAttribute("ticketType") @Valid TicketTypeDTO ticketTypeDTO, BindingResult result) {

		TicketType existing = ticketTypeService.getTicketTypeById(ticketTypeId);
		if (existing == null) {
			return new ResponseEntity<Object>("The ticketType with ticketType id as " + ticketTypeId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}

		ticketTypeService.updateTicketType(ticketTypeDTO, ticketTypeId);

		return new ResponseEntity<Object>(
				"You have successfully updated a ticketType with ticketType id " + ticketTypeId, HttpStatus.OK);
	}

	@DeleteMapping("/admin/api/v1/ticketType/{ticketTypeId}")
	public ResponseEntity<Object> deleteTicketType(@PathVariable(name = "ticketTypeId") Long ticketTypeId) {

		TicketType existing = ticketTypeService.getTicketTypeById(ticketTypeId);
		if (existing == null) {
			return new ResponseEntity<Object>("The ticketType with ticketType id as " + ticketTypeId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			ticketTypeService.deleteTicketType(ticketTypeId);
			return new ResponseEntity<Object>("ticketType successfully deleted", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
