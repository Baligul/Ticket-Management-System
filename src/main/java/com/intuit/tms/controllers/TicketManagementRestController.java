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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.tms.dto.TicketDTO;
import com.intuit.tms.entities.Ticket;
import com.intuit.tms.services.ErrorBuilderService;
import com.intuit.tms.services.TicketService;

@RestController
public class TicketManagementRestController {

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ErrorBuilderService errorBuilderService;

	@ModelAttribute("ticket")
	public TicketDTO ticketDTO() {
		return new TicketDTO();
	}

	@PostMapping("/api/v1/ticket")
	public ResponseEntity<Object> saveTicket(@ModelAttribute("ticket") @Valid TicketDTO ticketDTO,
			BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			ticketService.saveTicket(ticketDTO);
			return new ResponseEntity<Object>("You have successfully added a ticket", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket cannot be saved, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/api/v1/tickets")
	public ResponseEntity<Object> getAllTickets() {
		try {
			List<Ticket> tickets = ticketService.getAllTickets();
			return new ResponseEntity<Object>(tickets, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Tickets cannot be fetched, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/api/v1/ticket/{ticketId}")
	public ResponseEntity<Object> updateTicket(@PathVariable(name = "ticketId") Long ticketId,
			@ModelAttribute("ticket") @Valid TicketDTO ticketDTO, BindingResult result) {

		Ticket existing = ticketService.getTicketById(ticketId);
		if (existing == null) {
			return new ResponseEntity<Object>("The ticket with ticket id as " + ticketId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(errorBuilderService.generateErrorMessage(result), HttpStatus.BAD_REQUEST);
		}

		try {
			ticketService.updateTicket(ticketDTO, ticketId);
			return new ResponseEntity<Object>("You have successfully updated a ticket with ticket id " + ticketId,
					HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket cannot be updated, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/api/v1/ticket/{ticketId}")
	public ResponseEntity<Object> deleteTicket(@PathVariable(name = "ticketId") Long ticketId) {

		Ticket existing = ticketService.getTicketById(ticketId);
		if (existing == null) {
			return new ResponseEntity<Object>("The ticket with ticket id as " + ticketId + " is not found",
					HttpStatus.BAD_REQUEST);
		}

		try {
			ticketService.deleteTicket(ticketId);
			return new ResponseEntity<Object>("Ticket successfully deleted", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket cannot be deleted, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/api/v1/ticket/search")
	public ResponseEntity<Object> searchTickets(@RequestBody Ticket ticket) {
		try {
			List<Ticket> searchedTickets = ticketService.serachTickets(ticket);
			return new ResponseEntity<Object>(searchedTickets, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>("Ticket cannot be searched, Reason: " + ex.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}
}
