package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class TicketTypeDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String ticketType;

	private String description;

	// Getters and Setters
	// ===================
	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}