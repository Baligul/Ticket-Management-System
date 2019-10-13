package com.intuit.tms.dto;

import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WorkflowDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String title;

	private Set<String> statuses;

	// Getters and Setters
	// ===================
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(Set<String> statuses) {
		this.statuses = statuses;
	}
}