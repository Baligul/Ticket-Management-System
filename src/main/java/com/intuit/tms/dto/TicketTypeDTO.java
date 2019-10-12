package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class TicketTypeDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String title;

	private String description;

	// Getters and Setters
	// ===================
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}