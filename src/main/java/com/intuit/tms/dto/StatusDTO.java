package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class StatusDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String status;

	private String description;

	// Getters and Setters
	// ===================
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}