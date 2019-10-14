package com.intuit.tms.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.TicketTypeDTO;
import com.intuit.tms.dto.TicketTypeUpdateDTO;
import com.intuit.tms.entities.TicketType;
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.TicketTypeService;

@RestController
public class TicketTypeManagementRestController {

	@Autowired
	private TicketTypeService ticketTypeService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

	@ModelAttribute("ticketType")
	public TicketTypeDTO ticketTypeDTO() {
		return new TicketTypeDTO();
	}

	@ModelAttribute("ticketTypeUpdate")
	public TicketTypeUpdateDTO ticketTypeUpdateDTO() {
		return new TicketTypeUpdateDTO();
	}

	@PostMapping("/admin/api/v1/ticketType")
	public ResponseEntity<Object> saveTicketType(@ModelAttribute("ticketType") @Valid TicketTypeDTO ticketTypeDTO,
			BindingResult result) {

		TicketType existing = ticketTypeService.getTicketTypeByTicketType(ticketTypeDTO.getTicketType());
		if (existing != null) {
			return new ResponseEntity<Object>("There is already exist a ticketType as " + ticketTypeDTO.getTicketType(),
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			ticketTypeService.saveTicketType(ticketTypeDTO);
			return new ResponseEntity<Object>("You have successfully added a ticketType", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket type cannot be saved, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/admin/api/v1/ticketTypes")
	public ResponseEntity<Object> getTicketTypes() {

		try {
			List<TicketType> ticketTypes = ticketTypeService.getAllTicketTypes();
			return new ResponseEntity<Object>(ticketTypes, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket types cannot be fetched, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/admin/api/v1/ticketType/{ticketType}")
	public ResponseEntity<Object> updateTicketType(@PathVariable(name = "ticketType") String strTicketType,
			@ModelAttribute("ticketTypeUpdate") @Valid TicketTypeUpdateDTO ticketTypeUpdateDTO, BindingResult result) {

		TicketType existing = ticketTypeService.getTicketTypeByTicketType(strTicketType);
		if (existing == null) {
			return new ResponseEntity<Object>("The ticketType as " + strTicketType + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			ticketTypeService.updateTicketType(ticketTypeUpdateDTO, strTicketType);
			return new ResponseEntity<Object>("You have successfully updated a ticketType as " + strTicketType,
					HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket type cannot be updated, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@Transactional
	@DeleteMapping("/admin/api/v1/ticketType/{ticketType}")
	public ResponseEntity<Object> deleteTicketType(@PathVariable(name = "ticketType") String strTicketType) {

		TicketType existing = ticketTypeService.getTicketTypeByTicketType(strTicketType);
		if (existing == null) {
			return new ResponseEntity<Object>("The ticketType as " + strTicketType + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			ticketTypeService.deleteTicketType(strTicketType);
			return new ResponseEntity<Object>("Ticket type successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket type cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
