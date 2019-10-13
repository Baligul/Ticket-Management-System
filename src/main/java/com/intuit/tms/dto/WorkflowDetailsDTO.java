package com.intuit.tms.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class WorkflowDetailsDTO {

	@Column(nullable = false)
	@NotNull
	private Long workflowId;

	@Column(nullable = false)
	@NotNull
	private String status;

	// Getters and Setters
	// ===================

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}