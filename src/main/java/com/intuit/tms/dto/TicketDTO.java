package com.intuit.tms.dto;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

public class TicketDTO {

	@NotNull
	private Long projectId;

	@NotNull
	private String ticketType;

	@NotNull
	private String description;

	@NotNull
	private String summary;

	private String priority;

	private Long assignee;

	private LocalDateTime dueDate;

	private String resolution;

	private String status;

	// Getters and Setters
	// ===================

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Long getAssignee() {
		return assignee;
	}

	public void setAssignee(Long assignee) {
		this.assignee = assignee;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
