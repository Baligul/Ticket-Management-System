package com.intuit.tms.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class WorkflowDetailsId implements Serializable {
	@Column(name = "workflow_id")
	private Long workflowId;

	private String status;

	public WorkflowDetailsId() {
	}

	// default constructor
	public WorkflowDetailsId(Long workflowId, String status) {
		super();
		this.workflowId = workflowId;
		this.status = status;
	}

	// getters, equals() and hashCode() methods
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
