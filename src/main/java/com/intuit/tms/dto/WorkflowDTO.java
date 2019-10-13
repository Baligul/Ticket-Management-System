package com.intuit.tms.dto;

import java.util.SortedSet;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.intuit.tms.entities.Status;

public class WorkflowDTO {

	@Column(nullable = false, unique = true)
	@NotNull
	private String title;

	private SortedSet<Status> statuses;

	// Getters and Setters
	// ===================
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SortedSet<Status> getStatuses() {
		return statuses;
	}

	public void setStatuses(SortedSet<Status> statuses) {
		this.statuses = statuses;
	}
}