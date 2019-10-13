package com.intuit.tms.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "workflow_details")
public class WorkflowDetails extends BaseEntityWithoutId {

	@EmbeddedId
	private WorkflowDetailsId workflowDetailsId;

	private int sequence;

	protected WorkflowDetails() {
	}

	// Getters and Setters
	// ===================

	public WorkflowDetails(WorkflowDetailsId workflowDetailsId, int sequence) {
		super();
		this.workflowDetailsId = workflowDetailsId;
		this.sequence = sequence;
	}

	public WorkflowDetailsId getWorkflowDetailsId() {
		return workflowDetailsId;
	}

	public void setWorkflowDetailsId(WorkflowDetailsId workflowDetailsId) {
		this.workflowDetailsId = workflowDetailsId;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
}
