package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WorkflowDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String title;

	// Getters and Setters
	// ===================
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}