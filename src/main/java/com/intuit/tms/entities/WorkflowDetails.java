package com.intuit.tms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "workflow_details")
public class WorkflowDetails extends BaseEntity {
	@Column(name = "workflow_id")
	private Long workflowId;

	private String status;

	private Integer sequence;

	protected WorkflowDetails() {
	}

	public WorkflowDetails(Long workflowId, String status) {
		super();
		this.workflowId = workflowId;
		this.status = status;
	}

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

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
}
